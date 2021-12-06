package com.scottlogic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorPostSorterTest {

    @Test
    void sort_withNull_returnsNull(){
        List<UserPost> actual = null;
        actual = new AuthorPostSorter().sort(actual, SortOrder.ASC);
        Assertions.assertEquals(null,actual);
    }

    @Test
    void sort_withEmptyList_returnsEmptyList(){
        List<UserPost> actual = Arrays.asList();
        List<UserPost> expected = Arrays.asList();
        actual = new AuthorPostSorter().sort(actual, SortOrder.ASC);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void sort_withOneElement_returnsListWithOneElement(){
        UserPost userPost1 = new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                "Hello World!", 2);
        List<UserPost> actual = Arrays.asList(userPost1);
        List<UserPost> expected = Arrays.asList(userPost1);
        actual = new AuthorPostSorter().sort(actual, SortOrder.ASC);
        Assertions.assertEquals(expected,actual);
    }


    @Test
    void sort_withMultipleElements_returnsListWithMultipleElements() {
        //arrange
        UserPost userPost1 = new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                "Hello World!", 2);

        UserPost userPost2 = new UserPost("Alreen Bloggs",
                OffsetDateTime.of(2020, 1, 3, 8, 53, 34, 0, ZoneOffset.UTC),
                "Another example post.", 1);

        UserPost userPost3 = new UserPost("Jane Smith",
                OffsetDateTime.of(2020, 3, 12, 13, 22, 12, 0, ZoneOffset.UTC),
                "An example of a post \nwith lines breaks.", 3);

        List<UserPost> actual = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> expected = Arrays.asList(userPost2, userPost3, userPost1);
        //act
        actual = new AuthorPostSorter().sort(actual, SortOrder.ASC);
        //asses
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void sort_withMultipleElementsDESC_returnsListWithMultipleElementsDESC() {
        //arrange
        UserPost userPost1 = new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                "Hello World!", 2);

        UserPost userPost2 = new UserPost("Alreen Bloggs",
                OffsetDateTime.of(2020, 1, 3, 8, 53, 34, 0, ZoneOffset.UTC),
                "Another example post.", 1);

        UserPost userPost3 = new UserPost("Jane Smith",
                OffsetDateTime.of(2020, 3, 12, 13, 22, 12, 0, ZoneOffset.UTC),
                "An example of a post \nwith lines breaks.", 3);

        List<UserPost> actual = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> expected = Arrays.asList(userPost1, userPost3, userPost2);
        //act
        actual = new AuthorPostSorter().sort(actual, SortOrder.DESC);
        //asses
        Assertions.assertEquals(expected,actual);
    }





}