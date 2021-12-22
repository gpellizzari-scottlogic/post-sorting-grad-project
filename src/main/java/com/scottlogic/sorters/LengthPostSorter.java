package com.scottlogic.sorters;

import com.scottlogic.PostSorter;
import com.scottlogic.SortOrder;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LengthPostSorter implements PostSorter {

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder sortOrder) {

        List<UserPost> sortedUserPosts = new ArrayList<UserPost>();

        if (inputList == null || inputList.isEmpty()) {
            return sortedUserPosts;
        }

        sortedUserPosts = switch (sortOrder) {
            case ASC -> inputList.stream()
                    .sorted(Comparator.comparingInt(o -> o.getContents().length()))
                    .collect(Collectors.toList());
            case DESC -> inputList.stream()
                    .sorted(Collections.reverseOrder(Comparator.comparingInt(o -> o.getContents().length())))
                    .collect(Collectors.toList());
        };

        return sortedUserPosts;
    }
}