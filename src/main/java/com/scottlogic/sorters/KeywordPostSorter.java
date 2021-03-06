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
import java.util.stream.Collectors;

public class KeywordPostSorter implements PostSorter {

    private String keyword;

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public KeywordPostSorter(String keyword) {
        this.keyword = keyword;
    }

    private int getNumberOfKeywords(UserPost userPost) {
        int numberOfKeywords = 0;

        String regex = this.keyword.toLowerCase();
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(userPost.getContents().toLowerCase());
        while (matcher.find()) {
            numberOfKeywords++;
        }
        return numberOfKeywords;
    }

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder sortOrder) {

        if (inputList == null || inputList.isEmpty()) {
            return new ArrayList<UserPost>();
        }

        return sortOrder == SortOrder.ASC
                ? inputList.stream()
                    .sorted(Comparator.comparingInt(o -> getNumberOfKeywords(o)))
                    .collect(Collectors.toList())
                : inputList.stream()
                    .sorted(Collections.reverseOrder(Comparator.comparingInt(o -> getNumberOfKeywords(o))))
                    .collect(Collectors.toList());
    }
}
