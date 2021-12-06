package com.scottlogic;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AuthorPostSorter implements PostSorter{
    @Override
    public List<UserPost> sort(List<UserPost> inputList, SortOrder sortOrder) {
        if(inputList==null){
            return null;
        }else if(inputList.isEmpty()){
            return inputList;
        }
        Collections.sort(inputList, Comparator.comparing(UserPost::getAuthor));
        if(sortOrder.equals(SortOrder.DESC)){
            Collections.reverse(inputList);
        }
        return inputList;
    }
}
