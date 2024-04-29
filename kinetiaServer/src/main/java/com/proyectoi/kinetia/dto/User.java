package com.proyectoi.kinetia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyectoi.kinetia.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class User {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("rol")
    private RolModel.RolType rol;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("secondSurname")
    private String secondSurname;
    @JsonProperty("birthDate")
    private LocalDate birthDate;
    @JsonProperty("profilePicture")
    private String profilePicture;
    @JsonProperty("company")
    private String company;
    @JsonProperty("cif")
    private String cif;
    @JsonProperty("adress")
    private String adress;
    @JsonProperty("advertisements")
    private List<AdvertisementModel> advertisements = new ArrayList<>();
    @JsonProperty("activitiesOfered")
	private List<UserActivity> activitiesOffered = new ArrayList<>();
    @JsonProperty("activitiesFav")
    private List<UserActivity> activitiesFav = new ArrayList<>();
    @JsonProperty("activitiesReserved")
	private List<UserActivity> activitiesReserved = new ArrayList<>();
    @JsonProperty("contacts")
    private List<UserContact> contacts = new ArrayList<>();

    public User(UserModel user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.rol = user.getRol().getRolType();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.secondSurname = user.getSecondSurname();
        this.birthDate = user.getBirthDate();
        this.profilePicture = user.getProfilePicture();
        this.company = user.getCompany();
        this.cif = user.getCif();
        this.adress = user.getAdress();
        this.advertisements = user.getAdvertisements();

        for (ActivityModel activity : user.getActivitiesOffered()) {
            this.activitiesOffered.add(new UserActivity(activity));
        }
        for (ActivityModel activity : user.getActivitiesFav()) {
            this.activitiesFav.add(new UserActivity(activity));
        }
        for (ActivityModel activity : user.getActivitiesReserved()) {
            this.activitiesReserved.add(new UserActivity(activity));
        }

        Set<UserModel> contacts = new HashSet<>();

        for (MessageModel message : user.getSentMessages()) {
            contacts.add(message.getRecipient());
        }
        for (MessageModel message : user.getReceivedMessages()) {
            contacts.add(message.getRecipient());
        }

        for (UserModel contact : contacts){
            this.contacts.add(new UserContact(user, contact));
        }


    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", rol=" + rol +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", birthDate=" + birthDate +
                ", profilePicture='" + profilePicture + '\'' +
                ", company='" + company + '\'' +
                ", cif='" + cif + '\'' +
                ", adress='" + adress + '\'' +
                ", advertisements=" + advertisements.size() +
                ", activitiesOffered=" + activitiesOffered.size() +
                ", activitiesFav=" + activitiesFav.size() +
                ", activitiesReserved=" + activitiesReserved.size() +
                ", contacts=" + contacts.size() +
                '}';
    }
}
