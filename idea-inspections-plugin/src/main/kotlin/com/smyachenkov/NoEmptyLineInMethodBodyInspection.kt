package com.smyachenkov

import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import org.jetbrains.kotlin.idea.inspections.AbstractKotlinInspection
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtVisitorVoid

class NoEmptyLineInMethodBodyInspection : AbstractKotlinInspection() {

    override fun buildVisitor(
        holder: ProblemsHolder,
        isOnTheFly: Boolean
    ): PsiElementVisitor {
        return object: KtVisitorVoid() {
            override fun visitNamedFunction(function: KtNamedFunction) {
                if (function.children.size > 1) {
                    val body = function.children[1]
                    if (body.text.contains("\n\n") || body.text.endsWith("\n}")) {
                        holder.registerProblem(function, "Method body contains empty lines")
                    }
                }
            }
        }
    }

}
