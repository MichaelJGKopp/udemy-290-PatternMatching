package dev.lpa;

import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {

    String sentence = "I like B.M.W. motorcycles.";
    boolean matched = Pattern.matches("[A-z].*[.]", sentence);
    System.out.println(matched + ": " + sentence);

    Pattern firstPattern = Pattern.compile("[A-Z].*?[.]"); // ? shortest possible solution
    var matcher = firstPattern.matcher(sentence);
    matched = matcher.matches();
    System.out.println(matched + ": " + sentence + "\n");

    System.out.println("sentence.length: " + sentence.length());
    System.out.println("Matched Ending Index: " + matcher.end() + "\n");

    System.out.println(matcher.lookingAt() + ": " + sentence);  // doesn't match entire string,
    // starts with beginning, but open end
    System.out.println("Matched Ending Index: " + matcher.end() + "\n");
    System.out.println("Matched on : " + sentence.substring(0, matcher.end()) + "\n");

    matcher.reset();  // since find starts where the matcher ended before
    System.out.println(matcher.find() + ": " + sentence);
    System.out.println("Matched Ending Index: " + matcher.end());
    System.out.println("Matched on : " +
      sentence.substring(matcher.start(), matcher.end()));
  }
}
