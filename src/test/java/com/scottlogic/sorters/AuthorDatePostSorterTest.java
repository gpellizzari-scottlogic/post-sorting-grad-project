package com.scottlogic.sorters;

import com.scottlogic.SortOrder;
import com.scottlogic.UserPost;
import com.scottlogic.filters.DatePostFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDatePostSorterTest {

    UserPost userPost1 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "Hello World!", 2); //6

    UserPost userPost2 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "Hello World!", 2); //7

    UserPost userPost3 = new UserPost("Albert Einstein",
            OffsetDateTime.of(2020, 1, 3, 8, 53, 34, 0, ZoneOffset.UTC),
            "Another example post.", 0); //1

    UserPost userPost4 = new UserPost("Cucumber",
            OffsetDateTime.of(2021, 3, 12, 13, 22, 12, 0, ZoneOffset.UTC),
            "An example of a post \nwith lines breaks.", 3); //5

    UserPost userPost5 = new UserPost("",
            OffsetDateTime.of(2020, 1, 3, 17, 12, 3, 0, ZoneOffset.UTC),
            "Welcome to the jungle of misery extraterrestrial", 0); //8

    UserPost userPost6 = new UserPost("Cucumber",
            OffsetDateTime.of(2019, 10, 8, 12, 12, 3, 0, ZoneOffset.UTC),
            "I Hate washing the dishes", -1); //2

    UserPost userPost7 = new UserPost("Cucumber",
            OffsetDateTime.of(2020, 12, 19, 7, 6, 34, 0, ZoneOffset.UTC),
            "Dining in the dining room", -1); //3

    UserPost userPost8 = new UserPost("Cucumber",
            OffsetDateTime.of(2021, 2, 8, 7, 0, 0, 0, ZoneOffset.UTC),
            "Racoons for dinner", -1); //4

    @Test
    void authorDatePostSorter_withNull_returnsEmptyList() {
        List<UserPost> initialList = null;
        List<UserPost> expected = Arrays.asList();
        List<UserPost> filteredList = new AuthorDatePostSorter().sort(initialList, SortOrder.ASC);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void authorDatePostSorter_withEmptyList_returnsEmptyList() {
        List<UserPost> initialList = Arrays.asList();
        List<UserPost> expected = Arrays.asList();
        List<UserPost> filteredList = new AuthorDatePostSorter().sort(initialList, SortOrder.ASC);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void authorDatePostSorter_withOneElement_returnsOneElement() {
        List<UserPost> initialList = Arrays.asList(userPost1);
        List<UserPost> expected = Arrays.asList(userPost1);
        List<UserPost> filteredList = new AuthorDatePostSorter().sort(initialList, SortOrder.ASC);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void authorDatePostSorter_withMultipleElements_returnsMultipleElements() {
        List<UserPost> initialList = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5, userPost6, userPost7, userPost8);
        List<UserPost> expected = Arrays.asList(userPost5, userPost1, userPost2, userPost6, userPost7, userPost8, userPost4, userPost3);
        List<UserPost> filteredList = new AuthorDatePostSorter().sort(initialList, SortOrder.ASC);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void authorDatePostSorter_withMultipleElements_returnsMultipleElementsDesc() {
        List<UserPost> initialList = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5, userPost6, userPost7, userPost8);
        List<UserPost> expected = Arrays.asList(userPost3, userPost6, userPost7, userPost8, userPost4, userPost1, userPost2, userPost5);
        List<UserPost> filteredList = new AuthorDatePostSorter().sort(initialList, SortOrder.DESC);
        Assertions.assertEquals(expected, filteredList);
    }
}