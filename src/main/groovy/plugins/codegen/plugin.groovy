package plugins.codegen

import common.Runner
import plugins.codegen.all.AllGenerateConstructorAction
import plugins.codegen.all.AllGenerateGetterAction

// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

Runner.isIdeStartup = isIdeStartup

Runner.registerAction(new AllGenerateConstructorAction(), "Java Data Class Code Gen - Constructor", "meta shift C")
Runner.registerAction(new AllGenerateGetterAction(), "Java Data Class Code Gen - Getters", "meta shift G")
Runner.registerAction(new JavaDataClassGenAction(), "Java Data Class Code Gen - Constructor & Getters")
