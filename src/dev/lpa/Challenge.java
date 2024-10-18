package dev.lpa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Challenge {
  
  public static void main(String[] args) {
  
    String emails = """
      john.boy@valid.com
      john.boy@invalid
      jane.doe-smith@valid.co.uk
      jane_Doe1976@valid.co.uk
      bob-1964@valid.net
      bob!@invalid.com
      elaine@valid-test.com.au
      elaineinvalid1983@.com
      david@valid.io
      david@invalid..com
      """;
    
    Pattern pattern = Pattern.compile("([\\w.-]+)@(([\\w-]+[.])+[\\w-]{2,})");
    Matcher matcher = pattern.matcher(emails);
    
    matcher.results()
      .forEach(mr -> System.out.printf(
             "Email: %-30s \tUsername: %-20s \tDomain: %-20s%n",
             mr.group(0), mr.group(1), mr.group(2)));
    System.out.println("-----------------------------------------");
    
    Pattern emailPattern = Pattern.compile("([\\w.-]+)@(([\\w-]+[.])+[\\w-]{2,})");
    String[] emailSamples = emails.lines().toArray(String[]::new);
    for (String email: emailSamples) {
      Matcher eMatcher = emailPattern.matcher(email);
      boolean matched = eMatcher.matches();
      System.out.println(email + " is " + (matched ? "VALID" : "INVALID"));
      if (matched) {
        System.out.printf("[username=%s domain=%s]%n", eMatcher.group(1), eMatcher.group(2));
      } else {
        System.out.println();
      }
    }
  }
}
