package plugins.formatcustom

import common.Runner

// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

Runner.isIdeStartup = isIdeStartup

Runner.registerAction(new FormatCustomAction(), "Format Custom - ReformatCode, RearrangeCode", "meta shift E")
