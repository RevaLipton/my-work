package com.techelevator.tenmo.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.dao.TransferSqlDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.dao.UserSqlDAO;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;

@PreAuthorize("isAuthenticated()")
@RestController

public class TransferController {
	
	private TransferDAO dao;
	private UserDAO userDao; // added to use principal
	public TransferController(TransferDAO dao, UserDAO userDao) {
		this.dao = dao;
		this.userDao = userDao; // added to use principal
	}
	
	//*********  WE REMOVED '/' IN FRONT OF ALL PATHS
	//                     vvvv
	//*********  Switched int accountFrom to Principal principal in parameters
	
	@RequestMapping(value = "accounts/balance", method = RequestMethod.PUT)
	public void sendTransfer(@Valid @RequestBody TransferDTO transferDto, Principal principal) {
		int userId = userDao.findIdByUsername(principal.getName());
		dao.sendTransfer(userId, transferDto.getTransferToId(), transferDto.getAmount());
		return;
	}
	
	//@RequestMapping(value = "/transfers", method = RequestMethod.GET)
	//public List<Transfer> getTransfersForThisUser(int userId) {
	//	return dao.getTransfersForThisUser(userId);
	//}
	
	@RequestMapping(value = "transfers", method = RequestMethod.GET )
	public List<Transfer> getTransfersForThisUser(Principal principal){ // implemented same technique to get userId as done in AuctionController 
		System.out.println("Username is: " + principal.getName());
		int userId = userDao.findIdByUsername(principal.getName());
		return dao.getTransfersForThisUser(userId);
		
	}
	
	
	@RequestMapping(value = "transfers/{id}", method = RequestMethod.GET)
	public Transfer getTransferByTransferId(@Valid @PathVariable long id) {
		return dao.getTransferByTransferId(id);
	}
	
	
	  @RequestMapping(value = "availabletransfers", method = RequestMethod.GET)
	  public List<Account> availableTransfers(Principal principal) { int
	  accountFrom = userDao.findIdByUsername(principal.getName()); return
	  dao.getAvailableTransfers(accountFrom); }
	 

	
	
	
	
	
	
	
}
