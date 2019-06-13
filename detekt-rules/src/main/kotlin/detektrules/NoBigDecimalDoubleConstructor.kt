package detektrules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.KtNodeTypes
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtConstantExpression
import org.jetbrains.kotlin.psi.KtValueArgumentList

class NoBigDecimalDoubleConstructor(config: Config = Config.empty) : Rule(config) {

    override val issue = Issue(
            javaClass.simpleName,
            Severity.Maintainability,
            "Calling BigDecimal float constructor",
            Debt.FIVE_MINS
    )

    override fun visitCallExpression(expression: KtCallExpression) {
        if (expression.firstChild.text == "BigDecimal"
                && (expression.lastChild as KtValueArgumentList).elementType == KtNodeTypes.VALUE_ARGUMENT_LIST) {
            val constructorArguments = expression.lastChild.children
            if (constructorArguments.size == 1
                    && (constructorArguments[0].firstChild is KtConstantExpression)
                    && (constructorArguments[0].firstChild as KtConstantExpression).elementType == KtNodeTypes.FLOAT_CONSTANT) {
                report(CodeSmell(
                        issue,
                        Entity.from(expression),
                        "Usage of BigDecimal float constructor ${expression.name}"
                ))
            }
        }
    }

}
