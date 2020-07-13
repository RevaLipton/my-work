package com.techelevator;

public class Slot {
	
	private VendingMachineItem slotItem;
	private double price;
	private int quantity;
	
	public Slot(VendingMachineItem slotItem, double price, int quantity) {
		this.slotItem = slotItem;
		this.price = price;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return slotItem + " | Price $" + price + " | Quantity available: " + quantity;
	}

	/**
	 * @return the slotItem
	 */
	public VendingMachineItem getSlotItem() {
		return slotItem;
	}

	/**
	 * @param slotItem the slotItem to set
	 */
	public void setSlotItem(VendingMachineItem slotItem) {
		this.slotItem = slotItem;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
