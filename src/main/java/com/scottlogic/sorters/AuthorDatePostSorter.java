package com.scottlogic.sorters;

import com.scottlogic.PostSorter;
import com.scottlogic.SortOrder;
import com.scottlogic.UserPost;
import com.scottlogic.filters.AuthorPostFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthorDatePostSorter implements PostSorter {

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder sortOrder) {
        List<UserPost> sortedList = null;

        if (inputList == null || inputList.isEmpty()) {
            return new ArrayList<UserPost>();
        }

        List<UserPost> listSortedByAuthor = new AuthorPostSorter().sort(inputList, sortOrder);
        String previousName = null;

        for (UserPost userPost : listSortedByAuthor) {
            if (!userPost.getAuthor().equals(previousName)) {
                List<UserPost> tempList = new AuthorPostFilter(userPost.getAuthor()).filter(listSortedByAuthor);
                sortedList.addAll(new DatePostSorter().sort(tempList, SortOrder.ASC));
                previousName = userPost.getAuthor();
            }
        }
        return sortedList;
    }
}
