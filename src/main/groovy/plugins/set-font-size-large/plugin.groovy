import com.intellij.ide.ui.UISettings
import com.intellij.openapi.editor.colors.impl.AppEditorFontOptions
import com.intellij.openapi.wm.WindowManager

import javax.swing.JFrame

import static liveplugin.PluginUtil.*

if (isIdeStartup) return

static def updateEditorFont(int fontSize) {
  def options = AppEditorFontOptions.getInstance()
  def state = options.getState()
  show("Changing editor font size from " + state.FONT_SIZE + " to " + fontSize)
  state.FONT_SIZE = fontSize
  options.loadState(state)
}

static def updateSystemFont(int fontSize) {
  def options = UISettings.instance
  def state = options.getState()
  show("Changing system font size from " + state.fontSize + " to " + fontSize)
  state.fontSize = fontSize
  options.loadState(state)
}

updateEditorFont(11)
updateSystemFont(11)
