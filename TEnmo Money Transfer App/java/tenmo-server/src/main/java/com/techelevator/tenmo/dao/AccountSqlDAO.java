package com.techelevator.tenmo.dao;



import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.tenmo.model.Account;

@Service
public class AccountSqlDAO implements AccountDAO {
	
	private JdbcTemplate jdbcTemplate;
	//public AccountSqlDAO() {};
    public AccountSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	@Override
	public Account getAccountById(int userId) {
		Account anAccount = new Account(); 
		String anAccountSQL = "Select account_id, balance From accounts Where user_id = ?";		
		SqlRowSet theAccountSet = jdbcTemplate.queryForRowSet(anAccountSQL, userId);
		
		if(theAccountSet.next()) {
			anAccount = mapRowToUser(theAccountSet);
		}
		return anAccount;
	}

	@Override
	public Account getAccountByUserName(String userName) {
		Account anAccount = null; 
		String anAccountSQL = "Select account_id, balance From accounts Where user_id IN (select user_id from user where username = ?)";		
		SqlRowSet theAccountSet = jdbcTemplate.queryForRowSet(anAccountSQL, userName);
		
		if(theAccountSet.next()) {
			anAccount = mapRowToUser(theAccountSet);
		}
		return anAccount;
	}
		
	

	@Override
	public Double viewBalance(long accountId) { // we switched from double to BigDecimal to Double  
		Double thisAccountBalance = null; // set to null in case empty 
		System.out.println("View Balance got account id: " + accountId);
		String aBalanceSQL = "Select balance From accounts Where account_id = ?";
		SqlRowSet theBalanceSet = jdbcTemplate.queryForRowSet(aBalanceSQL, accountId);
		
		if(theBalanceSet.next()) {
			thisAccountBalance = theBalanceSet.getDouble("balance") ;
		}
		return thisAccountBalance;
	}

	//@Override
	//public void updateBalance(Account account) {
		   
	//}
	
	
    private Account mapRowToUser(SqlRowSet rs) {
        Account account = new Account();
        account.setAccountId(rs.getLong("account_id"));
        account.setUserId(rs.getInt("user_id"));
        account.setBalance(rs.getDouble("balance")); // switched to BigDecimal to Double 
        return account;
        
    }

	//@Override
	//public void updateBalance(Account account) {
		// TODO Auto-generated method stub
		
	//}
    
	

}
