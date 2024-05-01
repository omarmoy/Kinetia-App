package com.proyectoi.kinetia.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import com.proyectoi.kinetia.models.ActivityModel;
import com.proyectoi.kinetia.models.AdvertisementModel;
import com.proyectoi.kinetia.models.MessageModel;
import com.proyectoi.kinetia.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoi.kinetia.models.UserModel;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IRolRepository rolRepository;

    @Autowired
    IActivityRepository activityRepository;

    @Autowired
    IAdvertisementRepository advertisementRepository;

    @Autowired
    IMessageRepository messageRepository;


    /*BORRAR Y MODIFICAR USUARIO*/

    public Boolean updateUser(UserModel user) {
        //TODO: falta fronted, y creo que así borra todo los datos de las listas?¿?
        Optional<UserModel> optional = userRepository.findById(user.getId());
        if (optional.isPresent()) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public Boolean deleteUser(Long id) {
        try {
            Optional <UserModel> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
//                userOptional.get().setActivitiesOffered(new ArrayList<>());
//                userRepository.save(userOptional.get());
                userRepository.delete(userOptional.get());
                return true;
            }
        } catch (Exception ignored) {
        }

        return false;
    }


    /*CRUD ACTIVITY Y FAV*/

    public Long createActivity(ActivityModel activity) {
        try {
            activityRepository.save(activity);
            return activity.getId();
        } catch (Exception e) {
            return -1L;
        }
    }

    public Boolean updateActivity(ActivityModel activity) {
        Optional<ActivityModel> activityOptional = activityRepository.findById(activity.getId());
        if (activityOptional.isPresent()) {
            activityRepository.save(activity);
            return true;
        }
        return false;
    }

    public Boolean deleteActivity(Long id) {
        try {
            activityRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /*FAVORITOS*/

    public Boolean addFav(Long idUser, Long idActivity) {
        try {
            UserModel user = userRepository.findById(idUser).get();
            ActivityModel activity = activityRepository.findById(idActivity).get();
            if (user.addFav(activity)) {
                userRepository.save(user);
                return true;
            }else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean deleteFav(Long idUser, Long idActivity) {
        try {
            UserModel user = userRepository.findById(idUser).get();
            ActivityModel activity = activityRepository.findById(idActivity).get();
            user.deleteFav(activity);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*RESERVAS*/
    public Boolean addReservation(Long idUser, Long idActivity) {
        try {
            UserModel user = userRepository.findById(idUser).get();
            ActivityModel activity = activityRepository.findById(idActivity).get();
            if (activity.addReservation(user)) {
                activityRepository.save(activity);
                return true;
            }else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean cancelReservation(Long idUser, Long idActivity) {
        try {
            UserModel user = userRepository.findById(idUser).get();
            ActivityModel activity = activityRepository.findById(idActivity).get();
            activity.cancelReservation(user);
            activityRepository.save(activity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /*CRUD DE ANUNCIOS*/

    public Long createAdvertisement(AdvertisementModel advertisement) {
        try {
            advertisementRepository.save(advertisement);
            return advertisement.getId();
        } catch (Exception e) {
            return -1L;
        }
    }

    public Boolean updateAdvertisement(AdvertisementModel advertisement) {
        Optional<AdvertisementModel> optional = advertisementRepository.findById(advertisement.getId());
        if (optional.isPresent()) {
            advertisementRepository.save(advertisement);
            return true;
        }
        return false;
    }

    public Boolean deleteAdvertisement(Long id) {
        try {
            advertisementRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /*CRUD DE MENSAJES*/

    public Boolean saveMessage(MessageModel message) {
        try {
            messageRepository.save(message);
            //TODO: enviar al otro usuario
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean updateMessage(Long id) {
        try {
            Optional<MessageModel> optional = messageRepository.findById(id);
            if (optional.isPresent()) {
                optional.get().setRead(true);
                messageRepository.save(optional.get());
                return true;
            }
        } catch (Exception ignored) {
        }
        return false;
    }

    public Boolean deleteChat(Long idUser, Long idContact) {

        try {
            UserModel user = userRepository.findById(idUser).get();
            UserModel contact = userRepository.findById(idContact).get();

            ArrayList<MessageModel> messagesSent = messageRepository.findBySenderAndReceiver(user, contact);
            for (MessageModel message : messagesSent) {
                message.setSenderHasDeleted(true);
                if (message.getSenderHasDeleted() && message.getReceiverHasDeleted())
                    messageRepository.delete(message);
                else
                    messageRepository.save(message);
            }

            ArrayList<MessageModel> messagesReceived = messageRepository.findBySenderAndReceiver(contact, user);
            for (MessageModel message : messagesReceived) {
                message.setReceiverHasDeleted(true);
                if (message.getSenderHasDeleted() && message.getReceiverHasDeleted())
                    messageRepository.delete(message);
                else
                    messageRepository.save(message);
            }
            return true;
        } catch (Exception e) {
            return false;
        }


    }



}
