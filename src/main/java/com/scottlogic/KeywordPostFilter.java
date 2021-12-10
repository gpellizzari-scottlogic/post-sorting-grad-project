package com.scottlogic;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeywordPostFilter implements PostFilter {

    private String keyword;

    public KeywordPostFilter(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public List<UserPost> filter(List<UserPost> inputList) {
        List<UserPost> filteredList = new ArrayList<UserPost>();
        if (inputList == null) {
            return null;
        } else if (inputList.isEmpty() || keyword.length() < 1) {
            return new ArrayList<UserPost>();
        }
        String regex = "\\b" + this.keyword.toLowerCase() + "\\b";
        Pattern pattern = Pattern.compile(regex);

        for (UserPost userPost : inputList) {
            Matcher matcher = pattern.matcher(userPost.getContents().toLowerCase());
            if (matcher.find()) {
                filteredList.add(userPost);
            }
        }
        return filteredList;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


}
