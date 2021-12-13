package com.scottlogic.filters;

import com.scottlogic.PostFilter;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.List;

public class AndFilter implements PostFilter {

    PostFilter postFilter1;
    PostFilter postFilter2;

    public AndFilter(PostFilter postFilter1, PostFilter postFilter2) {
        this.postFilter1 = postFilter1;
        this.postFilter2 = postFilter2;
    }

    @Override
    public List<UserPost> filter(List<UserPost> inputList) {
        if (inputList == null || inputList.isEmpty()) {
            return new ArrayList<UserPost>();
        }

        List<UserPost> filteredList1 = postFilter1.filter(inputList);
        List<UserPost> filteredList2 = postFilter2.filter(inputList);
        filteredList1.retainAll(filteredList2);
        return filteredList1;
    }
}
