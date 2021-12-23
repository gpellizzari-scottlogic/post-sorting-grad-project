package com.scottlogic.filters;

import com.scottlogic.PostFilter;
import com.scottlogic.SortOrder;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LikePostFilter implements PostFilter {

    private boolean isLikedPosts; //boolean that states if to search for the liked or not liked posts

    public LikePostFilter(boolean isLikedPosts) {
        this.isLikedPosts = isLikedPosts;
    }

    @Override
    public List<UserPost> filter(List<UserPost> inputList) {

        if (inputList == null || inputList.isEmpty()) {
            return new ArrayList<UserPost>();
        }

        return isLikedPosts
                ? inputList.stream()
                    .filter(u -> u.getLikeCount() > 0)
                    .collect(Collectors.toList())
                : inputList.stream()
                    .filter(u -> u.getLikeCount() == 0)
                    .collect(Collectors.toList());
    }

    public void setLikedPosts(boolean likedPosts) {
        isLikedPosts = likedPosts;
    }
}
