package com.example.codegenerationroom

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.project.Project
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import javax.swing.JComponent

class CreateEntityDialog(
    project: Project
) : DialogWrapper(project, true) {
    init {
        title = "Create Entity"
        init()
    }

    override fun createCenterPanel(): JComponent {
        return panel {
            row("Class name") {
                textField()
                    .bindText(CreateEntityService.instance.createEntityState::className)
            }
            row {
                label("Attributes")
            }
            row {
                label("Name")
                textField()
                    .bindText(CreateEntityService.instance.createEntityState::attributeName1)
                label("Type")
                textField()
                    .bindText(CreateEntityService.instance.createEntityState::attributeType1)
            }
            row {
                label("Name")
                textField()
                    .bindText(CreateEntityService.instance.createEntityState::attributeName2)
                label("Type")
                textField()
                    .bindText(CreateEntityService.instance.createEntityState::attributeType2)
            }
            row {
                label("Name")
                textField()
                    .bindText(CreateEntityService.instance.createEntityState::attributeName3)
                label("Type")
                textField()
                    .bindText(CreateEntityService.instance.createEntityState::attributeType3)
            }
            row {
                label("Name")
                textField()
                    .bindText(CreateEntityService.instance.createEntityState::attributeName4)
                label("Type")
                textField()
                    .bindText(CreateEntityService.instance.createEntityState::attributeType4)
            }
            row {
                label("Name")
                textField()
                    .bindText(CreateEntityService.instance.createEntityState::attributeName5)
                label("Type")
                textField()
                    .bindText(CreateEntityService.instance.createEntityState::attributeType5)
            }
        }
    }
}