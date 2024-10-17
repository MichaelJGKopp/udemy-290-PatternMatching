package dev.lpa;

import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {

    String sentence = "I like motorcycles.";
    boolean matched = Pattern.matches("[A-z].*[.]", sentence);
    System.out.println(matched + ": " + sentence);

    Pattern firstPattern = Pattern.compile("[A-Z].*[.]");
    var matcher = firstPattern.matcher(sentence);
    matched = matcher.matches();
    System.out.println(matched + ": " + sentence + "\n");

    System.out.println("sentence.length: " + sentence.length());
    System.out.println("Matched Ending Index: " + matcher.end() + "\n");

    System.out.println(matcher.lookingAt() + ": " + sentence);  // doesn't match entire string
    System.out.println("Matched Ending Index: " + matcher.end() + "\n");
  }
}
