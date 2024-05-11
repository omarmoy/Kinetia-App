package com.proyectoi.kinetia.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyectoi.kinetia.models.UserModel;

public class Reservation {
    @JsonProperty("contactId")
    private Long contactId;
    @JsonProperty("contactName")
    private String contactName;
    @JsonProperty("contactPicture")
    private String contactPicture;

    public Reservation() {
    }

    public Reservation(UserModel contact) {
        this.contactId = contact.getId();
        this.contactName = contact.fullName();
        this.contactPicture = contact.getProfilePicture();
    }
}
