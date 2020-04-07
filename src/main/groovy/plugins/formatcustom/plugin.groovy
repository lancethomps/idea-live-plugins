package plugins.formatcustom

import common.Runner

// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

Runner.isIdeStartup = isIdeStartup

Runner.registerAction(new FormatCustomAction(), "Format Custom - ReformatCode, RearrangeCode", "meta shift E")
Runner.registerAction(new FormatCustomWithoutKeepingLineBreaksAction(), "Format Custom Without Keeping Line Breaks")
Runner.registerAction(new UpdateJavaStyleKeepLineBreaksAction(false), "keep line breaks when formatting Java code: OFF", "", "UpdateJavaStyleKeepLineBreaksActionFALSE")
Runner.registerAction(new UpdateJavaStyleKeepLineBreaksAction(true), "keep line breaks when formatting Java code", "", "UpdateJavaStyleKeepLineBreaksActionTRUE")
