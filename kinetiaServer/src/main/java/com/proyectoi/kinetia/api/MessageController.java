package com.proyectoi.kinetia.api;

import com.proyectoi.kinetia.models.MessageModel;
import com.proyectoi.kinetia.services.MessageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public Boolean sendMessage(@RequestBody MessageModel message) {
        return messageService.saveMessage(message);
    }

    @PutMapping(path = "{id}")
    public Boolean readMessage(@PathVariable("id") Long idMessage) {
        return messageService.updateMessage(idMessage);
    }

    @DeleteMapping(path = "/{userId}/{contactId}")
    public Boolean deleteChat(@PathVariable("userId") Long userId, @PathVariable("contactId") Long contactId) {
        return messageService.deleteChat(userId, contactId);
    }

}
