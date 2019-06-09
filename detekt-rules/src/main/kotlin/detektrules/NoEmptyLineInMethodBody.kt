package detektrules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.KtNodeTypes
import org.jetbrains.kotlin.psi.KtNamedFunction

private const val EMPTY_LINE = "\n\n "

class NoEmptyLineInMethodBody(config: Config = Config.empty) : Rule(config) {

    override val issue = Issue(
            javaClass.simpleName,
            Severity.Style,
            "Empty line in method",
            Debt.FIVE_MINS
    )

    override fun visitNamedFunction(function: KtNamedFunction) {
        val hasEmptyLines = function.children
                .filter { it.node.elementType == KtNodeTypes.BLOCK }
                .first()
                .text
                .contains(EMPTY_LINE)
        if (hasEmptyLines) {
            report(CodeSmell(
                    issue,
                    Entity.from(function),
                    "Method ${function.name} has empty lines in it's body"
            ))
        }
    }
}
