package com.scottlogic.sorters;

import com.scottlogic.PostSorter;
import com.scottlogic.SortOrder;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorPostSorter implements PostSorter {
    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder sortOrder) {

        if (inputList == null || inputList.isEmpty()) {
            return new ArrayList<UserPost>();
        }

        return sortOrder == SortOrder.ASC
                ? inputList.stream()
                    .sorted(Comparator.comparing(UserPost::getAuthorSurname))
                    .collect(Collectors.toList())
                : inputList.stream()
                    .sorted(Comparator.comparing(UserPost::getAuthorSurname).reversed())
                    .collect(Collectors.toList());
    }
}
