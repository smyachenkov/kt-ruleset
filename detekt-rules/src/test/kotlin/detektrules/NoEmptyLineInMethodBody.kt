package detektrules

import io.gitlab.arturbosch.detekt.test.lint
import org.junit.Assert.assertEquals
import org.junit.Test

class NoEmptyLineInMethodBodyTest {

    @Test
    fun `should trigger rule when method has empty lines`() {
        val errors = NoEmptyLineInMethodBody().lint("""
        fun foo(){
            println("hello")

            println("world")
        }
        """.trimIndent())
        assertEquals(1, errors.size)
    }

    @Test
    fun `should not trigger rule when method has no empty lines`() {
        val errors = NoEmptyLineInMethodBody().lint("""
        fun foo(){
            println("hello")
            println("world")
        }
        """.trimIndent())
        assertEquals(0, errors.size)
    }

}
