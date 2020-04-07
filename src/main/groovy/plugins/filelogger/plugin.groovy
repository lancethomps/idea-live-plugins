package plugins.filelogger

import common.Runner

// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

Runner.isIdeStartup = isIdeStartup

Runner.registerAction(new FileLoggerAction(), "Log File Info")

