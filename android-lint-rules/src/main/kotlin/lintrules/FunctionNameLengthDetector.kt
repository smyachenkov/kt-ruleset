package lintrules

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope.ALL_JAVA_FILES
import com.android.tools.lint.detector.api.Severity
import org.jetbrains.uast.UMethod
import java.util.EnumSet

private const val MAX_LENGTH = 20

val FUN_NAME_LENGTH_ISSUE = Issue.create("FunctionNameLength", "Function name length",
        "Long function name affect readability", Category.CORRECTNESS,
        2, Severity.INFORMATIONAL,
        Implementation(FunctionNameLengthDetector::class.java, EnumSet.of(ALL_JAVA_FILES))
)

class FunctionNameLengthDetector : Detector(), Detector.UastScanner {
    override fun createUastHandler(ctx: JavaContext): UElementHandler = object : UElementHandler() {
                override fun visitMethod(node: UMethod) {
                    if (node.name.length > MAX_LENGTH) {
                        ctx.report(FUN_NAME_LENGTH_ISSUE, node, ctx.getLocation(node),
                                "Function name ${node.name} is longer than $MAX_LENGTH")
                    }
                }
            }
}
