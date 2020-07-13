package com.techelevator;

public class VendingMachineItem {

	private String name;
	private String type;

	public VendingMachineItem(String name, String type) {
		this.name = name;
		this.type = type;
	}


	@Override
	public String toString() {
		return type + " | " + name;
		}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	

}
