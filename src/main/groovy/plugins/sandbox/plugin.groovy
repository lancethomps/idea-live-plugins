package plugins.sandbox

import static liveplugin.PluginUtil.registerAction
import static liveplugin.PluginUtil.show

import java.lang.reflect.Field
import java.util.concurrent.ConcurrentMap

import com.intellij.ide.plugins.PluginManager
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.colors.impl.AppEditorFontOptions
import com.intellij.util.messages.Topic
import com.intellij.util.messages.impl.MessageBusConnectionImpl
import com.intellij.util.messages.impl.MessageBusImpl
import com.intellij.util.ui.UIUtil

import common.Logs
import common.PrefsUtil
import common.Runner

// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

Runner.isIdeStartup = isIdeStartup

if (isIdeStartup) return

static def showEditorFontSize() {
  show("Editor Font Size: ${AppEditorFontOptions.getInstance().getState().FONT_SIZE}")
}

showEditorFontSize()
