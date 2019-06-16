package ktlintrules

import com.pinterest.ktlint.core.Rule
import org.jetbrains.kotlin.KtNodeTypes
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.psi.psiUtil.children

class NoBigDecimalDoubleConstructor : Rule("no-bigdecimal-float-constructor") {
    override fun visit(node: ASTNode,
                       autoCorrect: Boolean,
                       emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit
    ) {
        if (node.elementType == KtNodeTypes.CALL_EXPRESSION
                && node.firstChildNode.elementType == KtNodeTypes.REFERENCE_EXPRESSION
                && node.lastChildNode.elementType == KtNodeTypes.VALUE_ARGUMENT_LIST
                && node.firstChildNode.text == "BigDecimal"
        ) {
            val argumentList = node.lastChildNode
            val arguments = argumentList.children().filter { it.elementType == KtNodeTypes.VALUE_ARGUMENT }.toList()
            if (arguments.size != 1) {
                return
            }
            val argument = arguments[0].children().filter { it.elementType == KtNodeTypes.FLOAT_CONSTANT }.toList()
            if (argument.size != 1) {
                return
            }
            val floatValue = argument[0]
            emit(node.startOffset, "Calling BigDecimal float constructor for '${floatValue.text}' value.", false)
        }
    }
}
