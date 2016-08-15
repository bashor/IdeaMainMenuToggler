package com.bashorov.mainMenuToggler

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.actionSystem.ToggleAction
import javax.swing.JMenuBar

class ToggleMainMenuAction : ToggleAction() {

    override fun isSelected(e: AnActionEvent?): Boolean {
        if (e == null) return false

        val menuBar = getMenuBar(e) ?: return false

        return menuBar.isVisible
    }

    override fun setSelected(e: AnActionEvent?, state: Boolean) {
        if (e == null) return

        val menuBar = getMenuBar(e) ?: return

        menuBar.isVisible = state

        PropertiesComponent.getInstance().setValue("is_menu_visible", state)
    }

    override fun update(e: AnActionEvent) {
        val p = e.presentation

        p.text = MENU_TEXT
        if (getMenuBar(e) == null) {
            p.isEnabled = false
        } else {
            super.update(e)
        }
    }

    private fun getMenuBar(e: AnActionEvent): JMenuBar? {
        val project = PlatformDataKeys.PROJECT.getData(e.dataContext) ?: return null
        return project.getMenuBar()
    }

    companion object {
        private val MENU_TEXT = "Main Menu"
    }
}

