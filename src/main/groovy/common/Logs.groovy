package common

import static liveplugin.PluginUtil.currentProjectInFrame
import static liveplugin.PluginUtil.showInConsole

class Logs {

  static String PRINT_SEP = "---------------------------"

  static void showMessagesInConsole(String title, String[] messages) {
    showMessagesInConsole(title, messages.toList())
  }

  static void showMessagesInConsole(String title, Collection<String> messages) {
    showInConsole("${title}\n${PRINT_SEP}\n${messages.join('\n')}", title, currentProjectInFrame())
  }
}