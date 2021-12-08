package com.scottlogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LikePostSorter implements PostSorter {

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder sortOrder) {
        if (inputList == null) {
            return null;
        } else if (inputList.isEmpty()) {
            return inputList;
        }
        List<UserPost> sortedUserPosts = new ArrayList<UserPost>(inputList);
        if (sortOrder.equals(SortOrder.ASC)) {
            Collections.sort(sortedUserPosts, Comparator.comparing(UserPost::getLikeCount));
        } else {
            Collections.sort(sortedUserPosts, Comparator.comparing(UserPost::getLikeCount).reversed());
        }
        return sortedUserPosts;
    }
}
