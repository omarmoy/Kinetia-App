package com.proyectoi.kinetia.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyectoi.kinetia.models.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class User extends SimpleUser{

    @JsonProperty("advertisements")
    private List<Advertisement> advertisements = new ArrayList<>();
    @JsonProperty("activitiesOffered")
    private List<ActivityPro> activitiesOffered = new ArrayList<>();
    @JsonProperty("activitiesFav")
    private List<Activity> activitiesFav = new ArrayList<>();
    @JsonProperty("activitiesReserved")
    private List<Activity> activitiesReserved = new ArrayList<>();
    @JsonProperty("chats")
    private List<Chat> chats = new ArrayList<>();

    public User(UserModel user) {
        super(user);

        for (AdvertisementModel am : user.getAdvertisements()) {
            this.advertisements.add(new Advertisement(am));
        }

        for (ActivityModel activity : user.getActivitiesOffered()) {
            this.activitiesOffered.add(new ActivityPro(activity));
        }
        for (ActivityModel activity : user.getActivitiesFav()) {
            this.activitiesFav.add(new Activity(activity));
        }
        for (ActivityModel activity : user.getActivitiesReserved()) {
            this.activitiesReserved.add(new Activity(activity));
        }

        Set<UserModel> contacts = new HashSet<>();

        for (MessageModel message : user.getSentMessages()) {
            if (!message.getSenderHasDeleted())
                contacts.add(message.getReceiver());
        }
        for (MessageModel message : user.getReceivedMessages()) {
            if (!message.getReceiverHasDeleted())
                contacts.add(message.getSender());
        }

        for (UserModel contact : contacts) {
            this.chats.add(new Chat(user, contact));
        }
    }


}
