package com.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketBrokerConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/all","/specific");// all to broadcast to everyone, specific to send to specific user 
        config.setApplicationDestinationPrefixes("/app");// /app prefix messages are sent to controller
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
         //registry.addEndpoint("/ws");
         registry.addEndpoint("/ws").withSockJS(); //server url
    }
}
