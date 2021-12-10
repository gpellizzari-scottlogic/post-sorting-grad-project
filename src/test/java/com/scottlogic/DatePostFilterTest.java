package com.scottlogic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatePostFilterTest {

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
            OffsetDateTime.of(2022, 8, 9, 3, 6, 39, 0, ZoneOffset.UTC),
            "Welcome to the jungle of misery extraterrestrial", 0);

    UserPost userPost6 = new UserPost("Luke Vincent",
            OffsetDateTime.of(2021, 12, 8, 7, 12, 3, 0, ZoneOffset.UTC),
            "I will not approve your PR", -1);

    OffsetDateTime date1 = OffsetDateTime.of(2010, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC);
    OffsetDateTime date2 = OffsetDateTime.of(2011, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC);
    OffsetDateTime date3 = OffsetDateTime.of(2020, 1, 2, 7, 12, 3, 0, ZoneOffset.UTC);
    OffsetDateTime date4 = OffsetDateTime.of(2020, 1, 3, 9, 12, 3, 0, ZoneOffset.UTC);
    OffsetDateTime date5 = OffsetDateTime.of(2021, 4, 10, 7, 12, 3, 0, ZoneOffset.UTC);

    @Test
    void datePostFilter_withMultipleElements_endDateBeforeStartDate_throwsException() {
        List<UserPost> initialList = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);
        String expectedResult = "";
        String errorMessage = "";
        try{
            List<UserPost> filteredList = new DatePostFilter(date1, date2).filter(initialList);
        }catch (DateTimeException e){
            errorMessage = e.getMessage();
        }
        Assertions.assertEquals(expectedResult, errorMessage);
    }

    @Test
    void datePostFilter_withNull_returnsNull() {
        List<UserPost> initialList = null;
        List<UserPost> filteredList = new DatePostFilter(date1, date2).filter(initialList);
        Assertions.assertEquals(null, filteredList);
    }

    @Test
    void datePostFilter_withEmptyList_returnsEmptyList() {
        List<UserPost> initialList = Arrays.asList();
        List<UserPost> expected = Arrays.asList();
        List<UserPost> filteredList = new DatePostFilter(date3, date4).filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void datePostFilter_withOneElement_returnsOneElement() {
        List<UserPost> initialList = Arrays.asList(userPost1);
        List<UserPost> expected = Arrays.asList(userPost1);
        List<UserPost> filteredList = new DatePostFilter(date3, date4).filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void likePostFilter_withMultipleElements_returnsMatches() {
        List<UserPost> initialList = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5);
        List<UserPost> expected = Arrays.asList(userPost1, userPost2, userPost3);
        List<UserPost> filteredList = new DatePostFilter(date3, date4).filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }
}