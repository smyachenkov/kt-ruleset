package ktlintrules

import com.github.shyiko.ktlint.test.lint
import org.junit.Assert.assertEquals
import org.junit.Test


class NoBigDecimalDoubleConstructorTest {

    @Test
    fun `should trigger rule on Double constructor`() {
        val foo = NoBigDecimalDoubleConstructor()
                .lint("""
                    import java.math.BigDecimal
                    val foo = BigDecimal(3.14)
                    """.trimIndent())
        assertEquals(1, foo.size)
    }

    @Test
    fun `should trigger rule on Float constructor`() {
        val foo = NoBigDecimalDoubleConstructor()
                .lint("""
                    import java.math.BigDecimal
                    val foo = BigDecimal(3.14f)
                    """.trimIndent())
        assertEquals(1, foo.size)
    }

}