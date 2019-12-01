package lintrules

import com.android.tools.lint.checks.infrastructure.TestFiles
import com.android.tools.lint.checks.infrastructure.TestLintTask
import org.junit.Test

class FunctionNameLengthDetectorTest {

    @Test
    fun `should trigger on long functions`() {
        TestLintTask.lint().allowMissingSdk()
                .files(TestFiles.kt(
                """
                    fun `this function name is longer than 20 characters`() {}
                """).indented())
                .issues(FUN_NAME_LENGTH_ISSUE)
                .run()
                .expectErrorCount(1)
    }

    @Test
    fun `should not trigger on short functions`() {
        TestLintTask.lint().allowMissingSdk()
                .files(TestFiles.kt(
                """
                    fun shortFunction() {}
                """).indented())
                .issues(FUN_NAME_LENGTH_ISSUE)
                .run()
                .expectErrorCount(1)
    }

}
