package Models;

import Repository.Identifiable;

public class Forum implements Identifiable {
    private int forumID;
    private String topic;
    private String[] topics;

    Forum(int forumID, String topic, String[] topics) {
        this.forumID = forumID;
        this.topic = topic;
        this.topics = topics;
    }

    public int getForumID() {
        return this.forumID;
    }

    public String getTopic() {
        return this.topic;
    }

    public String[] getTopics() {
        return this.topics;
    }

    public void addPost(Message message){
        //implementation
    }

    public void viewMessage(Message message){
        //implementation
    }

    @Override
    public int getId() {
        return this.forumID;
    }
}
