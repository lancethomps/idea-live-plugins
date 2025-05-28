package plugins.collapsecustom

import common.Runner

// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

Runner.isIdeStartup = isIdeStartup

[
  new CollapseAllThenExpandAction(1),
  new CollapseAllThenExpandAction(2),
  new CollapseAllThenExpandAction(3),
  new CollapseAllThenExpandAction(4),
  new CollapseAllThenExpandAction(5),
].each { Runner.registerAction(it, it.getActionText(), "", it.getActionText().replace(" ", "")) }
