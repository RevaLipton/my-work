package com.techelevator.tenmo.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

@Service
public class TransferSqlDAO implements TransferDAO{

	private JdbcTemplate jdbcTemplate;

	//public TransferSqlDAO() {}

	public TransferSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}




	@Override        
	public void sendTransfer(int accountFrom, int accountTo, Double amount) {   // IF THIS DOES NOT WORK USE 2 STRINGS AND 2 JDBC UPDATES
		System.out.println("Transfer accountFrom: " + accountFrom);
		System.out.println("Transfer accountTo" + accountTo);
		System.out.println("Transfer amount" + amount);


		if (jdbcTemplate == null) {                    
			System.out.println("jdbcTemplate = null ");
		}

		String balanceCheckSQL = "SELECT balance FROM accounts WHERE user_id = ?";
		String sendTransferSQL = "Update accounts Set balance = (balance - ?) where account_id IN(SELECT account_from FROM transfers where account_from = ?)";
		String sendTransferSQL2= "Update accounts Set balance = (balance + ?) where account_id IN(SELECT account_to FROM transfers where account_to = ?)";  // << Removed ';' from SQL String
		String insertTransferSQL = "Insert Into transfers(transfer_type_id, transfer_status_id, account_from, account_to, amount) values(?,?,?,?,?)";
		String checkIfAccountExistsSQL = "Select account_id from accounts Where account_id = ?"; // added to check if account exists

		SqlRowSet accountSet = jdbcTemplate.queryForRowSet(checkIfAccountExistsSQL, accountTo); // added to check if account exists 
		SqlRowSet balanceSet = jdbcTemplate.queryForRowSet(balanceCheckSQL, accountFrom);
		Double balance = 0.0;

		if(accountSet.next()) { // added to check if the account you are trying to transfer to exists 

			if (balanceSet.next()) {
				balance = balanceSet.getDouble("balance");
			}



			if (balance >= amount) {

				jdbcTemplate.update(insertTransferSQL, 2, 2, accountFrom, accountTo, amount);

				jdbcTemplate.update(sendTransferSQL, amount, accountFrom);

				jdbcTemplate.update(sendTransferSQL2, amount, accountTo);


			}

			else if (balance < amount) {
				System.out.println("Invalid funds for transfer by this user.");
			}
		}

		else {
			System.out.println("Account you wish to transfer funds to does not exist.");
		}

	} 
	//return;



	@Override
	public List<Transfer> getTransfersForThisUser(int userId) {
		List<Transfer> transfersForThisUser = new ArrayList<Transfer>();
		String transfersForThisUserSQL = "Select * From transfers Where account_to IN (Select account_id From accounts Where user_id IN (Select user_id From users where user_id = ?)) OR account_from IN (Select account_id From accounts Where user_id IN (Select user_id From users where user_id = ?))";

		SqlRowSet transferSet = jdbcTemplate.queryForRowSet(transfersForThisUserSQL, userId, userId);

		while(transferSet.next()) {
			Transfer aTransfer = mapRowToTransfer(transferSet);
			System.out.println("rowset = : " + transferSet); // added here to see if code gets to here
			transfersForThisUser.add(aTransfer);
		}
		return transfersForThisUser;
	}

	@Override
	public Transfer getTransferByTransferId(long transferId) {  // Can anyone see everyone's anything?
		Transfer transferForThisId = new Transfer();
		String transferForThisIdSQL = "Select * From transfers where transfer_id = ?";

		SqlRowSet transferSet = jdbcTemplate.queryForRowSet(transferForThisIdSQL, transferId);

		if (transferSet.next()) {
			transferForThisId = mapRowToTransfer(transferSet);
		}

		return transferForThisId;
	}
	
	/*
	 * @Override public String getAvailableTransfers(int accountFrom) { String
	 * availableResults; String getAvailableTransfersSQL =
	 * "Select * From accounts where account_id != ?";
	 * 
	 * SqlRowSet transferSet = jdbcTemplate.queryForRowSet(getAvailableTransfersSQL,
	 * accountFrom);
	 * 
	 * if (transferSet.next()) { availableResults = mapRowToTransfer(transferSet); }
	 * 
	 * return availableResults; }
	 */
	
	
	  @Override public List<Account> getAvailableTransfers(int accountFrom) {
	  List<Account> accountsAvailable = new ArrayList<Account>(); 
	  String accountsAvailableSQL = "Select * From accounts Where account_id != ?";
	  
	  SqlRowSet accountSet = jdbcTemplate.queryForRowSet(accountsAvailableSQL,
	  accountFrom);
	  
	  while(accountSet.next()) { 
		  Account anAccount = mapRowToAccount(accountSet);
	  System.out.println("Available Account = : " + anAccount); // added here to see if code gets to here 
	  accountsAvailable.add(anAccount); 
	  } 
	  return accountsAvailable; }
	 


	private Transfer mapRowToTransfer(SqlRowSet rs) {
		Transfer transfer = new Transfer();
		transfer.setTransferId(rs.getLong("transfer_id"));
		transfer.setTransferType(rs.getInt("transfer_type_id"));
		transfer.setStatusId(rs.getInt("transfer_status_id"));
		transfer.setAccountFrom(rs.getInt("account_from"));
		transfer.setAccountTo(rs.getInt("account_to"));
		transfer.setAmount(rs.getDouble("amount")); // switched to BigDecimal to Double 
		return transfer;

	}
	
	private Account mapRowToAccount(SqlRowSet rs) {
		Account account = new Account();
		account.setAccountId(rs.getLong("account_id"));
		account.setBalance(rs.getDouble("balance"));
		account.setUserId(rs.getInt("user_id"));
		return account;

	}

}
