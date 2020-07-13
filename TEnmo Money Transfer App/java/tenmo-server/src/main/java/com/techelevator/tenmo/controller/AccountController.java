package com.techelevator.tenmo.controller;


import java.security.Principal;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.AccountSqlDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.dao.UserSqlDAO;
import com.techelevator.tenmo.model.Account;


@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	private AccountDAO accountDao;
	private UserDAO userDao; // added to use principal
	public AccountController(AccountDAO accountDao, UserDAO userDao) { // should we make diff constructor 
		this.accountDao = accountDao;
		this.userDao = userDao;// added to use principal
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Account getAccountById(@Valid @PathVariable int id) {
		return accountDao.getAccountById(id);
	}
	
	//@RequestMapping(value = "", method = RequestMethod.GET)// do we want to incorporate Principal in this??? 
	//public BigDecimal getBalance(long accountId) {
	//	return accountDao.viewBalance(accountId);
	//}
	
	// i figured out this solution and ran it past frank... he approved :)
	//@PreAuthorize("permitAll")
	@RequestMapping(value = "/balance", method = RequestMethod.GET)// is this the proper alternative to getBalance()
	public Double getBalance(Principal principal) {           // YES! yayy
		System.out.println("principal name: " + principal.getName());
		int userId = userDao.findIdByUsername(principal.getName());
		System.out.println("userId is: " + userId);
		return accountDao.viewBalance(userId);
		
	}
	
	@PreAuthorize("permitAll")// added so that we can test 
	@RequestMapping(value = "/name", method = RequestMethod.GET) // is this working??? what are we doing???
	public Account getAccountByName(Principal principal) {	// YES!! yayy 
		return accountDao.getAccountByUserName(principal.getName());
	}




//HELPER METHODS
	//private Principal getCurrentUserId(Principal principal) {
		//return principal;
	//}
	







}
	
	// public Account getAccountByName(@Valid)
	

