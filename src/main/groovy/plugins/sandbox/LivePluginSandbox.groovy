package plugins.sandbox

import static liveplugin.PluginUtil.show

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.impl.ApplicationImpl
import com.intellij.openapi.editor.colors.impl.AppEditorFontOptions
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectFileIndex
import com.intellij.openapi.vfs.VirtualFile

import common.Logs
import liveplugin.PluginUtil

class LivePluginSandbox {

  static def showEditorFontSize() {
    show("Editor Font Size: ${AppEditorFontOptions.getInstance().getState().FONT_SIZE}")
  }

  static def saveAllowed() {
    ApplicationImpl app = (ApplicationImpl) ApplicationManager.getApplication()
    show("Save allowed: ${app.isSaveAllowed()}")
  }

  static def test2() {
    def info = ActionManager.getInstance().getAction("CopyPaths").getClass().getName()
    Logs.showMessagesInConsole("Action", [info])
  }

  static void test() {
    Project project = PluginUtil.currentProjectInFrame()
    VirtualFile file = PluginUtil.findFileByName("LivePluginSandbox.groovy", project).getVirtualFile()
    ProjectFileIndex projectFileIndex = ProjectFileIndex.getInstance(project)
    Module module = projectFileIndex.getModuleForFile(file)
    VirtualFile sourceRoot = projectFileIndex.getSourceRootForFile(file)
    show("sourceRoot: ${sourceRoot}")
    VirtualFile contentRoot = projectFileIndex.getContentRootForFile(file)
    show("contentRoot: ${contentRoot}")
  }
}
