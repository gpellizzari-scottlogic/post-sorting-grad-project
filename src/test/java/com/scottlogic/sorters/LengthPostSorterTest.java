package com.scottlogic.sorters;

import com.scottlogic.SortOrder;
import com.scottlogic.UserPost;
import com.scottlogic.sorters.LengthPostSorter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

class LengthPostSorterTest {

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
            OffsetDateTime.of(2021, 3, 12, 13, 22, 12, 0, ZoneOffset.UTC),
            "An example of a post \nwith lines breaks.", 3);

    @Test
    void lengthPostSort_withNull_returnsEmptyList() {
        List<UserPost> actual = null;
        List<UserPost> expected = Arrays.asList();
        actual = new LengthPostSorter().sort(actual, SortOrder.ASC);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void lengthPostSort_withEmptyList_returnsEmptyList() {
        List<UserPost> actual = Arrays.asList();
        List<UserPost> expected = Arrays.asList();
        actual = new LengthPostSorter().sort(actual, SortOrder.ASC);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void lengthPostSort_withOneElement_returnsListWithOneElement() {
        List<UserPost> actual = Arrays.asList(userPost1);
        List<UserPost> expected = Arrays.asList(userPost1);
        actual = new LengthPostSorter().sort(actual, SortOrder.ASC);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void lengthPostSort_withMultipleElements_returnsListWithMultipleElements() {
        List<UserPost> actual = Arrays.asList(userPost4, userPost2, userPost3, userPost1);
        List<UserPost> expected = Arrays.asList(userPost1, userPost2, userPost3, userPost4);
        actual = new LengthPostSorter().sort(actual, SortOrder.ASC);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void lengthPostSort_withMultipleElementsDESC_returnsListWithMultipleElementsDESC() {
        List<UserPost> actual = Arrays.asList(userPost4, userPost2, userPost3, userPost1);
        List<UserPost> expected = Arrays.asList(userPost3, userPost4, userPost2, userPost1);
        actual = new LengthPostSorter().sort(actual, SortOrder.DESC);
        Assertions.assertEquals(expected, actual);
    }
}