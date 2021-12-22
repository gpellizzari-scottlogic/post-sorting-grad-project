package com.scottlogic.sorters;

import com.scottlogic.PostSorter;
import com.scottlogic.SortOrder;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DatePostSorter implements PostSorter {

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder sortOrder) {

        if (inputList == null || inputList.isEmpty()) {
            return new ArrayList<UserPost>();
        }

        List<UserPost> sortedUserPosts;

        sortedUserPosts = switch (sortOrder) {
            case ASC -> inputList.stream()
                    .sorted(Comparator.comparing(UserPost::getDateTime))
                    .collect(Collectors.toList());

            case DESC -> inputList.stream()
                    .sorted(Comparator.comparing(UserPost::getDateTime).reversed())
                    .collect(Collectors.toList());
        };

        return sortedUserPosts;
    }
}
