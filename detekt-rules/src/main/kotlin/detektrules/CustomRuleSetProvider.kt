package detektrules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class CustomRuleSetProvider : RuleSetProvider {

    override val ruleSetId: String = "smyachenkov"

    override fun instance(config: Config) = RuleSet(
            ruleSetId,
            listOf(
                    NoBigDecimalDoubleConstructor(config),
                    NoEmptyLineInMethodBody(config),
                    FunctionNameLength(config)
            )
    )

}
