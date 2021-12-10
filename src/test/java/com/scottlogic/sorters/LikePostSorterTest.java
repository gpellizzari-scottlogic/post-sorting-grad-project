package com.scottlogic.sorters;

import com.scottlogic.SortOrder;
import com.scottlogic.UserPost;
import com.scottlogic.sorters.LikePostSorter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

class LikePostSorterTest {

    UserPost userPost1 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "Hello World!", 2);

    UserPost userPost2 = new UserPost("Alreen Bloggs",
            OffsetDateTime.of(2020, 1, 3, 8, 53, 34, 0, ZoneOffset.UTC),
            "Another example post.", 1);

    UserPost userPost3 = new UserPost("Jane Smith",
            OffsetDateTime.of(2021, 3, 12, 13, 22, 12, 0, ZoneOffset.UTC),
            "An example of a post \nwith lines breaks.", 3);

    UserPost userPost4 = new UserPost("Jane Smith",
            OffsetDateTime.of(2021, 4, 12, 13, 22, 12, 0, ZoneOffset.UTC),
            "An example of a post \nwith lines breaks.", 3);

    @Test
    void likePostSort_withNull_returnsNull() {
        List<UserPost> initialList = null;
        List<UserPost> sortedList = new LikePostSorter().sort(initialList, SortOrder.ASC);
        Assertions.assertEquals(null, sortedList);
    }

    @Test
    void likePostSort_withEmptyList_returnsEmptyList() {
        List<UserPost> initialList = Arrays.asList();
        List<UserPost> expected = Arrays.asList();
        List<UserPost> sortedList = new LikePostSorter().sort(initialList, SortOrder.ASC);
        Assertions.assertEquals(expected, sortedList);
    }

    @Test
    void likePostSort_withOneElement_returnsListWithOneElement() {
        List<UserPost> initialList = Arrays.asList(userPost1);
        List<UserPost> expected = Arrays.asList(userPost1);
        List<UserPost> sortedList = new LikePostSorter().sort(initialList, SortOrder.ASC);
        Assertions.assertEquals(expected, sortedList);
    }

    @Test
    void likePostSort_withMultipleElements_returnsListWithMultipleElements() {
        List<UserPost> initialList = Arrays.asList(userPost2, userPost4, userPost3, userPost1);
        List<UserPost> expected = Arrays.asList(userPost2, userPost1, userPost4, userPost3);
        List<UserPost> sortedList = new LikePostSorter().sort(initialList, SortOrder.ASC);
        Assertions.assertEquals(expected, sortedList);
    }

    @Test
    void likePostSort_withMultipleElementsDESC_returnsListWithMultipleElementsDESC() {
        List<UserPost> initialList = Arrays.asList(userPost2, userPost4, userPost3, userPost1);
        List<UserPost> expected = Arrays.asList(userPost4, userPost3, userPost1, userPost2);
        List<UserPost> sortedList = new LikePostSorter().sort(initialList, SortOrder.DESC);
        Assertions.assertEquals(expected, sortedList);
    }
}