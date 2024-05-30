package com.proyectoi.kinetia.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.proyectoi.kinetia.api.request.SignUpRequest;
import com.proyectoi.kinetia.domain.Reservation;
import com.proyectoi.kinetia.domain.SimpleUser;
import com.proyectoi.kinetia.domain.User;
import com.proyectoi.kinetia.models.*;
import com.proyectoi.kinetia.repositories.*;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final IUserRepository userRepository;
    private final IRolRepository rolRepository;
    private final IActivityRepository activityRepository;
    private final IMessageRepository messageRepository;

    public UserService(
            IUserRepository userRepository,
            IRolRepository rolRepository,
            IActivityRepository activityRepository,
            IMessageRepository messageRepository
    ) {
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.activityRepository = activityRepository;
        this.messageRepository = messageRepository;
    }

    public List<SimpleUser> getAll() {
        List<UserModel> users = userRepository.findAll();
        List<SimpleUser> usersArray = new ArrayList<>();
        for (UserModel user : users) {
            usersArray.add(new SimpleUser(user));
        }
        return usersArray;
    }

    public Optional<User> logIn(String email, String password) {
        try {
            UserModel userModel = userRepository.findByEmail(email);
            User user = new User(userModel);
            if (userModel.getPassword().equals(password)) {
                return Optional.of(user);
            }
        } catch (Exception ignored) {
        }

        return Optional.empty();
    }

    public Boolean createUser(SignUpRequest signUpRequest) {
        try {
            RoleModel role = rolRepository.findByRoleType(signUpRequest.getRole());
            UserModel userModel = new UserModel(
                    signUpRequest.getEmail(),
                    role,
                    signUpRequest.getPassword(),
                    signUpRequest.getName(),
                    signUpRequest.getSurname(),
                    signUpRequest.getSecondSurname(),
                    signUpRequest.getBirthDate(),
                    signUpRequest.getProfilePicture(),
                    signUpRequest.getCompany(),
                    signUpRequest.getCif(),
                    signUpRequest.getAddress()
            );
            userRepository.save(userModel);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Boolean verify(String word) {
        UserModel userEmail = userRepository.findByEmail(word);
        UserModel userCif = userRepository.findByCif(word);
        return userCif != null || userEmail != null;
    }

    public Boolean updateUser(UserModel user) {
        Optional<UserModel> optional = userRepository.findById(user.getId());
        if (optional.isPresent()) {
            userRepository.save(user);
            return true;
        }
        return false;
    }


    public Boolean deleteUser(Long id) {
        try {
            UserModel user = userRepository.findById(id).orElseThrow();

            //delete messages
            for (MessageModel m : user.getReceivedMessages()) {
                messageRepository.deleteById(m.getId());
            }
            for (MessageModel m : user.getSentMessages()) {
                messageRepository.deleteById(m.getId());
            }

            //cancel activities reserved
            for (ActivityModel activityReserved : user.getActivitiesReserved()) {
                for (UserModel u : activityReserved.getReservations()) {
                    activityReserved.cancelReservation(u);
                }
                activityRepository.save(activityReserved);
            }

            //delete reservations and fav of activities offered
            for (ActivityModel activityOffered : user.getActivitiesOffered()) {
                for (UserModel u : activityOffered.getReservations()) {
                    activityOffered.cancelReservation(u);
                }

                for (UserModel uFav : activityOffered.getUsersWhoFav()) {
                    deleteFav(uFav.getId(), activityOffered.getId());
                }
                activityRepository.save(activityOffered);
            }

            //delete activities fav
            user.getActivitiesFav().clear();

            userRepository.delete(user);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    /*FAVORITES*/

    public Boolean addFav(Long userId, Long activityId) {
        try {
            Optional<UserModel> user = userRepository.findById(userId);
            Optional<ActivityModel> activity = activityRepository.findById(activityId);
            if (user.isPresent() && activity.isPresent() && user.get().addFav(activity.get())) {
                userRepository.save(user.get());
                return true;
            } else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean deleteFav(Long userId, Long activityId) {
        try {
            Optional<UserModel> user = userRepository.findById(userId);
            Optional<ActivityModel> activity = activityRepository.findById(activityId);
            if (user.isPresent() && activity.isPresent()) {
                user.get().deleteFav(activity.get());
                userRepository.save(user.get());
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /*RESERVATION*/

    public Boolean addReservation(Long userId, Long idActivity) {
        try {
            Optional<UserModel> user = userRepository.findById(userId);
            Optional<ActivityModel> activity = activityRepository.findById(idActivity);
            if (user.isPresent() && activity.isPresent() && activity.get().addReservation(user.get())) {
                activityRepository.save(activity.get());
                return true;
            } else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean cancelReservation(Long userId, Long idActivity) {
        try {
            Optional<UserModel> user = userRepository.findById(userId);
            Optional<ActivityModel> activity = activityRepository.findById(idActivity);
            if (user.isPresent() && activity.isPresent()) {
                activity.get().cancelReservation(user.get());
                activityRepository.save(activity.get());
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
