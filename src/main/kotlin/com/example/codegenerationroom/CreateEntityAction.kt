package com.example.codegenerationroom

import com.intellij.codeInsight.CodeInsightActionHandler
import com.intellij.codeInsight.actions.CodeInsightAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

class CreateEntityAction : CodeInsightAction(), CodeInsightActionHandler {
    override fun getHandler(): CodeInsightActionHandler = this

    override fun invoke(project: Project, editor: Editor, file: PsiFile) {
        TODO("Not yet implemented")
    }
}