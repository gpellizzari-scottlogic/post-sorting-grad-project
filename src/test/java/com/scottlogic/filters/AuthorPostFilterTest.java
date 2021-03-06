package com.scottlogic.filters;

import com.scottlogic.UserPost;
import com.scottlogic.filters.AuthorPostFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

class AuthorPostFilterTest {

    UserPost userPost1 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "Hello World!", 2);

    UserPost userPost2 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "Hello World!", 2);

    UserPost userPost3 = new UserPost("Albert Einstein",
            OffsetDateTime.of(2020, 1, 3, 8, 53, 34, 0, ZoneOffset.UTC),
            "Another example post.", 0);

    UserPost userPost4 = new UserPost("Cucumber",
            OffsetDateTime.of(2021, 3, 12, 13, 22, 12, 0, ZoneOffset.UTC),
            "An example of a post \nwith lines breaks.", 3);

    UserPost userPost5 = new UserPost("",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "Welcome to the jungle of misery extraterrestrial", 0);

    @Test
    void authorPostFilter_withNull_returnsEmptyList() {
        List<UserPost> initialList = null;
        List<UserPost> expected = Arrays.asList();
        List<UserPost> filteredList = new AuthorPostFilter("Alreen Trapezoid").filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void authorPostFilter_withEmptyList_returnsEmptyList() {
        List<UserPost> initialList = Arrays.asList();
        List<UserPost> expected = Arrays.asList();
        List<UserPost> filteredList = new AuthorPostFilter("Alreen Trapezoid").filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void authorPostFilter_withOneElementAndEmptyField_returnsEmptyList() {
        List<UserPost> initialList = Arrays.asList(userPost1);
        List<UserPost> expected = Arrays.asList();
        List<UserPost> filteredList = new AuthorPostFilter("").filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void authorPostFilter_withOneElementWithoutName_returnsEmptyList() {
        List<UserPost> initialList = Arrays.asList(userPost5);
        List<UserPost> expected = Arrays.asList();

        List<UserPost> filteredList = new AuthorPostFilter("Cucumber").filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void authorPostFilter_withOneElementAndNoAuthorToFilter_returnsElement() {
        List<UserPost> initialList = Arrays.asList(userPost5);
        List<UserPost> expected = Arrays.asList(userPost5);

        List<UserPost> filteredList = new AuthorPostFilter("").filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void authorPostFilter_withOneElement_returnsMatch() {
        List<UserPost> initialList = Arrays.asList(userPost4);
        List<UserPost> expected = Arrays.asList(userPost4);

        List<UserPost> filteredList = new AuthorPostFilter("Cucumber").filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void authorPostFilter_withMultipleElements_returnsMatch() {
        List<UserPost> initialList = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);
        List<UserPost> expected = Arrays.asList(userPost4);

        List<UserPost> filteredList = new AuthorPostFilter("Cucumber").filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void authorPostFilter_withMultipleElements_returnsMatches() {
        List<UserPost> initialList = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);
        List<UserPost> expected = Arrays.asList(userPost1, userPost2);

        List<UserPost> filteredList = new AuthorPostFilter("joe bloggs").filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }
}