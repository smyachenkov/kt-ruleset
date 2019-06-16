package ktlintrules

import com.pinterest.ktlint.core.Rule
import org.jetbrains.kotlin.KtNodeTypes
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.psiUtil.children

private const val MAX_LENGTH = 20

class FunctionNameLength : Rule("function-name-length") {
    override fun visit(node: ASTNode,
                       autoCorrect: Boolean,
                       emit: (offset: Int,
                              errorMessage: String,
                              canBeAutoCorrected: Boolean
                       ) -> Unit
    ) {
        if (node.elementType == KtNodeTypes.FUN) {
            node.children()
                    .first { it.elementType == KtTokens.IDENTIFIER }
                    .takeIf { it.textLength > MAX_LENGTH }
                    ?.let {
                        emit(
                            it.startOffset,
                            "Function name ${it.text} is longer than allowed $MAX_LENGTH",
                            false
                        )
                    }
        }
    }
}
