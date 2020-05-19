package moe.lemonneko.jaav.compiler

import moe.lemonneko.jaav.JaavBaseListener
import moe.lemonneko.jaav.JaavLexer
import moe.lemonneko.jaav.JaavParser
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*
import java.io.File
import java.nio.file.Path
import java.util.*
import kotlin.collections.HashMap

class Variable(val index: Int, val type: Int, val value: String)

interface Instruction {
    fun apply(visitor: MethodVisitor)
}

class VariableDeclaration(private val variable: Variable) : Instruction {
    override fun apply(visitor: MethodVisitor) {
        when (variable.type) {
            JaavLexer.NUMBER -> {
                val value = variable.value.toInt()
                if (value >= -128 && value <= 127) {
                    visitor.visitIntInsn(BIPUSH, value)
                } else if (value >= -65536 && value <= 65535) {
                    visitor.visitIntInsn(SIPUSH, value)
                } else {
                    visitor.visitIntInsn(LDC, value)
                }
                visitor.visitVarInsn(ISTORE, variable.index)
            }
            JaavLexer.STRING -> {
                visitor.visitLdcInsn(variable.value.substring(1 until variable.value.length))
                visitor.visitVarInsn(ASTORE, variable.index)
            }
        }
    }
}

class SyntaxError(override val message: String, lineNumber: Int) : Error(
    "Syntax error: $message, at line: $lineNumber"
)

class PrintInstruction(private val value: String) : Instruction {
    override fun apply(visitor: MethodVisitor) {
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
    }

    override fun exitPrint(ctx: JaavParser.PrintContext?) {
        ctx ?: return
        val id = ctx.ID()
        if (id == null) {
            instructionQueue.add(PrintInstruction(ctx.value().text))
        } else {
            val idText = id.text
            val value = variables[idText]?.value
                ?: throw SyntaxError("variable with name [$idText] is undefined.", id.symbol.line)
            instructionQueue.add(PrintInstruction(value))
        }
    }
}

fun getInstructions(file: File): Queue<Instruction> {
    val stream: CharStream = CharStreams.fromPath(Path.of(file.toURI()))
    val lexer = JaavLexer(stream)
    val tokenStream = CommonTokenStream(lexer)
    val parser = JaavParser(tokenStream)
    val listener = JaavTreeWalkListener()
    parser.addParseListener(listener)
    parser.compilationUnit()
    return listener.instructionQueue
}

fun generateByteCode(instructions: Queue<Instruction>, name: String): ByteArray {
    val cw = ClassWriter(0)
    cw.visit(52, ACC_PUBLIC + ACC_SUPER, name, null, "java/lang/Object", null)

    val mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC
        ,"main","([Ljava/lang/String;)V",null,null)
    val localVariablesCount = instructions.stream()
        .filter{ it is VariableDeclaration }.count()
    val maxStack = 100
    instructions.forEach { it.apply(mv) }
    mv.visitInsn(RETURN)
    mv.visitMaxs(maxStack,localVariablesCount.toInt())
    mv.visitEnd()

    return cw.toByteArray()
}

fun main(args: Array<String>) {
    if (args.isEmpty()){
        println("error: no input file.")
        return
    }
    val path = args[0]
    val file = File(path).absoluteFile
    if (!file.exists()){
        println("error: file not exists.")
        return
    }
    val instructions = getInstructions(file)
    val byteArray = generateByteCode(instructions,file.nameWithoutExtension)
    val outFile = File(file.parent,file.nameWithoutExtension + ".jy")
    outFile.writeBytes(byteArray)
}