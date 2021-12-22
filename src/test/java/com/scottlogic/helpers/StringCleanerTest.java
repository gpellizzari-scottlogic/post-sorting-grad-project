package com.scottlogic.helpers;

import com.scottlogic.Topic;
import com.scottlogic.TopicExtractor;
import com.scottlogic.UserPost;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringCleanerTest {

    @Test
    void stringCleaner_withNull_returnsEmptyString() {
        String stringToClean = null;
        String expected = "";
        String cleanedString = new StringCleaner().cleanString(stringToClean);
        Assertions.assertEquals(expected, cleanedString);
    }

    @Test
    void stringCleaner_withEmptyString_returnsEmptyString() {
        String stringToClean = "";
        String expected = "";
        String cleanedString = new StringCleaner().cleanString(stringToClean);
        Assertions.assertEquals(expected, cleanedString);
    }

    @Test
    void stringCleaner_withContentWithPunctuation_returnsCleanedString() {
        String stringToClean = "I'm super confident that 2*2 is 4!";
        String expected = "super confident 22 4";
        String cleanedString = new StringCleaner().cleanString(stringToClean);
        Assertions.assertEquals(expected, cleanedString);
    }

    @Test
    void stringCleaner_withContent_returnsCleanedString() {
        String stringToClean = "(*&%pasta    ";
        String expected = "pasta";
        String cleanedString = new StringCleaner().cleanString(stringToClean);
        Assertions.assertEquals(expected, cleanedString);
    }

    @Test
    void stringCleaner_withSpaceAtStartAndEnd_returnsCleanedString() {
        String stringToClean = " there are many trucks in town ";
        String expected = "many trucks town";
        String cleanedString = new StringCleaner().cleanString(stringToClean);
        Assertions.assertEquals(expected, cleanedString);
    }

}