package com.proyectoi.kinetia.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyectoi.kinetia.models.MessageModel;

import java.time.Instant;


public class Message {

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

    public Message(MessageModel message) {
        this.sender = message.getSender().getId();
        this.recipient = message.getReceiver().getId();
        this.content = message.getContent();
        this.sentAt = message.getSentAt();
        this.isRead = message.getRead();
    }


}
