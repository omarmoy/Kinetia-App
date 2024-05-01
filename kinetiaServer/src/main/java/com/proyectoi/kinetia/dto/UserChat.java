package com.proyectoi.kinetia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyectoi.kinetia.models.*;

import java.util.ArrayList;
import java.util.List;

public class UserChat {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("contactName")
    private String contactName;
    @JsonProperty("contactPicture")
    private String contactPicture;
    @JsonProperty("newMessage")
    private Boolean newMessage = false;
    @JsonProperty("messages")
    private List<Message> messages = new ArrayList<>();

    public UserChat(UserModel user, UserModel contact) {
        this.id = contact.getId();
        this.contactName = contact.fullName();
        this.contactPicture = contact.getProfilePicture();

        for(MessageModel message: user.getSentMessages()){
            if(contact.getId().equals(message.getReceiver().getId())){
                this.messages.add(new Message(message));
            }
        }

        for(MessageModel message: user.getReceivedMessages()){
            if(contact.getId().equals(message.getSender().getId())){
                this.messages.add(new Message(message));
                if(!message.getRead())
                    newMessage = true;
            }
        }
    }

}
