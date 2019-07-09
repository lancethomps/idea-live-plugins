package plugins.copying

import common.Runner

// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

Runner.isIdeStartup = isIdeStartup

[
  new CopyFileNameAction(),
  new CopyPathRelativeToHomeAction(),
  new CopyPathRelativeToModuleAction(),
  new CopyPathRelativeToTopModuleAction()
].each { Runner.registerAction(it, it.getActionText()) }

