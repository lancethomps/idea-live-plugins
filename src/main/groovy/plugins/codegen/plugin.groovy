package plugins.codegen

import common.Runner

// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

Runner.isIdeStartup = isIdeStartup

Runner.registerAction(new JavaDataClassGenAction(), "Java Data Class Code Gen - Constructor & Getters")
