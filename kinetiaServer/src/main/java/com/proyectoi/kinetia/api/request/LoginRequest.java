package com.proyectoi.kinetia.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {

    @JsonProperty
    private String email;
    @JsonProperty
    private String password;

    public String getEmail() { return email; }

    public String getPassword() { return password; }
}
