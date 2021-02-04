package plugins.configlogger

import static liveplugin.PluginUtil.currentProjectInFrame
import static liveplugin.PluginUtil.show
import static liveplugin.PluginUtil.showInConsole

import java.lang.reflect.Field
import java.util.concurrent.ConcurrentMap

import com.intellij.ide.plugins.PluginManager
import com.intellij.lang.LanguageUtil
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileTypes.FileTypeManager
import com.intellij.util.messages.Topic
import com.intellij.util.messages.impl.MessageBusConnectionImpl
import com.intellij.util.messages.impl.MessageBusImpl
import com.intellij.util.ui.UIUtil

import common.Logs
import common.PrefsUtil
import groovy.json.JsonOutput

class ConfigLogger {

  static void logActions() {
    List<String> actionIds = ActionManager.getInstance().getActionIds("").toList().toSorted(String.CASE_INSENSITIVE_ORDER)
    Logs.showMessagesInConsole("Action IDs", actionIds)
  }

  static void logActionListeners() {
    MessageBusImpl messageBus = ApplicationManager.getApplication().getMessageBus() as MessageBusImpl
    Field mySubscribersField = MessageBusImpl.class.getDeclaredField("mySubscribers")
    mySubscribersField.setAccessible(true)
    ConcurrentMap<Topic, List<MessageBusConnectionImpl>> mySubscribers = (ConcurrentMap<Topic, List<MessageBusConnectionImpl>>) mySubscribersField.get(messageBus)
    List<String> connectionsDetails = new ArrayList<>()
    List<String> details = mySubscribers.entrySet().collect { e ->
      Topic topic = e.getKey()
      List<MessageBusConnectionImpl> connections = e.getValue();
      connectionsDetails.add(String.format("%s\n%s\n-------------------------------\n%s\n", topic.getDisplayName(), topic.getListenerClass(), connections.collect {
        it.toString()
      }.toSorted(String.CASE_INSENSITIVE_ORDER).join("\n")))
      return String.format("%-70s | %-100s | %5s", topic.getDisplayName(), topic.getListenerClass(), connections.size())
    }.toSorted(String.CASE_INSENSITIVE_ORDER)
    details.add("\nDETAILS\n")
    details.addAll(connectionsDetails)
    Logs.showMessagesInConsole("Subscribers", details)
  }

  static void logLanguages() {
    Logs.showMessagesInConsole(
      "Languages",
      LanguageUtil.getFileLanguages().collect { it.toString() }.toSorted(String.CASE_INSENSITIVE_ORDER)
    )
  }

  static void logPlugins() {
    List<String> plugins = PluginManager.getPlugins().collect { [it.getPluginId().toString(), it.getName()].join(" | ") }.toSorted(String.CASE_INSENSITIVE_ORDER)
    Logs.showMessagesInConsole("Plugins", plugins)
  }

  static void logPluginsJson() {
    List<Map<String, ?>> plugins = PluginManager.getPlugins().collect { it ->
      [
        id     : it.getPluginId().toString(),
        name   : it.getName(),
        version: it.getVersion(),
        enabled: it.isEnabled()
      ]
    }.toSorted { it.get("id").toString().toLowerCase() }
    Logs.showMessagesInConsole("Plugins JSON", [JsonOutput.prettyPrint(JsonOutput.toJson(plugins))], false)
  }

  static void logPluginVersions() {
    List<String> plugins = PluginManager.getPlugins().collect { [it.getPluginId().toString(), it.getName(), it.getVersion()].join(" | ") }.toSorted(String.CASE_INSENSITIVE_ORDER)
    Logs.showMessagesInConsole("Plugin Versions", plugins)
  }

  static void logPluginIDs() {
    List<String> plugins = PluginManager.getPlugins().collect { it.getPluginId().toString() }.toSorted(String.CASE_INSENSITIVE_ORDER)
    Logs.showMessagesInConsole("Plugin IDs", plugins)
  }

  static void logUISettings() {
    PrefsUtil.logUtilClassBooleanSettings(UIUtil.class)
  }

  private static Map<String, List<String>> getFileTypes() {
    def manager = FileTypeManager.getInstance()
    Map<String, List<String>> jsonData = new TreeMap<>(String.CASE_INSENSITIVE_ORDER)
    manager.getRegisteredFileTypes().each { type ->
      def associations = manager.getAssociations(type)
      def matchers = associations.collect { it.presentableString }.toSorted(String.CASE_INSENSITIVE_ORDER)
      jsonData.put(type.name, matchers)
    }
    return jsonData
  }

  static void logFileTypes() {
    def fileTypes = getFileTypes().collect { type, matchers -> "${type}\n${Logs.PRINT_SEP}\n${matchers.join("\n")}\n".toString() }
      .toSorted(String.CASE_INSENSITIVE_ORDER)
    Logs.showMessagesInConsole("File Types", fileTypes)
  }

  static void logFileTypesJson() {
    showInConsole("${JsonOutput.prettyPrint(JsonOutput.toJson(getFileTypes()))}", "File Types JSON", currentProjectInFrame())
  }

  static void showFontSizes() {
    show("Font Sizes<br/>System: ${PrefsUtil.getSystemFontSize()} | Editor: ${PrefsUtil.getEditorFontSize()}")
  }

}