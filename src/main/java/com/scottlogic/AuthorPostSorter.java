package com.scottlogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AuthorPostSorter implements PostSorter {
    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder sortOrder) {

        if (inputList == null) {
            return null;
        } else if (inputList.isEmpty()) {
            return inputList;
        }
        List<UserPost> userPosts = new ArrayList<UserPost>(inputList);
        if (sortOrder.equals((sortOrder.ASC))) {
            Collections.sort(userPosts, Comparator.comparing(UserPost::getAuthorSurname));
        } else {
            Collections.sort(userPosts, Comparator.comparing(UserPost::getAuthorSurname).reversed());
        }
        return userPosts;
    }
}
