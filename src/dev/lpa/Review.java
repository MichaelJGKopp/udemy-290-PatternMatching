package dev.lpa;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Review {
  
  public static void main(String[] args) {
  
    String phoneList = """
      (800) 123-4567
      (800)123-4567
      (800) 123 4567
      800-123-4567
      800 123-4567
      800 123 4567
      8001234567
      """;
    
    Pattern phonePattern = Pattern.compile(
      "\\(?[0-9]{3}\\)?[-\\s]?\\d{3}[-\\s]?\\p{Digit}{4}");
    
    Matcher matcher = phonePattern.matcher(phoneList);
    matcher.results()
      .map(MatchResult::group)
            .forEach(System.out::println);
    
//    phoneList.lines()
//      .map(phonePattern::matcher)
//      .filter(Matcher::matches)
//      .map(Matcher::group)
//      .forEach(System.out::println);
    
  
  }
}
