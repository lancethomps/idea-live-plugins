package plugins.startup

import common.Runner
import liveplugin.PluginUtil

// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

Runner.isIdeStartup = isIdeStartup

def removeExtraDebuggerKotlinFiltersAction = new RemoveExtraDebuggerKotlinFiltersAction()
Runner.registerAction(removeExtraDebuggerKotlinFiltersAction, "Remove Extra Debugger Kotlin Filters")

if (isIdeStartup) {
  RemoveExtraDebuggerKotlinFiltersAction.logExistingKotlinFiltersCount(false)
  PluginUtil.actionById(Runner.createActionId(removeExtraDebuggerKotlinFiltersAction)).actionPerformed(PluginUtil.anActionEvent())
}
