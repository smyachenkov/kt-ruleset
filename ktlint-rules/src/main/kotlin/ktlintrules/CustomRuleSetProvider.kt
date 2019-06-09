package ktlintrules

import com.github.shyiko.ktlint.core.RuleSet
import com.github.shyiko.ktlint.core.RuleSetProvider

class CustomRuleSetProvider : RuleSetProvider {
    override fun get() = RuleSet(
            "smyachenkov",
            NoBigDecimalDoubleConstructor(),
            NoEmptyLineInMethodBody()
    )
}
