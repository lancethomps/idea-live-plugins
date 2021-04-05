package plugins.fontsizeupdater

import common.Runner

// add-to-classpath $HOME/github/idea-live-plugins/src/main/groovy

Runner.isIdeStartup = isIdeStartup

static def registerUpdateFontSize(String type, int fontSize) {
  def actionId = String.format("UpdateFontSize%s", type.replaceAll(" ", ""))
  def displayText = String.format("Update Font Size - %s (%s)", type, fontSize);
  Runner.registerAction(new UpdateFontSizeAction(fontSize), displayText, "", actionId)
}

registerUpdateFontSize("Extra Small", 7)
registerUpdateFontSize("Small", 9)
registerUpdateFontSize("Medium", 10)
registerUpdateFontSize("Large", 11)
registerUpdateFontSize("Extra Large", 12)
