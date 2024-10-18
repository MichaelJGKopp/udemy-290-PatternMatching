package dev.lpa;

import java.util.regex.Matcher;
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
    System.out.println("Matched on : " + sentence.substring(matcher.start(), matcher.end()));
    System.out.println("Matched on : " + matcher.group() + "\n");  // returns subsequence of prev
    // . match
    
    String htmlSnippet = """
      <H1>My Heading</H1>
      <h2>Sub-heading</h2>
      <p>This is a paragraph about something.</p>
      <p>This is another paragraph about something else.</p>
      <h3>Summary</h3>
      """;
    
    Pattern htmlPattern = Pattern.compile("<(?<level>[hH]\\d)>(.*)</[hH]\\d>");
    Matcher htmlMatcher = htmlPattern.matcher(htmlSnippet);
    
    while (htmlMatcher.find()) {
      System.out.println("Group: " + htmlMatcher.group());
      System.out.println("Group (0):" + htmlMatcher.group(0));
      System.out.println("Group (1): " + htmlMatcher.group("level")); // named-capturing group
      System.out.println("Group (2): " + htmlMatcher.group(2)); // capturing group 2
      System.out.println("index = " + htmlMatcher.start("level"));
      System.out.println();
    }
    System.out.println("------------------------");
    
    htmlMatcher.reset();
    htmlMatcher.results()
      .forEach(mr -> System.out.println(mr.group(1) + " " + mr.group(2)));
    System.out.println("------------------------");
    
    String tabbedText = """
      group1\tgroup2\tgroup3
      1\t2\t3
      a\tb\tc
      """;
    
    tabbedText.lines()
      .flatMap(s -> Pattern.compile("\\t").splitAsStream(s))
      .forEach(System.out::println);
    System.out.println("========================\n");
    
    htmlMatcher.reset();  // not necessary
    String updatedSnippet = htmlMatcher.replaceFirst((mr) ->
                                                       "<em>" + mr.group(2) + "</em>");
    System.out.println("-----------------------");
    System.out.println(updatedSnippet);
    System.out.println("Start " + htmlMatcher.start() + " : End " + htmlMatcher.end());
    System.out.println("Start to End: " + htmlSnippet.substring(htmlMatcher.start(),
      htmlMatcher.end()));
    System.out.println("Group 2: " + htmlMatcher.group(2));
    System.out.println("-----------------------");
    
    htmlMatcher.usePattern(Pattern.compile("<([hH]\\d)>(.*)</\\1>")); // back reference
    
    htmlMatcher.reset();
    System.out.println("Using Back Reference: \n" +
                         htmlMatcher.replaceFirst("<em>$2</em>"));
    System.out.println("------------------------");
    
    String replacedHTML = htmlMatcher.replaceAll((mr) -> "<em>" + mr.group(2) + "</em>");
    System.out.println(replacedHTML);
    
    htmlMatcher.reset();
    StringBuilder sb = new StringBuilder();
    int index = 1;
    while (htmlMatcher.find()) {
      htmlMatcher.appendReplacement(sb,
        switch(htmlMatcher.group(1).toLowerCase()) {
        case "h1" -> "<head>$2</head>";
        case "h2" -> "<em>$2</em>";
        default ->  "<$1>" + index++ + ". $2</$1>";
        });
    }
    htmlMatcher.appendTail(sb);
    System.out.println(sb);
  }
}
