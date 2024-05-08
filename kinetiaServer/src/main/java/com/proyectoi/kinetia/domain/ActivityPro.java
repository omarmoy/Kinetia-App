package com.proyectoi.kinetia.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyectoi.kinetia.models.ActivityModel;
import com.proyectoi.kinetia.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class ActivityPro extends Activity{

    @JsonProperty("reservations")
    private List<Reservation> reservations = new ArrayList<Reservation>();

    public ActivityPro(ActivityModel activity) {
        super(activity);
        for(UserModel user : activity.getReservations()){
            reservations.add(new Reservation(user));
        }
    }
}
