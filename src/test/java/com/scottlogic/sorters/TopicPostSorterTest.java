package com.scottlogic.sorters;

import com.scottlogic.SortOrder;
import com.scottlogic.UserPost;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TopicPostSorterTest {

    UserPost userPost1 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "The Ant is one of the coolest animals in the world. An Ant will sacrifice itself for the colony.", 2);

    UserPost userPost2 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "A baboon is a very dangerous animal. I would never want to fight a Baboon", 2);

    UserPost userPost3 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "The topic in this post is rabbits. Rabbits are a very cool animal species and are often utilised as pet animals. I hope this clears things about rabbits. I wish I owned rabbits or an animal. cats are cats and cats", 2);

    UserPost userPost4 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "The topic in this post is baboon. A baboon is a very cool animal species and isn't often utilised as a pet animal. I once saw a baboon", 2);

    @Test
    void topicPostSort_withNull_returnsEmptyList() {
        List<UserPost> actual = null;
        List<UserPost> expected = Arrays.asList();
        actual = new TopicPostSorter().sort(actual, SortOrder.ASC);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void topicPostSort_withEmptyList_returnsEmptyList() {
        List<UserPost> actual = Arrays.asList();
        List<UserPost> expected = Arrays.asList();
        actual = new TopicPostSorter().sort(actual, SortOrder.ASC);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void topicPostSort_withOneElement_returnsListWithOneElement() {
        List<UserPost> actual = Arrays.asList(userPost1);
        List<UserPost> expected = Arrays.asList(userPost1);
        actual = new TopicPostSorter().sort(actual, SortOrder.ASC);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void topicPostSort_withMultipleElements_returnsListWithMultipleElements() {
        List<UserPost> actual = Arrays.asList(userPost2, userPost1, userPost3);
        List<UserPost> expected = Arrays.asList(userPost1, userPost2, userPost3);
        actual = new TopicPostSorter().sort(actual, SortOrder.ASC);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void topicPostSort_withMultipleElements_returnsListWithMultipleElementsDESC() {
        List<UserPost> actual = Arrays.asList(userPost2, userPost1, userPost3);
        List<UserPost> expected = Arrays.asList(userPost3, userPost2, userPost1);
        actual = new TopicPostSorter().sort(actual, SortOrder.DESC);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void topicPostSort_withMultipleElementsOnOneTopic_returnsListWithMultipleElements() {
        List<UserPost> actual = Arrays.asList(userPost2, userPost1, userPost4, userPost3);
        List<UserPost> expected = Arrays.asList(userPost1, userPost2, userPost4, userPost3);
        actual = new TopicPostSorter().sort(actual, SortOrder.ASC);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void topicPostSort_withMultipleElementsOnOneTopic_returnsListWithMultipleElementsDESC() {
        List<UserPost> actual = Arrays.asList(userPost2, userPost1, userPost4, userPost3);
        List<UserPost> expected = Arrays.asList(userPost3, userPost2, userPost4, userPost1);
        actual = new TopicPostSorter().sort(actual, SortOrder.DESC);
        Assertions.assertEquals(expected, actual);
    }

}