package com.kunlinr.lifealien.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import com.kunlinr.lifealien.websocket.server.SocketServer;

@Configuration
public class WebSocketConfiguration {
	
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}
	
	@Bean
	public SocketServer socketServer() {
		return new SocketServer();
	}
}
