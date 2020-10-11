package com.astrokoala.services.register_service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RegisterService {
		
	private static final String ENDPOINT = "http://localhost:8080/register";
	
	private RegisterService() { }
	
	public static boolean register(String email, String pass, String username) {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
    		.uri(URI.create(ENDPOINT 
    				+ "?email=" + email 
    				+ "&pass=" + pass
    				+ "&username=" + username
    		))
        .build();
		try {
			return parseJsonResponse(client.send(request, HttpResponse.BodyHandlers.ofString()).body());
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
			ie.printStackTrace();
		}
		return false;
  }

	private static boolean parseJsonResponse(String json) {
		HashMap<?, ?> response = new HashMap<>();
		try {
			response = new ObjectMapper().readValue(json, HashMap.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return (boolean) response.get("success");
	}
}
