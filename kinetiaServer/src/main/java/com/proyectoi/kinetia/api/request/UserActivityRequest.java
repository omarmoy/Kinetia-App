package com.proyectoi.kinetia.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserActivityRequest {

    @JsonProperty
    private Long userId;
    @JsonProperty
    private Long activityId;

    public Long getUserId() { return userId; }

    public Long getActivityId() { return activityId; }
}
