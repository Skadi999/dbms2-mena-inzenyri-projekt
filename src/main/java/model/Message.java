package model;

public class Message {
    private final int messageType;
    private final String message;
    private final String sender;

    public Message(int messageType, String message, String sender) {
        this.messageType = messageType;
        this.message = message;
        this.sender = sender;
    }

    public int getMessageType() {
        return messageType;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }
}
