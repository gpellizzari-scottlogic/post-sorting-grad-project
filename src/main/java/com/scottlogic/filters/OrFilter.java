package com.scottlogic.filters;

import com.scottlogic.PostFilter;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.List;

public class OrFilter implements PostFilter {

    PostFilter postFilter1;
    PostFilter postFilter2;

    public OrFilter(PostFilter postFilter1, PostFilter postFilter2) {
        this.postFilter1 = postFilter1;
        this.postFilter2 = postFilter2;
    }

    @Override
    public List<UserPost> filter(List<UserPost> inputList) {
        if (inputList == null) {
            return null;
        } else if (inputList.isEmpty()) {
            return new ArrayList<UserPost>();
        }
        List<UserPost> combinedFilter = postFilter1.filter(inputList);
        List<UserPost> filteredList2 = postFilter2.filter(inputList);
        for (UserPost userPost : filteredList2) {
            if (!combinedFilter.contains(userPost)) {
                combinedFilter.add(userPost);
            }
        }
        return combinedFilter;
    }
}
