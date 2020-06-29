package moe.lemonneko.jaav.test

import org.junit.Test
import moe.lemonneko.jaav.compiler.main as compile

class ChineseSyntaxTest {
    @Test
    fun compileTest(){
        compile(arrayOf("src/test/resources/Test.av"))
    }
}