package com.scottlogic.sorters;

import com.scottlogic.PostSorter;
import com.scottlogic.SortOrder;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DatePostSorter implements PostSorter {

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder sortOrder) {

        if (inputList == null || inputList.isEmpty()) {
            return new ArrayList<UserPost>();
        }

        List<UserPost> sortedUserPosts = new ArrayList<UserPost>(inputList) ;

        if (sortOrder.equals(SortOrder.ASC)) {
            Collections.sort(sortedUserPosts, Comparator.comparing(UserPost::getDateTime));
        } else {
            Collections.sort(sortedUserPosts, Comparator.comparing(UserPost::getDateTime).reversed());
        }
        return sortedUserPosts;
    }
}
