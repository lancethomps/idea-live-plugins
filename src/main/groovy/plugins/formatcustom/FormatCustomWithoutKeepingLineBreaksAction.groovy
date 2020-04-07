package plugins.formatcustom

import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

import org.jetbrains.annotations.NotNull

import com.intellij.application.options.CodeStyle
import com.intellij.application.options.codeStyle.arrangement.action.RearrangeCodeAction
import com.intellij.codeInsight.actions.AbstractLayoutCodeProcessor
import com.intellij.codeInsight.actions.ReformatCodeAction
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile
import com.intellij.psi.codeStyle.CodeStyleSettingsManager
import com.intellij.util.IncorrectOperationException

import common.PrefsUtil
import liveplugin.PluginUtil

class FormatCustomWithoutKeepingLineBreaksAction extends AnAction {

  private final ReformatCodeAction reformatCodeAction = new ReformatCodeAction()
  private final RearrangeCodeAction rearrangeCodeAction = new RearrangeCodeAction()

  @Override
  void actionPerformed(AnActionEvent event) {
    callActions(event)
    resetCodeStyle(event)
  }

  @Override
  void update(AnActionEvent event) {
    updateCodeStyle(event)
    reformatCodeAction.update(event)
    rearrangeCodeAction.update(event)
  }

  void callActions(AnActionEvent event) {
    reformatCodeAction.actionPerformed(event)
    rearrangeCodeAction.actionPerformed(event)
  }

  Project getProjectFromEvent(AnActionEvent event) {
    DataContext dataContext = event.getDataContext()
    Project eventProject = CommonDataKeys.PROJECT.getData(dataContext)
    def file = getFileFromEvent(event)
    if (file == null) {
      return eventProject
    }
    return file.getProject()
  }

  PsiFile getFileFromEvent(AnActionEvent event) {
    DataContext dataContext = event.getDataContext()
    final Editor editor = CommonDataKeys.EDITOR.getData(dataContext)
    if (editor == null) {
      return null
    }
    return PsiDocumentManager.getInstance(CommonDataKeys.PROJECT.getData(dataContext)).getPsiFile(editor.getDocument())
  }

  void resetCodeStyle(AnActionEvent event) {
    def project = getProjectFromEvent(event)
    def processor = new AbstractLayoutCodeProcessor(project, getFileFromEvent(event), "FormatCustomWithoutKeepingLineBreaksAction", "Resetting style...", false) {

      @Override
      protected FutureTask<Boolean> prepareTask(@NotNull PsiFile file, boolean processChangedTextOnly) throws IncorrectOperationException {
        return new FutureTask<Boolean>(new Callable<Boolean>() {

          @Override
          Boolean call() throws Exception {
            return true
          }
        })
      }
    }
    processor.setPostRunnable(new Runnable() {

      @Override
      void run() {
        PluginUtil.log("resetting code style...")
        PrefsUtil.updateJavaCodeStyleKeepLineBreaks(true)
        CodeStyleSettingsManager.getInstance(project).dropTemporarySettings()
      }
    })
    processor.run()
  }

  void updateCodeStyle(AnActionEvent event) {
    def currentValue = PrefsUtil.getJavaCodeStyleKeepLineBreaks()
    PluginUtil.log("current: " + currentValue)

    def project = getProjectFromEvent(event)
    PrefsUtil.updateJavaCodeStyleKeepLineBreaks(false)
    CodeStyleSettingsManager.getInstance(project).setTemporarySettings(CodeStyle.getDefaultSettings())
  }
}