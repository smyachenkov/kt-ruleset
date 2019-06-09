package ktlintrules

import com.github.shyiko.ktlint.test.lint
import org.junit.Assert
import org.junit.Test

class NoEmptyLineInMethodBodyTest {

    @Test
    fun `should trigger rule on an empty line inside function`() {
        val errors = NoEmptyLineInMethodBody()
                .lint("""
                    package rules

                    fun foo() {
                        println("hello")

                        println("world")
                    }
                    """.trimIndent())
        Assert.assertEquals(1, errors.size)
    }

    @Test
    fun `should not trigger rule on a function without empty lines`() {
        val errors = NoEmptyLineInMethodBody()
                .lint("""
                    fun foo() {
                        println("hello")
                        println("world")
                    }
                    """.trimIndent())
        Assert.assertEquals(0, errors.size)
    }

}
