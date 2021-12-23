package com.scottlogic.filters;

import com.scottlogic.PostFilter;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        List<UserPost> list1 = postFilter1.filter(inputList);

        return postFilter2.filter(inputList).stream()
                .filter(list1::contains)
                .collect(Collectors.toList());
    }
}
