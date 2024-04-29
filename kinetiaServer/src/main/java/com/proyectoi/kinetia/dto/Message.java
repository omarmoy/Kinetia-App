package com.proyectoi.kinetia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyectoi.kinetia.models.MessageModel;

import java.time.LocalDateTime;


public class Message {

    @JsonProperty("sender")
    private Long sender;
    @JsonProperty("recipient")
    private Long recipient;
    @JsonProperty("content")
    private String content;
    @JsonProperty("sentAt")
    private LocalDateTime sentAt;
    @JsonProperty("isRead")
    private Boolean isRead;

    public Message(MessageModel message) {
        this.sender = message.getSender().getId();
        this.recipient = message.getRecipient().getId();
        this.content = message.getContent();
        this.sentAt = message.getSentAt();
        this.isRead = message.getRead();
    }


}
