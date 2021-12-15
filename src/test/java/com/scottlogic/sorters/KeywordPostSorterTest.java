package com.scottlogic.sorters;

import com.scottlogic.SortOrder;
import com.scottlogic.UserPost;
import com.scottlogic.filters.KeywordPostFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KeywordPostSorterTest {

    UserPost userPost1 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "Hello World!", 2);

    UserPost userPost2 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "example1, 2example, really examples everywhere!", 2);

    UserPost userPost3 = new UserPost("Albert Einstein",
            OffsetDateTime.of(2020, 1, 3, 8, 53, 34, 0, ZoneOffset.UTC),
            "Another example! post.", 1);

    UserPost userPost4 = new UserPost("Cucumber",
            OffsetDateTime.of(2021, 3, 12, 13, 22, 12, 0, ZoneOffset.UTC),
            "An example of a post \nwith lines breaks, If I wanted I could add many more examples", 3);

    UserPost userPost5 = new UserPost("",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "Welcome to the jungle of misery extraterrestrial", 2);

    @Test
    void keywordPostSorter_withNull_returnsEmptyList() {
        List<UserPost> initialList = null;
        List<UserPost> expected = Arrays.asList();
        List<UserPost> filteredList = new KeywordPostSorter("").sort(initialList, SortOrder.ASC);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void keywordPostSorter_withEmptyList_returnsEmptyList() {
        List<UserPost> initialList = Arrays.asList();
        List<UserPost> expected = Arrays.asList();
        List<UserPost> filteredList = new KeywordPostSorter("test").sort(initialList, SortOrder.ASC);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void keywordPostSorter_withOneElementAndEmptyField_returnsElement() {
        List<UserPost> initialList = Arrays.asList(userPost1);
        List<UserPost> expected = Arrays.asList(userPost1);
        List<UserPost> filteredList = new KeywordPostSorter("").sort(initialList, SortOrder.ASC);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void KeywordPostSorter_withMultipleElements_returnsMatches() {
        List<UserPost> initialList = Arrays.asList(userPost1, userPost2, userPost4, userPost3, userPost5);
        List<UserPost> expected = Arrays.asList(userPost1, userPost5, userPost3, userPost4, userPost2);
        List<UserPost> filteredList = new KeywordPostSorter("example").sort(initialList, SortOrder.ASC);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void KeywordPostSorter_withMultipleElements_returnsMatchesDESC() {
        List<UserPost> initialList = Arrays.asList(userPost1, userPost2, userPost4, userPost3, userPost5);
        List<UserPost> expected = Arrays.asList(userPost2, userPost4, userPost3, userPost1, userPost5);
        List<UserPost> filteredList = new KeywordPostSorter("example").sort(initialList, SortOrder.DESC);
        Assertions.assertEquals(expected, filteredList);
    }
}