package com.example.diaryproject.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {
    public static boolean isValidEmail(String email) {

        String emailPattern =
                "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return isValidEmail(emailAddress) == regexPattern.matches(emailAddress);
    }
}