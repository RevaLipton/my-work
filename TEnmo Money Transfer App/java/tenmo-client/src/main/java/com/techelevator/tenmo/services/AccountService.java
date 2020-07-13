package com.techelevator.tenmo.services;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.Account;

public class AccountService {
	
//  May have to remove '/' at end OR remove '/' from Controllers
	
	public static String AUTH_TOKEN = "";
	private final String BASE_URL;  
	private final RestTemplate restTemplate = new RestTemplate();

	public AccountService(String url) {
	    this.BASE_URL = url;   // What is this URL? Is it defined in app?
	  }
	
	
	public Double getBalance() {// do we need any parameters if we take care of Entity and Headers and Token in makeAuthEntity 
		Double balance = null;
		// come back to: add try catch statements and a ServiceException class 
		balance = restTemplate.exchange(BASE_URL + "accounts/balance", HttpMethod.GET, makeAuthEntity(), Double.class).getBody();
												// is the path correct??
		return balance;
	}
	
	public Account getAccountById() {
		Account anAccount = null;
		anAccount = restTemplate.exchange(BASE_URL + "accounts/{id}", HttpMethod.GET, makeAuthEntity(), Account.class).getBody();
		return anAccount;
	}
	
	public Account getAccountByName() {
		Account anAccount = null;
		anAccount = restTemplate.exchange(BASE_URL + "accounts/name", HttpMethod.GET, makeAuthEntity(), Account.class).getBody();
		return anAccount;
	}
	
	
	//HELPER METHOD
	
	private HttpEntity makeAuthEntity() {
	   //System.out.println("the auth token is :" + AUTH_TOKEN); commented out cuz just for testing 
		HttpHeaders headers = new HttpHeaders();
	    headers.setBearerAuth(AUTH_TOKEN);
	    HttpEntity entity = new HttpEntity<>(headers);
	    return entity;
	  }
}
