package com.scottlogic.filters;

import com.scottlogic.PostFilter;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AuthorPostFilter implements PostFilter {

    private String authorToFilter;

    public String getAuthorToFilter() {
        return authorToFilter;
    }

    public void setAuthorToFilter(String authorToFilter) {
        this.authorToFilter = authorToFilter;
    }

    public AuthorPostFilter(String authorToFilter) {
        this.authorToFilter = authorToFilter;
    }

    @Override
    public List<UserPost> filter(List<UserPost> inputList) {

        if (inputList == null || inputList.isEmpty()) {
            return new ArrayList<UserPost>();
        }

        return inputList.stream()
                .filter(u -> u.getAuthor().equalsIgnoreCase(this.authorToFilter))
                .collect(Collectors.toList());
    }
}
