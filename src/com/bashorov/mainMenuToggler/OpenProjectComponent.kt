package com.bashorov.mainMenuToggler

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.ApplicationComponent
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.project.ProjectManagerListener

/**
 * Hide or show the menu after the project is loaded
 */
class OpenProjectComponent : ApplicationComponent, ProjectManagerListener {

    override fun initComponent() {
        ProjectManager.getInstance().addProjectManagerListener(this)
    }

    override fun getComponentName(): String {
        return "com.bashorov.mainMenuToggler.OpenProject"
    }

    override fun disposeComponent() {
    }

    override fun projectOpened(project: Project?) {
        val state: Boolean = PropertiesComponent.getInstance().getBoolean("is_menu_visible")

        ApplicationManager.getApplication().invokeLater {
            project?.getMenuBar()?.isVisible = state
        }
    }

    override fun projectClosed(project: Project?) {
    }

    override fun canCloseProject(p0: Project?): Boolean {
        return true
    }

    override fun projectClosing(project: Project?) {
    }

}
