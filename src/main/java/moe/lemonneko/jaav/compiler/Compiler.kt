@file:JvmName("CompilerKt")

package moe.lemonneko.jaav.compiler

import moe.lemonneko.jaav.JaavBaseListener
import moe.lemonneko.jaav.JaavLexer
import moe.lemonneko.jaav.JaavParser
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.apache.logging.log4j.LogManager
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*
import java.io.File
import java.nio.file.Files
import java.util.*
import kotlin.collections.HashMap

private val logger = LogManager.getLogger()

private var debug = false

class Variable(val index: Int, val type: Int, val value: String)

interface Instruction {
    fun apply(visitor: MethodVisitor)
}

class VariableDeclaration(private val variable: Variable) : Instruction {
    override fun apply(visitor: MethodVisitor) {
        when (variable.type) {
            JaavLexer.NUMBER -> {
                val value = variable.value.toLong()
                if (value >= -128 && value <= 127) {
                    if (debug) println("将8位字节类型值推至栈顶")
                    visitor.visitIntInsn(BIPUSH, value.toInt())
                } else if (value >= -65536 && value <= 65535) {
                    if (debug) println("将16位短整型值推至栈顶")
                    visitor.visitIntInsn(SIPUSH, value.toInt())
                } else {
                    if (value >= - Math.pow(2.0,32.0) && value <= Math.pow(2.0,32.0) - 1){
                        if (debug) println("将32位整型值推至栈顶")
                    }else{
                        if (debug) println("将64位长整型值推至栈顶")
                    }
                    visitor.visitLdcInsn(value)
                }
                if (debug) println("将栈顶整型值存入本地变量")
                visitor.visitVarInsn(ISTORE, variable.index)
            }
            JaavLexer.STRING -> {
                if (debug) println("将字符串值推至栈顶")
                visitor.visitLdcInsn(variable.value.substring(1, variable.value.length - 1))
                if (debug) println("将栈顶字符串值存入本地变量")
                visitor.visitVarInsn(ASTORE, variable.index)
            }
        }
    }
}

class SyntaxError(override val message: String, lineNumber: Int) : Error(
    "发现语法错误：$message，行号是：$lineNumber"
)

class SemanticError(override val message: String,lineNumber: Int) : Error(
    "发现语义错误：$message，行号是：$lineNumber"
)

class PrintInstruction(private val value: String) : Instruction {
    override fun apply(visitor: MethodVisitor) {
        if (debug) println("")
        visitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
        visitor.visitLdcInsn(value)
        visitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false)
    }
}

class JaavTreeWalkListener : JaavBaseListener() {
    val variables = HashMap<String, Variable>()
    val instructionQueue = ArrayDeque<Instruction>()
    override fun exitVariable(ctx: JaavParser.VariableContext?) {
        ctx ?: return
        val varName = ctx.ID().text
        val varIndex = variables.size
        val varTextValue = ctx.value().text
        val variable = Variable(varIndex, ctx.value().start.type, varTextValue)
        variables[varName] = variable
        instructionQueue.add(VariableDeclaration(variable))
        if (debug) println("定义变量 $varName")
    }

    override fun exitPrint(ctx: JaavParser.PrintContext?) {
        ctx ?: return
        val id = ctx.ID()
        if (id == null) {
            if (ctx.value().NUMBER() != null) {
                instructionQueue.add(PrintInstruction(ctx.value().text))
                if (debug) println("使用输出语句")
            } else {
                instructionQueue.add(PrintInstruction(ctx.value().text.substring(1, ctx.value().text.length - 1)))
                if (debug) println("使用输出语句")
            }
        } else {
            val idText = id.text
            var value = variables[idText]?.value
                ?: throw SemanticError("没有找到名为 ${id.text} 的变量", id.symbol.line)
            val type = variables[idText]!!.type
            if (type == JaavLexer.STRING) {
                value = value.substring(1, value.length - 1)
            }
            instructionQueue.add(PrintInstruction(value))
            if (debug) println("使用输出语句")
        }
    }
}

fun getInstructions(file: File): Queue<Instruction> {
    val stream: CharStream = CharStreams.fromStream(file.inputStream())
    val lexer = JaavLexer(stream)
    val tokenStream = CommonTokenStream(lexer)
    val parser = JaavParser(tokenStream)
    val listener = JaavTreeWalkListener()
    parser.addParseListener(listener)
    parser.compilationUnit()
    return listener.instructionQueue
}

fun generateByteCode(instructions: Queue<Instruction>, name: String): ByteArray {
    if (debug) println("开始生成字节码")
    val cw = ClassWriter(0)
    if (debug) println("创建类 $name")
    cw.visit(52, ACC_PUBLIC + ACC_SUPER, name, null, "java/lang/Object", null)
    if (debug) println("生成主方法")
    val mv = cw.visitMethod(
        ACC_PUBLIC + ACC_STATIC
        , "main", "([Ljava/lang/String;)V", null, null
    )
    val localVariablesCount = instructions.stream()
        .filter { it is VariableDeclaration }.count()
    val maxStack = 100
    instructions.forEach { it.apply(mv) }
    mv.visitInsn(RETURN)
    mv.visitMaxs(maxStack, localVariablesCount.toInt())
    mv.visitEnd()

    return cw.toByteArray()
}

fun main(args: Array<String>) {
    try {
        if (args.contains("-v") || args.contains("--verbose")){
            debug = true
        }
        if (args.isEmpty()) {
            println("发生错误：需要输入一个文件")
            return
        }
        val path = args[0]
        val file = File(path).absoluteFile
        if (!file.exists()) {
            println("发生错误：输入的文件路径不存在")
            return
        }
        if (debug) println("编译流程开始")
        val instructions = getInstructions(file)
        val byteArray = generateByteCode(instructions, file.nameWithoutExtension)
        if (debug) println("字节码文件生成完毕，总长度${byteArray.size}")
        val outFile = File(file.parent, file.nameWithoutExtension + ".class")
        // 检查文件是否已经存在
        if (outFile.exists()){
            outFile.delete()
        }
        outFile.createNewFile()
        if (debug) println("开始写入文件")
        outFile.writeBytes(byteArray)

        if (debug) println("文件写入完毕，编译流程结束")
    } catch (e: Throwable) {
        println(e::class.java.name)
    }
}