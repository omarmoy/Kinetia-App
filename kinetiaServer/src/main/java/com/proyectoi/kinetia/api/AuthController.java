package com.proyectoi.kinetia.api;

import com.proyectoi.kinetia.api.request.UserActivityRequest;
import com.proyectoi.kinetia.api.request.LoginRequest;
import com.proyectoi.kinetia.api.request.SignUpRequest;
import com.proyectoi.kinetia.api.request.VerifyRequest;
import com.proyectoi.kinetia.domain.SimpleUser;
import com.proyectoi.kinetia.domain.User;
import com.proyectoi.kinetia.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public List<SimpleUser> getAllUser() {
        return userService.getAll();
    }

    @PostMapping(path = "/login")
    public User login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Optional<User> user = userService.logIn(email, password);
        return user.orElse(null);
    }

    @PostMapping(path = "/signUp")
    public Boolean signUp(@RequestBody SignUpRequest user) {
        return userService.createUser(user);
    }

    @PostMapping(path = "/verify")
    public Boolean verify(@RequestBody VerifyRequest verifyRequest) {
        return userService.verify(verifyRequest.getValue());
    }

    //TODO: moficiar usuario, cuando se pueda desde el front

    @DeleteMapping(path = "/{userId}")
    public Boolean deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }



    @PostMapping(path = "/fav")
    public Boolean favActivity(@RequestBody UserActivityRequest favRequest) {
        return userService.addFav(favRequest.getUserId(), favRequest.getActivityId());
    }

    @DeleteMapping(path = "/fav")
    public Boolean noFavActivity(@RequestBody UserActivityRequest favRequest) {
        return userService.deleteFav(favRequest.getUserId(), favRequest.getActivityId());
    }



    @PostMapping(path = "/reservation")
    public Boolean reserveActivity(@RequestBody UserActivityRequest reservationRequest) {
        //TODO: avisar al otro usuario
        return userService.addReservation(reservationRequest.getUserId(), reservationRequest.getActivityId());
    }

    @DeleteMapping(path = "/reservation")
    public Boolean cancelReservation(@RequestBody UserActivityRequest reservationRequest) {
        //TODO: avisar al otro usuario, dependiéndo de quién cancele, ofertante o consumidor
        return userService.cancelReservation(reservationRequest.getUserId(), reservationRequest.getActivityId());
    }


}
