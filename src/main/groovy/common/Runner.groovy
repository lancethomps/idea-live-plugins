package common

import static liveplugin.PluginUtil.registerAction
import static liveplugin.PluginUtil.show

import java.util.regex.Pattern

import com.intellij.openapi.actionSystem.AnAction

class Runner {

  static boolean isIdeStartup

  static String createActionId(AnAction action) {
    return action.getClass().getSimpleName().replaceAll(Pattern.compile('Action$'), '')
  }

  static AnAction registerAction(Closure action, String displayText, String keyStroke = "", String actionId = displayText, String actionGroupId = null) {
    registerAction(actionId, keyStroke, actionGroupId, displayText, action)
    if (!isIdeStartup) show(String.format("Loaded %s<br/>%s<br/>Run it through the actions search.", actionId, displayText))
    return action
  }

  static AnAction registerAction(AnAction action, String displayText, String keyStroke = "", String actionId = createActionId(action), String actionGroupId = null) {
    registerAction(actionId, keyStroke, actionGroupId, displayText, action)
    if (!isIdeStartup) show(String.format("Loaded %s<br/>%s<br/>Run it through the actions search.", actionId, displayText))
    return action
  }

}



