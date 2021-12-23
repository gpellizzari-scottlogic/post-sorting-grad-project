package com.scottlogic.filters;

import com.scottlogic.PostFilter;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class KeywordPostFilter implements PostFilter {

    private Pattern pattern;
    private String keyword;

    public KeywordPostFilter(String keyword) {
        this.pattern = Pattern.compile("\\b" + keyword.toLowerCase() + "\\b");
        this.keyword = keyword;
    }

    private boolean matchKeyword(String content) {
        Matcher matcher = pattern.matcher(content.toLowerCase());
        return matcher.find();
    }

    @Override
    public List<UserPost> filter(List<UserPost> inputList) {

        if (inputList == null || inputList.isEmpty() || keyword.length() < 1) {
            return new ArrayList<UserPost>();
        }

        return inputList.stream()
                .filter(u -> matchKeyword(u.getContents()))
                .collect(Collectors.toList());
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.pattern = Pattern.compile("\\b" + keyword.toLowerCase() + "\\b");
        this.keyword = keyword;
    }
}
