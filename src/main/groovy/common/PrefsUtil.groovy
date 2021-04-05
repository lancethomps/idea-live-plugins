package common

import static common.Logs.showMessagesInConsole
import static liveplugin.PluginUtil.show

import java.lang.reflect.Modifier

import com.intellij.application.options.CodeStyle
import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.UISettings
import com.intellij.lang.LanguageUtil
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.colors.impl.AppEditorFontOptions
import com.intellij.psi.codeStyle.CodeStyleSchemes
import com.intellij.psi.impl.source.codeStyle.CodeStyleSchemeImpl

class PrefsUtil {

  static def getEditorFontSize() {
    return AppEditorFontOptions.getInstance().getState().FONT_SIZE
  }

  static def getSystemFontSize() {
    return UISettings.instance.getState().fontSize
  }

  static def getLanguage(String id) {
    return LanguageUtil.getFileLanguages().find { it.ID.toLowerCase() == id.toLowerCase() }
  }

  static def updateEditorFont(int fontSize) {
    def options = AppEditorFontOptions.getInstance()
    def state = options.getState()
    show("Changing editor font size from " + state.FONT_SIZE + " to " + fontSize)
    state.FONT_SIZE = fontSize
    options.loadState(state)
  }

  static def updateSystemFont(int fontSize) {
    def options = UISettings.instance
    show("Changing system font size from " + options.fontSize + " to " + fontSize)
    options.fontSize = fontSize
    options.fireUISettingsChanged()
  }

  static def getJavaCodeStyleKeepLineBreaks() {
    return CodeStyle.getDefaultSettings().getCommonSettings(getLanguage("java")).KEEP_LINE_BREAKS
  }

  static def updateJavaCodeStyleKeepLineBreaks(boolean keepLineBreaks) {
    def codeStyleScheme = CodeStyleSchemes.getInstance().findPreferredScheme(null) as CodeStyleSchemeImpl
    def settings = codeStyleScheme.getCodeStyleSettings()
    def javaSettings = settings.getCommonSettings(getLanguage("java"))
    javaSettings.KEEP_LINE_BREAKS = keepLineBreaks
    codeStyleScheme.setCodeStyleSettings(settings)
    codeStyleScheme.writeScheme()
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