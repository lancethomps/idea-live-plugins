package plugins.startup


import com.intellij.openapi.application.ApplicationManager

import common.Runner

// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

Runner.isIdeStartup = isIdeStartup

def removeExtraDebuggerKotlinFiltersAction = new RemoveExtraDebuggerKotlinFiltersAction()
Runner.registerAction(removeExtraDebuggerKotlinFiltersAction, "Remove Extra Debugger Kotlin Filters")

if (isIdeStartup) {
  ApplicationManager.getApplication().invokeLaterOnWriteThread(new Runnable() {

    @Override
    void run() {
      RemoveExtraDebuggerKotlinFiltersAction.logExistingKotlinFiltersCount(false)
      RemoveExtraDebuggerKotlinFiltersAction.run()
    }
  })
}
