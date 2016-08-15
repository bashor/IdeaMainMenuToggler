package com.bashorov.mainMenuToggler

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ex.WindowManagerEx
import javax.swing.JMenuBar

fun Project.getMenuBar(): JMenuBar? {
    val frame = WindowManagerEx.getInstanceEx()?.getFrame(this) ?: return null
    return frame.jMenuBar
}
