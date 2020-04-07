package plugins.configlogger

import static liveplugin.PluginUtil.show

import java.lang.reflect.Field
import java.util.concurrent.ConcurrentMap

import com.intellij.ide.plugins.PluginManager
import com.intellij.lang.LanguageUtil
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.application.ApplicationManager
import com.intellij.util.messages.Topic
import com.intellij.util.messages.impl.MessageBusConnectionImpl
import com.intellij.util.messages.impl.MessageBusImpl
import com.intellij.util.ui.UIUtil

import common.Logs
import common.PrefsUtil

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
    List<String> pluginIds = PluginManager.getPlugins().collect { it.getPluginId().toString() }.toSorted(String.CASE_INSENSITIVE_ORDER)
    Logs.showMessagesInConsole("Plugin IDs", pluginIds)
  }

  static void logUISettings() {
    PrefsUtil.logUtilClassBooleanSettings(UIUtil.class)
  }

  static void showFontSizes() {
    show("Font Sizes<br/>System: ${PrefsUtil.getSystemFontSize()} | Editor: ${PrefsUtil.getEditorFontSize()}")
  }
}