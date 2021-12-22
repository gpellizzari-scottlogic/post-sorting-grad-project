package com.scottlogic.sorters;

import com.scottlogic.*;
import com.scottlogic.helpers.StringCleaner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TopicPostSorter implements PostSorter {

    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder sortOrder) {

        if (inputList == null || inputList.isEmpty()) {
            return new ArrayList<UserPost>();
        }

        List<UserPost> sortedUserPosts = new ArrayList<UserPost>(inputList);
        StringCleaner stringCleaner = new StringCleaner();

        if (sortOrder.equals(SortOrder.ASC)) {
            Collections.sort(sortedUserPosts,
                    Comparator.comparing(o -> new TopicExtractor(stringCleaner).ExtractMainTopic(o)));
        } else {
            Collections.sort(sortedUserPosts,
                    Collections.reverseOrder(Comparator.comparing(o -> new TopicExtractor(stringCleaner).ExtractMainTopic(o))));
        }

        return sortedUserPosts;
    }
}
