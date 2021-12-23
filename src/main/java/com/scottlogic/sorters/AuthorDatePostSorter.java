package com.scottlogic.sorters;

import com.scottlogic.PostSorter;
import com.scottlogic.SortOrder;
import com.scottlogic.UserPost;
import com.scottlogic.filters.AuthorPostFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorDatePostSorter implements PostSorter {

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder sortOrder) {

        if (inputList == null || inputList.isEmpty()) {
            return new ArrayList<UserPost>();
        }

        return sortOrder == SortOrder.ASC
                ? inputList.stream()
                    .sorted(Comparator.comparing(UserPost::getAuthorSurname).thenComparing(UserPost::getDateTime))
                    .collect(Collectors.toList())
                : inputList.stream()
                    .sorted(Comparator.comparing(UserPost::getAuthorSurname).reversed().thenComparing(UserPost::getDateTime))
                    .collect(Collectors.toList());
    }
}
