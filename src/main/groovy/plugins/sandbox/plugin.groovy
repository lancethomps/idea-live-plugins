package plugins.sandbox

import static liveplugin.PluginUtil.show

import com.intellij.ide.plugins.PluginManager
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.editor.colors.impl.AppEditorFontOptions
import com.intellij.util.ui.UIUtil

import common.Logs
import common.PrefsUtil

// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

if (isIdeStartup) return

static def showEditorFontSize() {
  show("Editor Font Size: ${AppEditorFontOptions.getInstance().getState().FONT_SIZE}")
}

static def logPlugins() {
  List<String> pluginIds = PluginManager.getPlugins().collect { it.getPluginId().toString() }.toSorted(String.CASE_INSENSITIVE_ORDER)
  Logs.showMessagesInConsole("Plugin IDs", pluginIds)
}

static def logActions() {
  List<String> actionIds = ActionManager.getInstance().getActionIds("").toList().toSorted(String.CASE_INSENSITIVE_ORDER)
  Logs.showMessagesInConsole("Action IDs", actionIds)
}

static def logUISettings() {
  PrefsUtil.logUtilClassBooleanSettings(UIUtil.class)
}

logActions()
logPlugins()
logUISettings()
