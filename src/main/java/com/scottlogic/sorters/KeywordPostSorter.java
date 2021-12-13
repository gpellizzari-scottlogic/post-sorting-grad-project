package com.scottlogic.sorters;

import com.scottlogic.PostSorter;
import com.scottlogic.SortOrder;
import com.scottlogic.UserPost;
import com.scottlogic.filters.KeywordPostFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeywordPostSorter implements PostSorter {

    private String keyword;

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public KeywordPostSorter(String keyword) {
        this.keyword = keyword;
    }

    //TODO: modify so that it matches pural words e.g. example has to match examples
    private int getNumberOfKeywords(UserPost userPost) {
        int numberOfKeywords = 0;
        String[] wordsInPostContent = userPost.getContents().split(" ");

        String regex = "\\b" + this.keyword.toLowerCase() + "\\b";
        Pattern pattern = Pattern.compile(regex);

        for (String word : wordsInPostContent) {
            Matcher matcher = pattern.matcher(word.toLowerCase());
            if (matcher.find()) {
                numberOfKeywords++;
            }
        }
        return numberOfKeywords;
    }

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder sortOrder) {
        List<UserPost> outputList = new ArrayList<UserPost>();

        if (inputList == null || inputList.isEmpty() || this.keyword.length() < 1) {
            return outputList;
        }

        outputList = new KeywordPostFilter(this.keyword).filter(inputList);

        if (sortOrder.equals(SortOrder.ASC)) {
            Collections.sort(outputList, Comparator.comparingInt(o -> getNumberOfKeywords(o)));
        } else {
            Collections.sort(outputList, Collections.reverseOrder(Comparator.comparingInt(o -> getNumberOfKeywords(o))));
        }

        return outputList;
    }
}
