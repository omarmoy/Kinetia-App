package com.proyectoi.kinetia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyectoi.kinetia.models.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserContact {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("profilePicture")
    private String profilePicture;
    @JsonProperty("newMessage")
    private Boolean newMessage = false;
    @JsonProperty("messages")
    private List<Message> messages = new ArrayList<>();

    public UserContact(UserModel user, UserModel contact) {
        this.id = contact.getId();
        this.fullName = contact.fullName();
        this.profilePicture = contact.getProfilePicture();

        for(MessageModel message: user.getSentMessages()){
            if(contact.getId().equals(message.getRecipient().getId())){
                this.messages.add(new Message(message));
                if(!message.getRead())
                    newMessage = true;
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
