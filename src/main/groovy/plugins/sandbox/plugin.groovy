package plugins.sandbox

import static liveplugin.PluginUtil.show

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.impl.ApplicationImpl
import com.intellij.openapi.editor.colors.impl.AppEditorFontOptions

import common.Runner

// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

Runner.isIdeStartup = isIdeStartup

if (isIdeStartup) return

static def showEditorFontSize() {
  show("Editor Font Size: ${AppEditorFontOptions.getInstance().getState().FONT_SIZE}")
}

static def test() {
  ApplicationImpl app = (ApplicationImpl) ApplicationManager.getApplication()
  show("Save allowed: ${app.isSaveAllowed()}")
}

test()
