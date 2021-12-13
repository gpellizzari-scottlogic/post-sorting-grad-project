package com.scottlogic.filters;

import com.scottlogic.PostFilter;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        List<UserPost> filteredList = new ArrayList<UserPost>();

        if (inputList == null || inputList.isEmpty() || authorToFilter.isEmpty()) {
            return new ArrayList<UserPost>();
        }

        for (UserPost userPost : inputList) {
            if (userPost.getAuthor().equalsIgnoreCase(this.authorToFilter)) {
                filteredList.add(userPost);
            }
        }
        return filteredList;
    }
}
