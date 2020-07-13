package com.techelevator.tenmo.models;

public class Account {


	private Long accountId;
	private int userId;
	private Double balance; // switched to BigDecimal to Double 
	
	public Account() {}
	
	public Account(Long accountId, int userId, Double balance) {
	      this.accountId = accountId;
	      this.userId = userId;
	      this.balance = balance;
	   }
	
	
	
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", userId=" + userId + ", balance=" + balance + "]";
	}

	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
