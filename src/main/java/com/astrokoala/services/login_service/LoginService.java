package com.astrokoala.services.login_service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.HashMap;

import com.astrokoala.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginService {
		
	private static final String ENDPOINT = "http://localhost:8080/login/";
	
	private LoginService() { }
	
	public static User login(String email, String pass) {
		User user = new User();
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
    		.uri(URI.create(ENDPOINT + "?email=" + email + "&pass=" + pass))
        .build();
		try {
			user = parseJsonIntoUser(client.send(request, HttpResponse.BodyHandlers.ofString()).body());
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
			ie.printStackTrace();
		}
		return user;
  }
	

	private static User parseJsonIntoUser(String json) {
		HashMap<?, ?> response = new HashMap<>();
		try {
			response = new ObjectMapper().readValue(json, HashMap.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new User(
				(int) response.get("id"),
				(String) response.get("email"),
				(String) response.get("username"),
				(LocalDate) response.get("dateCreated")
			);
	}
}
