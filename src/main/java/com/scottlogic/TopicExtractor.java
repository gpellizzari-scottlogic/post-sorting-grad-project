package com.scottlogic;

import com.scottlogic.helpers.StringCleaner;

import java.util.*;

public class TopicExtractor {

    //method that receives a post as input and returns a list of all the topics it contains sorted by number of appearances .
    public List<Topic> extractTopics(UserPost userPost) {

        HashMap<String, Integer> topics = new HashMap<>();
        List<Topic> outputList = new ArrayList<>();

        if (userPost == null) {
            return outputList;
        }

        String content = new StringCleaner().cleanString(userPost.getContents());
        String[] words = content.split(" ");

        //loop through each word of the content and add it to the topic dictionary
        for (String word : words) {
            topics.putIfAbsent(word, 0);
            topics.compute(word, (k, v) -> v + 1);
        }

        //convert the hash map into a list and sort it
        topics.forEach((k, v) -> outputList.add(new Topic(k, v)));
        Collections.sort(outputList, Comparator.comparingInt(Topic::getCount).reversed());

        //remove keywords that have only 1 occurrence in the list
        Iterator itr = outputList.iterator();
        while (itr.hasNext()) {
            Topic t = (Topic) itr.next();
            if (t.getCount() < 2) {
                itr.remove();
            }
        }

        return outputList;
    }

    public String ExtractMainTopic(UserPost userPost) {
        return extractTopics(userPost).get(0).getTopic();
    }

}
