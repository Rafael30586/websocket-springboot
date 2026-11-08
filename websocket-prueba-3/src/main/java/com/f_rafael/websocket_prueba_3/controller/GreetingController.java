package com.f_rafael.websocket_prueba_3.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.f_rafael.websocket_prueba_3.model.GreetingRequest;
import com.f_rafael.websocket_prueba_3.model.GreetingResponse;

@Controller
public class GreetingController {
	
	@MessageMapping("/message")
	@SendTo("/topic/greetings")
	GreetingResponse greet(GreetingRequest request) {
		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
		return new GreetingResponse("Hola, "+request.name() + " - "+time);
	}
	
	@MessageMapping("/private")
	@SendToUser("/topic/greetings")
	GreetingResponse greet_private(GreetingRequest request, SimpMessageHeaderAccessor headerAccesor) {
		if(!request.sessionId().isEmpty()) {
			headerAccesor.setSessionId(request.sessionId());
		}
		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
		return new GreetingResponse("Hello, "+ request.name()+ " - "+ time);
	}
	
	@MessageMapping("/connected")
	@SendToUser("/topic/connected")
	String connected(SimpMessageHeaderAccessor headerAccesor) {
		return "Connected with sessionId: " + headerAccesor.getSessionId();
	}

}
