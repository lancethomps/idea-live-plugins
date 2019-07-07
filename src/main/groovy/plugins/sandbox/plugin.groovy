package plugins.sandbox

import common.Runner
// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

Runner.isIdeStartup = isIdeStartup

if (isIdeStartup) return

LivePluginSandbox.test()
