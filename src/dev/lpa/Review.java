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
    
    System.out.println("--------------------------------------");
    String htmlSnippet = """
      <H1>My Heading</h1>
      <h2>Sub-heading</h2>
      <p>This is a paragraph about something.</p>
      <p style = "abc">This is another paragraph about something else.</p>
      <h3 id ="third">Summary</h3>
      <br/>
      <p>Testing</p>
      """;
    
//    Pattern htmlPattern = Pattern.compile(
//      "<(\\w+)[^>]*>(?<text>[^\\v</>]*)((?i)</\\1>)*"); // (?i) in group ignore case
     Pattern htmlPattern = Pattern.compile(
      "<(\\w+)[^>]*>(?<text>[^\\v</>]*)(</\\1>)*", Pattern.CASE_INSENSITIVE);
    
    htmlPattern.matcher(htmlSnippet)
      .results()
      .filter(mr -> mr.group(1).toLowerCase().startsWith("h"))
      .map(mr ->
             "Full text: " + mr.group(0) +
             "\n\t Type: " + mr.group(1) +
             "\n\t Text: " + mr.group("text"))
      .forEach(System.out::println);
  }
}
