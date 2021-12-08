package com.scottlogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LengthPostSorter implements PostSorter {

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder sortOrder) {
        if (inputList == null) {
            return null;
        } else if (inputList.isEmpty()) {
            return inputList;
        }
        List<UserPost> sortedUserPosts = new ArrayList<UserPost>(inputList);
        if (sortOrder.equals(SortOrder.ASC)) {
            Collections.sort(sortedUserPosts, Comparator.comparingInt(o -> o.getContents().length()));
        } else {
            Collections.sort(sortedUserPosts, Collections.reverseOrder(Comparator.comparingInt(o -> o.getContents().length())));
        }
        return sortedUserPosts;
    }
}
