package common

import static liveplugin.PluginUtil.currentProjectInFrame
import static liveplugin.PluginUtil.showInConsole

import com.intellij.notification.NotificationType

import liveplugin.PluginUtil

class Logs {

  static String PRINT_SEP = "---------------------------"

  static void log(Object message, NotificationType notificationType = null) {
    if (notificationType != null) {
      PluginUtil.log(message, notificationType)
    } else {
      PluginUtil.log(message)
    }
  }

  static void logOrShow(Object message, boolean shouldShow) {
    if (shouldShow) {
      show(message)
    } else {
      log(message)
    }
  }

  static void show(Object message) {
    PluginUtil.show(message)
  }

  static void showMessagesInConsole(String title, String[] messages) {
    showMessagesInConsole(title, messages.toList())
  }

  static void showMessagesInConsole(String title, Collection<String> messages) {
    showMessagesInConsole(title, messages, true)
  }

  static void showMessagesInConsole(String title, Collection<String> messages, boolean includeTitleInMessage) {
    String message = includeTitleInMessage ? "${title}\n${PRINT_SEP}\n${messages.join('\n')}" : messages.join('\n')
    showInConsole(message, title, currentProjectInFrame())
  }
}