package com.smyachenkov

import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import org.jetbrains.kotlin.idea.inspections.AbstractKotlinInspection
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtVisitorVoid

private const val MAX_LENGTH = 20

class FunctionNameLengthInspection : AbstractKotlinInspection() {

    override fun buildVisitor(
        holder: ProblemsHolder,
        isOnTheFly: Boolean
    ): PsiElementVisitor {
        return object: KtVisitorVoid() {
            override fun visitNamedFunction(function: KtNamedFunction) {
                function.name?.let {
                    if (it.length > MAX_LENGTH) {
                        holder.registerProblem(
                                function,
                                "Function name ${function.name} is longer than allowed $MAX_LENGTH"
                        )
                    }
                }
            }
        }
    }

}
