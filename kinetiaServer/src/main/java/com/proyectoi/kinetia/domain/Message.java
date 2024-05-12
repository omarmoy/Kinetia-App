package com.proyectoi.kinetia.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyectoi.kinetia.models.MessageModel;

import java.time.Instant;


public class Message {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("sender")
    private Long sender;
    @JsonProperty("recipient")
    private Long recipient;
    @JsonProperty("content")
    private String content;
    @JsonProperty("sentAt")
    private Instant sentAt;
    @JsonProperty("isRead")
    private Boolean isRead;

    public Message() {}

    public Message(MessageModel message) {
        this.id = message.getId();
        this.sender = message.getSender().getId();
        this.recipient = message.getReceiver().getId();
        this.content = message.getContent();
        this.sentAt = message.getSentAt();
        this.isRead = message.getIsRead();
    }

    public Long getSender() {
        return sender;
    }

    public Long getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

    public Instant getSentAt() {
        return sentAt;
    }

    public Boolean getIsRead() {
        return isRead;
    }
}
