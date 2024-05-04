package com.proyectoi.kinetia.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VerifyRequest {

    @JsonProperty
    private String value;

    public String getValue() {
        return value;
    }
}
