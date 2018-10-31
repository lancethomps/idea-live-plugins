import static liveplugin.PluginUtil.*

import com.intellij.ide.plugins.PluginManager
import com.intellij.openapi.editor.colors.impl.AppEditorFontOptions

import common.PrefsUtil

// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy


static def showEditorFontSize() {
  show("Editor Font Size: ${AppEditorFontOptions.getInstance().getState().FONT_SIZE}")
}

static def logPlugins() {
  def pluginIds = PluginManager.getPlugins().collect {it.getPluginId()}
  pluginIds.sort()
  pluginIds.each { log(it) }
}

PrefsUtil.hello()
//showEditorFontSize()
//logPlugins()
