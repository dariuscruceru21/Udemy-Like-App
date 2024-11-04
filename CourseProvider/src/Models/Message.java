package Models;

public class Message implements Identifiable {
    private int messageID;
    private String message;
    private User sender;
    private User receiver;

    public Message(int messageID, String message, User sender, User receiver) {
        this.messageID = messageID;
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getMessage() {
        return this.message;
    }

    public User getSender() {
        return this.sender;
    }

    public User getReceiver() {
        return this.receiver;
    }

    @Override
    public int getId() {
        return this.messageID;
    }
}
