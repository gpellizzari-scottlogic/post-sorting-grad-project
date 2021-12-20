package com.scottlogic.helpers;

import java.util.Arrays;
import java.util.List;

public class StringCleaner {

    private static final List<String> forbiddenWords = Arrays.asList("is", "as", "and", "to", "for", "it", "its", "the", "that", "we", "ever", "it", "he", "she", "a", "in", "i", "are", "this");
    private static String forbiddenWordsRegex;

    public StringCleaner() {
        forbiddenWordsRegex = "\\s" + forbiddenWords.get(0) + "\\s";

        for (int i = 1; i < forbiddenWords.size(); i++) {
            forbiddenWordsRegex += "|\\s" + forbiddenWords.get(i) + "\\s";
        }
    }

    //method that removes any punctuation or forbiddenWord from the String
    public String cleanString(String string) {
        String cleanedString = string.replaceAll("\\p{Punct}", "");
        cleanedString = cleanedString.toLowerCase();
        cleanedString = cleanedString.replaceAll(forbiddenWordsRegex, " ");

        String previousString = "";
        do {
            previousString = cleanedString;
            cleanedString = cleanedString.replaceAll(forbiddenWordsRegex, " ");
        } while (!cleanedString.equals(previousString));

        return cleanedString;
    }
}
