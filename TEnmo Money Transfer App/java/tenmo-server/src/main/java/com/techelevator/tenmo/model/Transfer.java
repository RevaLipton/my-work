package com.techelevator.tenmo.model;

public class Transfer {

	
	/**pk   transfer_id serial 
			Transfer_type_id int
			Transfer_status_id int
			Account_from int
			Account_to int 
			Amount numeric
*/
	
		private Long transferId;
		private int transferType;
		private int statusId;
		private int accountFrom;
		private int accountTo;
		private Double amount; // switched to BigDecimal to Double 
		
		public Transfer() {}
		
		@Override
		public String toString() {
			return "transferId= " + transferId + " \n" + "transferType= " + transferType + "\n" + "statusId= " + statusId
					+ "\n" + "accountFrom= " + accountFrom + " accountTo= " + accountTo + " amount= " + amount;
		}

		public Transfer(Long transferId, int transferType, int statusId, int accountFrom, int accountTo, Double amount) {
		      this.transferId = transferId;
		      this.statusId = statusId;
		      this.accountFrom = accountFrom;
		      this.accountTo = accountTo;
		      this.amount = amount;
		   }

		public Long getTransferId() {
			return transferId;
		}

		public void setTransferId(Long transferId) {
			this.transferId = transferId;
		}

		public int getTransferType() {
			return transferType;
		}

		public void setTransferType(int transferType) {
			this.transferType = transferType;
		}

		public int getStatusId() {
			return statusId;
		}

		public void setStatusId(int statusId) {
			this.statusId = statusId;
		}

		public int getAccountFrom() {
			return accountFrom;
		}

		public void setAccountFrom(int accountFrom) {
			this.accountFrom = accountFrom;
		}

		public int getAccountTo() {
			return accountTo;
		}

		public void setAccountTo(int accountTo) {
			this.accountTo = accountTo;
		}

		public Double getAmount() {
			return amount;
		}

		public void setAmount(Double amount) {
			this.amount = amount;
		}
	
}
