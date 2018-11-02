package common

import java.util.regex.Pattern

class Strings {

  private static final Pattern HUMAN_CASE_REGEX = Pattern.compile("(.)(\\p{Lu})");

  static String toHumanCase(String str) {
    return HUMAN_CASE_REGEX.matcher(str.capitalize()).replaceAll('$1 $2');
  }
}
