package com.f_rafael.websocket_prueba_2.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.f_rafael.websocket_prueba_2.model.GreetingRequest;
import com.f_rafael.websocket_prueba_2.model.GreetingResponse;

@Controller
public class GreetingController {
	
	@MessageMapping("/message")
	@SendTo("/topic/greetings")
	GreetingResponse greet(GreetingRequest request) {
		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
		return new GreetingResponse("Hola, "+request.name() + " - "+time);
	}

}
