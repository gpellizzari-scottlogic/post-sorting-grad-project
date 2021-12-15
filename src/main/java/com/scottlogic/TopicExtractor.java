package com.scottlogic;

import java.util.*;

public class TopicExtractor {

    private List<String> forbiddenWords;
    private String forbiddenWordsRegex;

    public TopicExtractor() {
        this.forbiddenWords = Arrays.asList("is", "as", "and", "to", "for", "it", "its", "the", "that", "we", "ever", "it", "he", "she");
        this.forbiddenWordsRegex = this.forbiddenWords.get(0);
        for (int i=1; i<this.forbiddenWords.size(); i++){
            this.forbiddenWordsRegex += "|" + this.forbiddenWords.get(i);
        }

//        for(String word: forbiddenWords){
//            this.forbiddenWordsRegex += word + "|";
//        }
//        this.forbiddenWordsRegex = this.forbiddenWordsRegex.substring(0,this.forbiddenWordsRegex.length()-1);
    }

    //method that removes any punctuation or forbiddenWord from the String
    private String cleanString(String string){
        String cleanedString = string.replaceAll("\\p{Punct}", "");
        cleanedString = cleanedString.replaceAll(this.forbiddenWordsRegex, "");

        return cleanedString;
    }

    //method that receives a post as input and returns a list of all the topics it contains sorted by number of appearances .
    public List<Topic> extractTopics(UserPost userPost){
        HashMap<String, Integer> topics = new HashMap<>();
        List<Topic> outputList = new ArrayList<>();

        if(userPost.getContents() == null || userPost.getContents().isEmpty()){
            return outputList;
        }

        String content = cleanString(userPost.getContents());
        String[] words = content.split(" ");

        //loop through each word of the topic
        for(String word: words){
            topics.putIfAbsent(word,0);
            topics.compute(word, (k,v) -> v+1);
        }

        //convert the hash map into a list and sort it
        topics.forEach((k,v) -> outputList.add(new Topic(k,v)));
        Collections.sort(outputList, Comparator.comparingInt(Topic::getCount));
        return outputList;
    }

}
