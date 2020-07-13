package com.techelevator.tenmo.dao;
import java.math.BigDecimal;

import com.techelevator.tenmo.model.Account;

public interface AccountDAO {
	
	Account getAccountById(int userId);
	
	Account getAccountByUserName(String userName);
	
	Double viewBalance(long accountId); // we switched from Account to double to Big Decimal to Double  
	
	//void updateBalance(Account account); come back to when have time 
	
	
}
