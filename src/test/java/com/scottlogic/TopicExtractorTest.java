package com.scottlogic;

import com.scottlogic.filters.KeywordPostFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TopicExtractorTest {

    UserPost userPost1 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "The topic in this post is rabbits. Rabbits are a very cool animal species and are often utilised as pet animals. I hope this clears things about rabbits. I wish I owned rabbits or an animal. cats are cats and cats", 2);

    UserPost userPost2 = new UserPost("Joe Bloggs",
            OffsetDateTime.of(2020, 1, 3, 7, 12, 3, 0, ZoneOffset.UTC),
            "", 2);

    UserPost userPost3 = new UserPost("Albert Einstein",
            OffsetDateTime.of(2020, 1, 3, 8, 53, 34, 0, ZoneOffset.UTC),
            "Another example post.", 1);

    UserPost userPost4 = new UserPost("Cucumber",
            OffsetDateTime.of(2021, 3, 12, 13, 22, 12, 0, ZoneOffset.UTC),
            "An example of a post \nwith lines breaks.", 3);

    @Test
    void topicExtractor_withNull_returnsEmptyList() {
        UserPost initialUserPost = null;
        List<Topic> expected = Arrays.asList();
        List<Topic> listOfTopics = new TopicExtractor().extractTopics(initialUserPost);
        Assertions.assertEquals(expected, listOfTopics);
    }

    @Test
    void topicExtractor_withNoContent_returnsEmptyList() {
        UserPost initialUserPost = userPost2;
        List<Topic> expected = Arrays.asList();
        List<Topic> listOfTopics = new TopicExtractor().extractTopics(initialUserPost);
        Assertions.assertEquals(expected, listOfTopics);
    }

    @Test
    void topicExtractor_withContentAnd_returnsListOfTopics() {
        UserPost initialUserPost = userPost1;
        List<Topic> expected = Arrays.asList(new Topic("rabbits: ",2), new Topic("fdsfs", 2));
        List<Topic> listOfTopics = new TopicExtractor().extractTopics(initialUserPost);
        for(Topic topic: listOfTopics) {
            System.out.println("Topic: " + topic.getTopic() + " count: " + topic.getCount());
        }
        Assertions.assertEquals(expected, listOfTopics);
    }
}