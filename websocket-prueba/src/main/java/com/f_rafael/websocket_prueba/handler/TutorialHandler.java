package com.f_rafael.websocket_prueba.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TutorialHandler implements WebSocketHandler{
	
	

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.print("Connection established on session: "+ session.getId());
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		String tutorial = (String) message.getPayload();
		System.out.println("Mensaje: "+ tutorial);
		session.sendMessage(new TextMessage("Comenzando procesando tutorial: "+ session + " - "+ tutorial));
		Thread.sleep(1000);
		session.sendMessage(new TextMessage("Completado el procesamiento del tutorial: "+ tutorial));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.out.println("Ocurrió una excepción: "+exception.getMessage()+"en sesión "+session.getId());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.out.println("La conexión se cerró en la sesión: "+session.getId()+" con estado "+ closeStatus.getCode());
	}

	@Override
	public boolean supportsPartialMessages() {

		return false;
	}
	
	
	
	

}
