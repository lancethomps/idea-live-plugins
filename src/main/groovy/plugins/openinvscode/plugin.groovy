package plugins.openinvscode

import common.Runner
// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

Runner.isIdeStartup = isIdeStartup

Runner.registerAction(new OpenInVSCodeAction(), "Open File in Visual Studio Code (VSCode)")

