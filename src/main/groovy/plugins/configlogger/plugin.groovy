package plugins.configlogger

import java.lang.reflect.Modifier

import common.Runner
import common.Strings

// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

Runner.isIdeStartup = isIdeStartup

ConfigLogger.class.getDeclaredMethods().findAll { method -> (method.getName().startsWith("log") || method.getName().startsWith("show")) && Modifier.isStatic(method.getModifiers()) }.each { method ->
  def id = method.getName().capitalize()
  def displayText = Strings.toHumanCase(method.getName())
  Runner.registerAction({ e ->
    method.invoke(null)
  }, displayText, "", id)
}
