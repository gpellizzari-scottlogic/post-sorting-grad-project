package com.scottlogic.filters;

import com.scottlogic.PostFilter;
import com.scottlogic.UserPost;

import java.util.ArrayList;
import java.util.List;

public class LikePostFilter implements PostFilter {

    private boolean isLikedPosts; //boolean that states if to search for the liked or not liked posts

    public LikePostFilter(boolean isLikedPosts) {
        this.isLikedPosts = isLikedPosts;
    }

    @Override
    public List<UserPost> filter(List<UserPost> inputList) {
        List<UserPost> filteredList = new ArrayList<UserPost>();

        if (inputList == null || inputList.isEmpty()) {
            return new ArrayList<UserPost>();
        }

        for (UserPost userPost : inputList) {
            if (isLikedPosts) {
                if (userPost.getLikeCount() > 0) {
                    filteredList.add(userPost);
                }
            } else {
                if (userPost.getLikeCount() == 0) {
                    filteredList.add(userPost);
                }
            }
        }
        return filteredList;
    }

    public void setLikedPosts(boolean likedPosts) {
        isLikedPosts = likedPosts;
    }
}
