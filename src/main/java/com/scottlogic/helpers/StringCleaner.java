package com.scottlogic.helpers;

import java.util.Arrays;
import java.util.List;

public class StringCleaner {

    private static final List<String> forbiddenWords = Arrays.asList("is", "as", "and", "to", "for", "it", "its", "the", "that", "we", "ever", "it", "he", "she", "a", "in", "are", "this", "im","i", "there");
    private static String forbiddenWordsRegex;

    public StringCleaner() {
        forbiddenWordsRegex = "\\b" + forbiddenWords.get(0) + "\\b";

        for (int i = 1; i < forbiddenWords.size(); i++) {
            forbiddenWordsRegex += "|\\b" + forbiddenWords.get(i) + "\\b";
        }
    }

    //method that removes any punctuation or forbiddenWord from the String
    public String cleanString(String string) {

        if(string == null){
            return "";
        }

        return string.replaceAll("\\p{Punct}", "")
                .toLowerCase()
                .replaceAll(forbiddenWordsRegex, "")
                .replaceAll("[ ]{2,}", " ")
                .trim();
    }
}