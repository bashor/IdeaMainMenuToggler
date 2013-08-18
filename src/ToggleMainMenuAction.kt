package com.bashorov.mainMenuToggler

import javax.swing.JMenuBar
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.actionSystem.ToggleAction
import com.intellij.openapi.wm.ex.WindowManagerEx
import com.intellij.openapi.wm.impl.IdeFrameImpl

public class ToggleMainMenuAction: ToggleAction() {
    public override fun isSelected(e: AnActionEvent?): Boolean {
        if (e == null) return false

        val menuBar = getMenuBar(e)
        if (menuBar == null) return false

        return menuBar.isVisible()
    }

    public override fun setSelected(e: AnActionEvent?, state: Boolean) {
        if (e == null) return

        val menuBar = getMenuBar(e)
        if (menuBar == null) return

        menuBar.setVisible(state)
    }

    public override fun update(e: AnActionEvent?) {
        if (e == null) return
        val p = e.getPresentation()

        p.setText(MENU_TEXT)
        if (getMenuBar(e) == null) {
            p.setEnabled(false);
        } else {
            super.update(e);
        }
    }

    private fun getFrame(e: AnActionEvent): IdeFrameImpl? {
        val project = PlatformDataKeys.PROJECT.getData(e.getDataContext());
        if (project == null) return null
        return WindowManagerEx.getInstanceEx()?.getFrame(project);
    }
    private fun getMenuBar(e: AnActionEvent): JMenuBar? {
        val frame = getFrame(e)
        if (frame == null) return null
        return frame.getJMenuBar()
    }

    class object {
        private val MENU_TEXT = "Main Menu"
    }
}