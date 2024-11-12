package Models;

import java.util.Arrays;

/**
 * Represents a forum in the system where users can participate in discussions based on a specific topic.
 * The forum has a unique ID, a main topic, and a list of related topics.
 */
public class Forum implements Identifiable {
    private int forumID;
    private String topic;
    private String[] topics;

    /**
     * Constructs a new Forum with the specified forum ID, main topic, and related topics.
     *
     * @param forumID The unique identifier for this forum.
     * @param topic   The main topic of the forum.
     * @param topics  An array of topics related to the forum discussion.
     */
    public Forum(int forumID, String topic, String[] topics) {
        this.forumID = forumID;
        this.topic = topic;
        this.topics = topics;
    }

    /**
     * Gets the unique identifier of the forum.
     *
     * @return The forum ID as an integer.
     */
    public int getForumID() {
        return this.forumID;
    }

    /**
     * Gets the main topic of the forum.
     *
     * @return A string representing the forum's main topic.
     */
    public String getTopic() {
        return this.topic;
    }

    /**
     * Gets the list of topics related to the forum's discussion.
     *
     * @return An array of strings containing the related topics.
     */
    public String[] getTopics() {
        return this.topics;
    }

    /**
     * Adds a post (message) to the forum.
     * The method should implement the logic to add a message to the forum's discussion.
     *
     * @param message The message to be added to the forum.
     */
    public void addPost(Message message){
        //implementation
    }

    /**
     * Views a specific message within the forum.
     * The method should implement the logic to display a particular message from the forum.
     *
     * @param message The message to be viewed.
     */
    public void viewMessage(Message message){
        //implementation
    }

    /**
     * Gets the unique identifier of the forum.
     *
     * @return The forum ID as an integer.
     */
    @Override
    public Integer getId() {
        return this.forumID;
    }

    /**
     * Provides a string representation of the forum, including the forum ID, main topic,
     * and the list of related topics.
     *
     * @return A formatted string with the forum details.
     */
    @Override
    public String toString() {
        return "Forum{" +
                "forumID=" + forumID +
                ", topic='" + topic + '\'' +
                ", topics=" + Arrays.toString(topics) +
                '}';
    }
}
