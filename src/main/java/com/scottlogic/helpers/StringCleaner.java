package com.scottlogic.helpers;

import java.util.Arrays;
import java.util.List;

public class StringCleaner {

    private static final List<String> forbiddenWords = Arrays.asList("is", "as", "and", "to", "for", "it", "its", "the", "that", "we", "ever", "it", "he", "she", "a", "in", "i", "are", "this", "im", "there");
    private static String forbiddenWordsRegex;

    public StringCleaner() {
        forbiddenWordsRegex = "[ ]{2,}|^\\s|\\s$";

        for (int i = 0; i < forbiddenWords.size(); i++) {
            forbiddenWordsRegex += "|\\b" + forbiddenWords.get(i) + "\\b\\s";
        }

        System.out.println(forbiddenWordsRegex);
    }

    //method that removes any punctuation or forbiddenWord from the String
    public String cleanString(String string) {

        if(string == null){
            return "";
        }

        String cleanedString = string.replaceAll("\\p{Punct}", "");
        cleanedString = cleanedString.toLowerCase();

        String previousString = "";
        do {
            previousString = cleanedString;
            cleanedString = cleanedString.replaceAll(forbiddenWordsRegex, "");
        } while (!cleanedString.equals(previousString));

        return cleanedString;
    }
}