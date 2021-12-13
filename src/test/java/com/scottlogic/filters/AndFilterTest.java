package com.scottlogic.filters;

import com.scottlogic.UserPost;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AndFilterTest {

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

    UserPost userPost6 = new UserPost("Luke Vincent",
            OffsetDateTime.of(2021, 12, 8, 7, 12, 3, 0, ZoneOffset.UTC),
            "I will not approve your PR", -1);
    
    AuthorPostFilter authorPostFilter = new AuthorPostFilter("Joe Bloggs"); //includes userPosts 1-2
    KeywordPostFilter keywordPostFilter = new KeywordPostFilter("example"); //includes userPosts 3-4
    LikePostFilter likePostFilter = new LikePostFilter(true); //includes userPosts 1-2-4

    @Test
    void andPostFilter_withNull_returnsEmptyList() {
        List<UserPost> initialList = null;
        List<UserPost> expected = Arrays.asList();
        List<UserPost> filteredList = new AndFilter(authorPostFilter, keywordPostFilter).filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void andPostFilter_withEmptyList_returnsEmptyList() {
        List<UserPost> initialList = Arrays.asList();
        List<UserPost> expected = Arrays.asList();
        List<UserPost> filteredList = new AndFilter(authorPostFilter, keywordPostFilter).filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void andPostFilter_withOneElement_returnsOneElement() {
        List<UserPost> initialList = Arrays.asList(userPost1);
        List<UserPost> expected = Arrays.asList(userPost1);
        List<UserPost> filteredList = new AndFilter(authorPostFilter, likePostFilter).filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void andPostFilter_withMultipleElements_returnsOneElement(){
        List<UserPost> initialList = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5, userPost6);
        List<UserPost> expected = Arrays.asList(userPost4);
        List<UserPost> filteredList = new AndFilter(keywordPostFilter, likePostFilter).filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }

    @Test
    void andPostFilter_withMultipleElements_returnsNoElements(){
        List<UserPost> initialList = Arrays.asList(userPost1, userPost2, userPost3, userPost4, userPost5, userPost6);
        List<UserPost> expected = Arrays.asList();
        List<UserPost> filteredList = new AndFilter(authorPostFilter, keywordPostFilter).filter(initialList);
        Assertions.assertEquals(expected, filteredList);
    }
}