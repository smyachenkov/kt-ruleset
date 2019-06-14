package detektrules

import io.gitlab.arturbosch.detekt.test.lint
import org.junit.Assert.*
import org.junit.Test

class FunctionNameLengthTest {

    @Test
    fun `should trigger rule on a long function name`() {
        val errors = FunctionNameLength()
                .lint("""
                    fun thisIsAnUnnecessaryLongFunctionName() {
                        print("hello, world!")
                    }
                    """.trimIndent())
        assertEquals(1, errors.size)
    }

    @Test
    fun `should not trigger rule on a short function name`() {
        val errors = FunctionNameLength()
                .lint("""
                    fun shortName() {
                        print("hello, world!")
                    }
                    """.trimIndent())
        assertEquals(0, errors.size)
    }
}
