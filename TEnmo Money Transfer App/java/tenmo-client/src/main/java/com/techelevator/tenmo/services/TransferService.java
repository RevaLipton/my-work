package com.techelevator.tenmo.services;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.TransferDTO;

public class TransferService {

	public static String AUTH_TOKEN = "";
	private final String BASE_URL;  
	private final RestTemplate restTemplate = new RestTemplate();

	public TransferService(String url) {
	    this.BASE_URL = url;             // What is this URL? Is it defined in app?
	  }
	
	
	// @SuppressWarnings("unchecked")  // <<< Is this okay?
	
	public Transfer[] getTransfersForThisUser() {
		Transfer[] theTransfers;
		theTransfers = restTemplate.exchange(BASE_URL + "transfers", HttpMethod.GET, makeAuthEntity(), Transfer[].class).getBody();
		return theTransfers;
	}
	
	public Transfer getTransferById() {
		Transfer aTransfer = null;
		aTransfer = restTemplate.exchange(BASE_URL + "transfers/{id}", HttpMethod.GET, makeAuthEntity(), Transfer.class).getBody();
		return aTransfer;
	}
	
	//***************************  QUESTION FOR FRANK
	
	// Do we need parameters in this method?  (int id, Double amount)
	
	public void sendTransfer(TransferDTO transferDto) {
		
		restTemplate.exchange(BASE_URL + "accounts/balance", HttpMethod.PUT, makeAuthDTO(transferDto), String.class);
	}
	
	// ***************************************************
	
	public Account[] availableAccounts() {
		Account[] theAccounts;
		theAccounts = restTemplate.exchange(BASE_URL + "availabletransfers", HttpMethod.GET, makeAuthEntity(),Account[].class).getBody();
		return theAccounts;
	}
	
	
	/*
	 * public ResponseEntity<Account[]> availableTransfers() {
	 * ResponseEntity<Account[]> availableTransfers = null; availableTransfers =
	 * restTemplate.exchange(BASE_URL + "availabletransfers", HttpMethod.GET,
	 * makeAuthEntity(), Account[].class); return availableTransfers; }
	 */
	
	 
	
	
	//HELPER METHOD
	
		private HttpEntity makeAuthEntity() {
			//System.out.println("Auth token is: " + AUTH_TOKEN); commented out cuz was for testing 
		    HttpHeaders headers = new HttpHeaders();
		    headers.setBearerAuth(AUTH_TOKEN);
		    HttpEntity entity = new HttpEntity<>(headers);
		    return entity;
		  }
		
		private HttpEntity makeAuthDTO(TransferDTO transferDto) {
			//System.out.println("Auth token is: " + AUTH_TOKEN); commented out cuz was for testing 
		    HttpHeaders headers = new HttpHeaders();
		    headers.setBearerAuth(AUTH_TOKEN);
		    HttpEntity entity = new HttpEntity<>(transferDto, headers);
		    return entity;
		  }
}
