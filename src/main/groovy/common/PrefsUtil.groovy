package common

import static common.Logs.showMessagesInConsole
import static liveplugin.PluginUtil.show

import java.lang.reflect.Modifier

import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.UISettings
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.colors.impl.AppEditorFontOptions

class PrefsUtil {

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

  static def reloadUI() {
    LafManager lafManager = LafManager.getInstance();
    lafManager.updateUI();
    EditorFactory.instance.refreshAllEditors()
  }

  static def logUtilClassBooleanSettings(Class<?> type) {
    def messages = type.getDeclaredMethods().findAll { method ->
      return method.getName().startsWith("is") && method.getParameterCount() == 0 && Modifier.isStatic(method.getModifiers()) && Modifier.isPublic(method.getModifiers())
    }.sort { a, b -> a.getName().compareTo(b.getName()) }.collect { method ->
      return String.format("%-35s: %s", method.getName(), method.invoke(null));
    }
    showMessagesInConsole(type.getSimpleName() + " Settings", messages)
  }
}