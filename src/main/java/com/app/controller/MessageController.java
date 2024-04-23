package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


import com.app.entity.Message;

@Controller
public class MessageController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    // Mapped as /app/application
    @MessageMapping("/application")// client that sends the messages to /app/application will route it below url
    @SendTo("/all/messages")// if the receiver is subscribed to this url then only he will recieve the messages from above url
    public Message send(final Message message) throws Exception {
        return message;
    }

    // Mapped as /app/private
    @MessageMapping("/private")// client that sends message to /private will be handled here
    public void sendToSpecificUser(@Payload Message message)//@Payload ensures that the Message object sent by the client is mapped to the message parameter of the sendToSpecificUser method. 
    {
        simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/specific", message);
    }
    
    
}
