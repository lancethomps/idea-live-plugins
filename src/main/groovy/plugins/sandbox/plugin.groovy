package plugins.sandbox

import static liveplugin.PluginUtil.log
import static liveplugin.PluginUtil.show

import com.intellij.ide.plugins.PluginManager
import com.intellij.openapi.editor.colors.impl.AppEditorFontOptions
// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

if (isIdeStartup) return

static def showEditorFontSize() {
  show("Editor Font Size: ${AppEditorFontOptions.getInstance().getState().FONT_SIZE}")
}

static def logPlugins() {
  def pluginIds = PluginManager.getPlugins().collect { it.getPluginId() }
  pluginIds.sort()
  pluginIds.each { log(it) }
}

showEditorFontSize()
//logPlugins()
