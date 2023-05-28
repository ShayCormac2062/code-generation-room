package com.example.codegenerationroom

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.idea.util.application.executeWriteCommand
import org.jetbrains.kotlin.psi.KtPsiFactory

class CreateEntityAction : AnAction() {
    override fun update(e: AnActionEvent) {
        if (e.getData(CommonDataKeys.PSI_FILE)?.isDirectory == false) {
            e.presentation.isEnabled = false
            e.presentation.isVisible = false
        }
    }

    override fun actionPerformed(e: AnActionEvent) {
        e.project?.let {
            val pkg = e.getData(CommonDataKeys.PSI_ELEMENT)
            val wasDialogOK = CreateEntityDialog(it).showAndGet()
            if (wasDialogOK) {
                handleDialogOK(it, pkg)
            }
        }
    }

    private fun handleDialogOK(project: Project, pkg: PsiElement?) {
        val state = CreateEntityService.instance.createEntityState
        val psiFactory = KtPsiFactory(project)
        project.executeWriteCommand("CreateEntityClass") {
            val entityFile = psiFactory.createFile(state.className + ".kt", getEntityClassString(state))
            pkg?.add(entityFile)

            val appDatabaseFile = psiFactory.createFile("AppDatabase.kt", getAppDatabaseClassString(state))

            pkg?.add(appDatabaseFile)
        }
    }

    private fun getAppDatabaseClassString(state: CreateEntityService.State): String {
        return "import android.content.Context\n" +
                "import androidx.room.Database\n" +
                "import androidx.room.Room\n" +
                "import androidx.room.RoomDatabase\n" +
                "\n" +
                "import ${state.className}\n\n" +
                "@Database(entities = [${state.className}::class], version = 1)\n" +
                "abstract class AppDatabase : RoomDatabase() {\n" +
                "   \n\n" +
                "   companion object {\n" +
                "       private const val DATABASE_NAME = \"${state.className}.db\"\n\n" +
                "       @Volatile\n" +
                "       private var instance: AppDatabase? = null\n" +
                "       private val LOCK = Any()\n\n" +
                "       operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {\n" +
                "           buildDatabase(context).also { instance = it }\n" +
                "       }\n\n" +
                "       private fun buildDatabase(context: Context) =\n" +
                "           Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)\n" +
                "               .fallbackToDestructiveMigration()\n" +
                "               .build()\n" +
                "   }\n" +
                "}"
    }

    private fun getEntityClassString(state: CreateEntityService.State): String {
        return "import androidx.room.Entity\n\n" +
                "@Entity(tableName = \"${state.className}\")\n" +
                "data class ${state.className}(\n" +
                getAttributes(state) +
                "\n)"
    }

    private fun getAttributes(state: CreateEntityService.State): String {
        var res = ""
        if (state.attributeName1.isNotBlank())
            res += "    val ${state.attributeName1}: ${state.attributeType1}"
        if (state.attributeName2.isNotBlank())
            res += ",\n    val ${state.attributeName2}: ${state.attributeType2}"
        if (state.attributeName3.isNotBlank())
            res += ",\n    val ${state.attributeName3}: ${state.attributeType3}"
        if (state.attributeName4.isNotBlank())
            res += ",\n    val ${state.attributeName4}: ${state.attributeType4}"
        if (state.attributeName5.isNotBlank())
            res += ",\n    val ${state.attributeName5}: ${state.attributeType5}"
        return res
    }
}