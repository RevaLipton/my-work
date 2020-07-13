package com.techelevator.tenmo.dao;
import java.util.List;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

public interface TransferDAO {
	
	void sendTransfer(int accountFrom, int accountTo, Double amount);
	
	List<Transfer> getTransfersForThisUser(int userId);
	
	Transfer getTransferByTransferId(long transferId); //should it be specific for the user or can anyone see anyones everything
	
	List<Account> getAvailableTransfers(int accountFrom);
	

}
