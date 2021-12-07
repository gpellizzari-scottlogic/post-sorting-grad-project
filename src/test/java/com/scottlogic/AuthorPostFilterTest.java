package com.scottlogic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorPostFilterTest {

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

    UserPost userPost5 = new UserPost("",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "Hello World!", 2);

    @Test
    void authorPostFilter_withNull_returnsNull() {
        List<UserPost> actual = null;
        actual = new AuthorPostFilter("Alreen Trapezoid").filter(actual);
        Assertions.assertEquals(null, actual);
    }

    @Test
    void authorPostFilter_withEmptyList_returnsEmptyList() {
        List<UserPost> actual = Arrays.asList();
        List<UserPost> expected = Arrays.asList();
        actual = new AuthorPostFilter("Alreen Trapezoid").filter(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void authorPostFilter_withOneElementAndEmptyField_returnsEmptyList() {
        List<UserPost> actual = Arrays.asList(userPost1);
        List<UserPost> expected = Arrays.asList();
        actual = new AuthorPostFilter("").filter(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void authorPostFilter_withOneElementWithoutName_returnsEmptyList() {
        List<UserPost> actual = Arrays.asList(userPost5);
        List<UserPost> expected = Arrays.asList();

        actual = new AuthorPostFilter("Cucumber").filter(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void authorPostFilter_withOneElementAndNoAuthorToFilter_returnsEmptyList() {
        List<UserPost> actual = Arrays.asList(userPost5);
        List<UserPost> expected = Arrays.asList();

        actual = new AuthorPostFilter("").filter(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void authorPostFilter_withOneElement_returnsMatch() {
        List<UserPost> actual = Arrays.asList(userPost4);
        List<UserPost> expected = Arrays.asList(userPost4);

        actual = new AuthorPostFilter("Cucumber").filter(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void authorPostFilter_withMultipleElements_returnsMatch() {
        List<UserPost> actual = Arrays.asList(userPost1,userPost2,userPost3,userPost4,userPost5);
        List<UserPost> expected = Arrays.asList(userPost4);

        actual = new AuthorPostFilter("Cucumber").filter(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void authorPostFilter_withMultipleElements_returnsMatches() {
        List<UserPost> actual = Arrays.asList(userPost1,userPost2,userPost3,userPost4,userPost5);
        List<UserPost> expected = Arrays.asList(userPost1, userPost2);

        actual = new AuthorPostFilter("joe bloggs").filter(actual);
        Assertions.assertEquals(expected, actual);
    }




}