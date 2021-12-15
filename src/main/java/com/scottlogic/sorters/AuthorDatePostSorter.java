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
        List<UserPost> outputList = new ArrayList<UserPost>();

        if (inputList == null || inputList.isEmpty()) {
            return outputList;
        }

        List<UserPost> listSortedByAuthor = new AuthorPostSorter().sort(inputList, sortOrder);
        String previousName = null;

        for (UserPost userPost : listSortedByAuthor) {
            if (!userPost.getAuthor().equals(previousName)) {
                //add to the output list some posts filtered by name
                List<UserPost> tempList = new AuthorPostFilter(userPost.getAuthor()).filter(listSortedByAuthor);
                outputList.addAll(new DatePostSorter().sort(tempList, SortOrder.ASC));
                previousName = userPost.getAuthor();
            }
        }
        return outputList;
    }
}
