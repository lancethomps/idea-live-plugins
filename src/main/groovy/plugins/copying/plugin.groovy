package plugins.copying

import common.Runner

// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

Runner.isIdeStartup = isIdeStartup

Runner.registerAction(new CopyFileNameAction(), "Copy File Name")
Runner.registerAction(new CopyPathRelativeToHomeAction(), "Copy Path Relative To Home Directory")
