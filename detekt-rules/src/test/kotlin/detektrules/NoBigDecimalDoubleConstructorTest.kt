package detektrules

import io.gitlab.arturbosch.detekt.test.lint
import org.junit.Assert.assertEquals
import org.junit.Test

class NoBigDecimalDoubleConstructorTest {

    @Test
    fun `should find error on Double constructor`() {
        val errors = NoBigDecimalDoubleConstructor()
                .lint("""
                    import java.math.BigDecimal
                    val foo = BigDecimal(3.14)
                    """.trimIndent())
        assertEquals(1, errors.size)
    }

    @Test
    fun `should find error on Float constructor`() {
        val errors = NoBigDecimalDoubleConstructor()
                .lint("""
                    import java.math.BigDecimal
                    val foo = BigDecimal(3.14f)
                    """.trimIndent())
        assertEquals(1, errors.size)
    }

    @Test
    fun `should find no errors on String constructor`() {
        val errors = NoBigDecimalDoubleConstructor()
                .lint("""
                    import java.math.BigDecimal
                    val foo = BigDecimal("3.14")
                    """.trimIndent())
        assertEquals(0, errors.size)
    }

}
