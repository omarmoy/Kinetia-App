package com.proyectoi.kinetia.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyectoi.kinetia.models.*;

import java.util.ArrayList;
import java.util.List;

public class Chat {

    @JsonProperty("contactId")
    private Long contactId;
    @JsonProperty("contactName")
    private String contactName;
    @JsonProperty("contactPicture")
    private String contactPicture;
    @JsonProperty("newMessage")
    private Boolean newMessage = false;
    @JsonProperty("messages")
    private List<Message> messages = new ArrayList<>();

    public Chat(UserModel user, UserModel contact) {
        this.contactId = contact.getId();
        this.contactName = contact.fullName();
        this.contactPicture = contact.getProfilePicture();

        for(MessageModel message: user.getSentMessages()){
            if(contact.getId().equals(message.getReceiver().getId()) && !message.getSenderHasDeleted()){
                this.messages.add(new Message(message));
            }
        }

        for(MessageModel message: user.getReceivedMessages()){
            if(contact.getId().equals(message.getSender().getId()) && !message.getReceiverHasDeleted()){
                this.messages.add(new Message(message));
                if(!message.getIsRead())
                    this.newMessage = true;
            }
        }
    }

}
