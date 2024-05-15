package com.proyectoi.kinetia.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyectoi.kinetia.models.RoleModel;

import java.time.LocalDate;

public class SignUpRequest {

    @JsonProperty("email")
    private String email;
    @JsonProperty("role")
    private RoleModel.RoleType role;
    @JsonProperty("password")
    private String password;
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
    @JsonProperty("address")
    private String address;

    public String getEmail() {
        return email;
    }

    public RoleModel.RoleType getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getCompany() {
        return company;
    }

    public String getCif() {
        return cif;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "SignUpRequest{" +
                "email='" + email + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", birthDate=" + birthDate +
                ", profilePicture='" + profilePicture + '\'' +
                ", company='" + company + '\'' +
                ", cif='" + cif + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

