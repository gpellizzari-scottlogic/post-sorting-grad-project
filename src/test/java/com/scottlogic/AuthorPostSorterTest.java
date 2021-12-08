package com.scottlogic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorPostSorterTest {

    UserPost userPost1 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "Hello World!", 2);

    UserPost userPost2 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "Hello World!", 2);

    UserPost userPost3 = new UserPost("Albert Einstein",
            OffsetDateTime.of(2020, 1, 3, 8, 53, 34, 0, ZoneOffset.UTC),
            "Another example post.", 1);

    UserPost userPost4 = new UserPost("Cucumber",
            OffsetDateTime.of(2021, 3, 12, 13, 22, 12, 0, ZoneOffset.UTC),
            "An example of a post \nwith lines breaks.", 3);

    UserPost userPost5 = new UserPost("Alreen Trapezoid",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "Hello World!", 2);

    @Test
    void authorPostSort_withNull_returnsNull() {
        List<UserPost> initialList = null;
        List<UserPost> sortedList = new AuthorPostSorter().sort(initialList, SortOrder.ASC);
        Assertions.assertEquals(null, sortedList);
    }

    @Test
    void authorPostSort_withEmptyList_returnsEmptyList() {
        List<UserPost> initialList = Arrays.asList();
        List<UserPost> expected = Arrays.asList();
        List<UserPost> sortedList = new AuthorPostSorter().sort(initialList, SortOrder.ASC);
        Assertions.assertEquals(expected, sortedList);
    }

    @Test
    void authorPostSort_withOneElement_returnsListWithOneElement() {
        List<UserPost> initialList = Arrays.asList(userPost1);
        List<UserPost> expected = Arrays.asList(userPost1);
        List<UserPost> sortedList = new AuthorPostSorter().sort(initialList, SortOrder.ASC);
        Assertions.assertEquals(expected, sortedList);
    }

    @Test
    void authorPostSort_withMultipleElements_returnsListWithMultipleElements() {
        List<UserPost> initialList = Arrays.asList(userPost5, userPost3, userPost1, userPost2);
        List<UserPost> expected = Arrays.asList(userPost1, userPost2, userPost3, userPost5);
        List<UserPost> sortedList = new AuthorPostSorter().sort(initialList, SortOrder.ASC);
        Assertions.assertEquals(expected, sortedList);
    }

    @Test
    void authorPostSort_withMultipleElementsAndName_returnsListWithMultipleElements() {
        List<UserPost> initialList = Arrays.asList(userPost3, userPost1, userPost2, userPost5, userPost4);
        List<UserPost> expected = Arrays.asList(userPost1, userPost2, userPost4, userPost3, userPost5);
        List<UserPost> sortedList = new AuthorPostSorter().sort(initialList, SortOrder.ASC);
        Assertions.assertEquals(expected, sortedList);
    }

    @Test
    void authorPostSort_withMultipleElementsDESC_returnsListWithMultipleElementsDESC() {
        List<UserPost> initialList = Arrays.asList(userPost1, userPost5, userPost2, userPost3);
        List<UserPost> expected = Arrays.asList(userPost5, userPost3, userPost1, userPost2);
        List<UserPost> sortedList = new AuthorPostSorter().sort(initialList, SortOrder.DESC);
        Assertions.assertEquals(expected, sortedList);
    }
}