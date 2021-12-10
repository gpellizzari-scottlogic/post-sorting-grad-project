package com.scottlogic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KeywordPostFilterTest {

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
            "Welcome to the jungle of misery extraterrestrial", 2);

    @Test
    void keywordPostFilter_withNull_returnsNull() {
        List<UserPost> initialList = null;
        List<UserPost> filteredList = new KeywordPostFilter("").filter(initialList);
        Assertions.assertEquals(null, filteredList);
    }

    @Test
    void keywordPostFilter_withEmptyList_returnsEmptyList() {
        List<UserPost> initialList = Arrays.asList();
        List<UserPost> expected = Arrays.asList();
        List<UserPost> filteredList = new KeywordPostFilter("test").filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void keywordPostFilter_withOneElementAndEmptyField_returnsEmptyList() {
        List<UserPost> initialList = Arrays.asList(userPost1);
        List<UserPost> expected = Arrays.asList();
        List<UserPost> filteredList = new KeywordPostFilter("").filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void keywordPostFilter_withOneElement_returnsMatchAtMiddleOfSentence() {
        List<UserPost> initialList = Arrays.asList(userPost3);
        List<UserPost> expected = Arrays.asList(userPost3);
        List<UserPost> filteredList = new KeywordPostFilter("Example").filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void keywordPostFilter_withOneElement_returnsMatchAtTheStartOfPost() {
        List<UserPost> initialList = Arrays.asList(userPost1);
        List<UserPost> expected = Arrays.asList(userPost1);
        List<UserPost> filteredList = new KeywordPostFilter("Hello").filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void keywordPostFilter_withOneElement_returnsMatchAtTheEndOfPost() {
        List<UserPost> initialList = Arrays.asList(userPost1);
        List<UserPost> expected = Arrays.asList(userPost1);
        List<UserPost> filteredList = new KeywordPostFilter("World").filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void keywordPostFilter_withMultipleElements_returnsMatch() {
        List<UserPost> initialList = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);
        List<UserPost> expected = Arrays.asList(userPost5);
        List<UserPost> filteredList = new KeywordPostFilter("jungle").filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void keywordPostFilter_withMultipleElements_returnsMatches() {
        List<UserPost> initialList = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);
        List<UserPost> expected = Arrays.asList(userPost3, userPost4);
        List<UserPost> filteredList = new KeywordPostFilter("example").filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void keywordPostFilter_withMultipleElements_returnsEmptyList() {
        List<UserPost> initialList = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);
        List<UserPost> expected = Arrays.asList();
        List<UserPost> filteredList = new KeywordPostFilter("Tiramisu").filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void keywordPostFilter_withMultipleElementsWithWordOfFieldEmbedded_returnsEmptyList() {
        List<UserPost> initialList = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);
        List<UserPost> expected = Arrays.asList();
        List<UserPost> filteredList = new KeywordPostFilter("Terra").filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }


}