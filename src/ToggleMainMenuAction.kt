package com.bashorov.mainMenuToggler

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.actionSystem.ToggleAction
import com.intellij.openapi.wm.ex.WindowManagerEx
import com.intellij.openapi.wm.impl.IdeFrameImpl
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

    private fun getFrame(e: AnActionEvent): IdeFrameImpl? {
        val project = PlatformDataKeys.PROJECT.getData(e.dataContext) ?: return null
        return WindowManagerEx.getInstanceEx()?.getFrame(project)
    }
    private fun getMenuBar(e: AnActionEvent): JMenuBar? {
        val frame = getFrame(e) ?: return null
        return frame.jMenuBar
    }

    companion object {
        private val MENU_TEXT = "Main Menu"
    }
}
