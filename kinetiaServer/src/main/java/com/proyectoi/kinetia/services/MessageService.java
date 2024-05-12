package com.proyectoi.kinetia.services;

import com.proyectoi.kinetia.domain.Message;
import com.proyectoi.kinetia.models.MessageModel;
import com.proyectoi.kinetia.models.UserModel;
import com.proyectoi.kinetia.repositories.IMessageRepository;
import com.proyectoi.kinetia.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MessageService {

    private final IMessageRepository messageRepository;
    private final IUserRepository userRepository;

    public MessageService(IMessageRepository messageRepository, IUserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public Boolean saveMessage(Message message) {
        try {
            UserModel sender = userRepository.findById(message.getSender()).orElseThrow();
            UserModel recipient = userRepository.findById(message.getRecipient()).orElseThrow();
            MessageModel messageModel = new MessageModel(message);
            messageModel.setSender(sender);
            messageModel.setReceiver(recipient);
            messageRepository.save(messageModel);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean updateMessage(Long id) {
        try {
            Optional<MessageModel> optional = messageRepository.findById(id);
            if (optional.isPresent()) {
                optional.get().setIsRead(true);
                messageRepository.save(optional.get());
                return true;
            }
        } catch (Exception ignored) {
        }
        return false;
    }

    public Boolean deleteChat(Long idUser, Long idContact) {

        try {
            Optional<UserModel> user = userRepository.findById(idUser);
            Optional<UserModel> contact = userRepository.findById(idContact);

            if (user.isPresent() && contact.isPresent()) {

                ArrayList<MessageModel> messagesSent = messageRepository.findBySenderAndReceiver(user.get(), contact.get());
                for (MessageModel message : messagesSent) {
                    message.setSenderHasDeleted(true);
                    if (message.getSenderHasDeleted() && message.getReceiverHasDeleted())
                        messageRepository.delete(message);
                    else
                        messageRepository.save(message);
                }

                ArrayList<MessageModel> messagesReceived = messageRepository.findBySenderAndReceiver(contact.get(), user.get());
                for (MessageModel message : messagesReceived) {
                    message.setReceiverHasDeleted(true);
                    if (message.getSenderHasDeleted() && message.getReceiverHasDeleted())
                        messageRepository.delete(message);
                    else
                        messageRepository.save(message);
                }

                return true;
            }
        } catch (Exception ignored) {
        }

        return false;
    }

}
