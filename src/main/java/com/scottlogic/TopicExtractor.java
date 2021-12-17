package com.scottlogic;

import java.util.*;

public class TopicExtractor {

    private List<String> forbiddenWords;
    private String forbiddenWordsRegex;

    public TopicExtractor() {
        this.forbiddenWords = Arrays.asList("is", "as", "and", "to", "for", "it", "its", "the", "that", "we", "ever", "it", "he", "she", "a", "in", "i", "are", "this");
        this.forbiddenWordsRegex = "\\s" + this.forbiddenWords.get(0) + "\\s";

        for (int i = 1; i < this.forbiddenWords.size(); i++) {
            this.forbiddenWordsRegex += "|\\s" + this.forbiddenWords.get(i) + "\\s";
        }
    }

    //method that removes any punctuation or forbiddenWord from the String
    private String cleanString(String string) {

        String cleanedString = string.replaceAll("\\p{Punct}", "");
        cleanedString = cleanedString.toLowerCase();
        cleanedString = cleanedString.replaceAll(this.forbiddenWordsRegex, " ");

        String previousString = "";
        do {
            previousString = cleanedString;
            cleanedString = cleanedString.replaceAll(this.forbiddenWordsRegex, " ");
        } while (!cleanedString.equals(previousString));

        return cleanedString;
    }

    //method that receives a post as input and returns a list of all the topics it contains sorted by number of appearances .
    public List<Topic> extractTopics(UserPost userPost) {
        HashMap<String, Integer> topics = new HashMap<>();
        List<Topic> outputList = new ArrayList<>();

        if (userPost == null) {
            return outputList;
        }

        String content = cleanString(userPost.getContents());
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

}
