package com.scottlogic;

import com.scottlogic.filters.KeywordPostFilter;
import com.scottlogic.helpers.StringCleaner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TopicExtractorTest {

    StringCleaner stringCleaner = new StringCleaner();

    @Test
    void topicExtractor_withNull_returnsEmptyList() {
        UserPost initialUserPost = null;
        List<Topic> expected = Arrays.asList();
        List<Topic> listOfTopics = new TopicExtractor(stringCleaner).extractTopics(initialUserPost);
        Assertions.assertEquals(expected, listOfTopics);
    }

    @Test
    void topicExtractor_withNoContent_returnsEmptyList() {
        UserPost userPost2 = new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                "", 2);

        UserPost initialUserPost = userPost2;
        List<Topic> expected = Arrays.asList();

        List<Topic> listOfTopics = new TopicExtractor(stringCleaner).extractTopics(initialUserPost);
        Assertions.assertEquals(expected, listOfTopics);
    }

    @Test
    void topicExtractor_withContentAndTopics_returnsListOfTopics() {
        UserPost userPost1 = new UserPost("Joe Bloggs",
                OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
                "The topic in this post is rabbits. Rabbits are a very cool animal species and are often utilised as pet animals. I hope this clears things about rabbits. I wish I owned rabbits or an animal. cats are cats and cats", 2);

        Topic rabbits = new Topic("rabbits", 4);
        Topic cats = new Topic("cats", 3);
        Topic animal = new Topic("animal", 2);

        UserPost initialUserPost = userPost1;
        List<Topic> expected = Arrays.asList(rabbits, cats, animal);

        List<Topic> listOfTopics = new TopicExtractor(stringCleaner).extractTopics(initialUserPost);
        Assertions.assertEquals(expected, listOfTopics);
    }

    @Test
    void topicExtractor_withContentAndNoTopics_returnsListOfTopics() {
        UserPost userPost4 = new UserPost("Cucumber",
                OffsetDateTime.of(2021, 3, 12, 13, 22, 12, 0, ZoneOffset.UTC),
                "An example of a post \nwith lines breaks.", 3);

        UserPost initialUserPost = userPost4;
        List<Topic> expected = Arrays.asList();

        List<Topic> listOfTopics = new TopicExtractor(stringCleaner).extractTopics(initialUserPost);
        Assertions.assertEquals(expected, listOfTopics);
    }
}