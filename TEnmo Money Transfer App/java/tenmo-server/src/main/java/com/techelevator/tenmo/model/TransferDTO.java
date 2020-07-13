package com.techelevator.tenmo.model;

public class TransferDTO {
	
	int transferToId;
	Double amount;
	
	public int getTransferToId() {
		return transferToId;
	}
	public void setTransferToId(int transferToId) {
		this.transferToId = transferToId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
