package detektrules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtNamedFunction

private const val MAX_LENGTH = 20

class FunctionNameLength(config: Config = Config.empty) : Rule(config) {

    override val issue = Issue(
            javaClass.simpleName,
            Severity.CodeSmell,
            "Code smell",
            Debt.FIVE_MINS
    )

    override fun visitNamedFunction(function: KtNamedFunction) {
        function.name?.let {
            if (it.length > MAX_LENGTH) {
                report(
                    CodeSmell(
                        issue,
                        Entity.from(function),
                        "Function name ${function.name} is longer than allowed $MAX_LENGTH"
                    )
                )
            }
        }
    }
}
