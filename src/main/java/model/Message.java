package model;

public class Message {
    private int id;
    private final int messageType;
    private final String message;
    private final String sender;
    private final String receiver;
    private final String subject;

    public Message(String message, String sender, String receiver, String subject) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.messageType = -1;
        this.subject = subject;
    }

    public Message(int messageType, String message, String sender, String subject) {
        this.messageType = messageType;
        this.message = message;
        this.sender = sender;
        this.receiver = null;
        this.subject = subject;
    }

    public Message(int id, String message, String sender, String receiver, String subject) {
        this.id = id;
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.messageType = -1;
        this.subject = subject;
    }

    public Message(int id, int messageType, String message, String sender, String subject) {
        this.id = id;
        this.messageType = messageType;
        this.message = message;
        this.sender = sender;
        this.receiver = null;
        this.subject = subject;
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

    public String getReceiver() {
        return receiver;
    }

    public String getSubject() {
        return subject;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "#" + id + " From: " + sender + "    " + (subject != null ? ("Subject: " + subject) : "(No Subject)");
    }
}
