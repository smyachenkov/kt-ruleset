package ktlintrules

import com.pinterest.ktlint.core.Rule
import com.pinterest.ktlint.core.ast.nextCodeLeaf
import org.jetbrains.kotlin.KtNodeTypes
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.psiUtil.children

private const val EMPTY_LINE = "\n\n "

class NoEmptyLineInMethodBody : Rule("no-empty-line-in-method-body") {
    override fun visit(node: ASTNode,
                       autoCorrect: Boolean,
                       emit: (offset: Int,
                              errorMessage: String,
                              canBeAutoCorrected: Boolean
                       ) -> Unit
    ) {
        if (node.elementType == KtNodeTypes.FUN) {
            val functionName = node.nextCodeLeaf()?.nextCodeLeaf()?.text
            val block = node.lastChildNode
            block.children()
                    .filter { it.elementType == KtTokens.WHITE_SPACE }
                    .filter { it.text.contains(EMPTY_LINE) }
                    .forEach {
                        emit(node.startOffset, "Empty line in method $functionName", true)
                    }
        }
    }
}
