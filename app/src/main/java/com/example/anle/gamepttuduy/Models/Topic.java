package com.example.anle.gamepttuduy.Models;

public class Topic {

    private int IdTopic;
    private int imgResource;
    private String TopicName;

    public Topic(int IdTopic,int imgResource, String topicName) {
        this.IdTopic=IdTopic;
        this.imgResource = imgResource;
        TopicName = topicName;
    }

    public int getIdTopic() {
        return IdTopic;
    }

    public void setIdTopic(int idTopic) {
        IdTopic = idTopic;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public String getTopicName() {
        return TopicName;
    }

    public void setTopicName(String topicName) {
        TopicName = topicName;
    }
}
