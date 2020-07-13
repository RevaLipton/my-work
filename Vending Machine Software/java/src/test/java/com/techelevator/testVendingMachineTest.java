package com.techelevator;
//
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class testVendingMachineTest { 

	@Test
	public void get_inventory_test() throws FileNotFoundException {
		VendingMachine tester = new VendingMachine();
		Map<String, Slot> testInventory = tester.getInventory();
		Slot testSlot = testInventory.get("A1");
		//created a new Slot object to hold the value held by the key A1 in the map
		testInventory.put("B1", testSlot);
		//moved the vale held by testSlot to the key B1 
		assertEquals(testSlot, testInventory.get("B1"));
		//key B1 now retrieves the value in testSlot
	}

	@SuppressWarnings("deprecation")
	@Test
	public void get_current_balance_0_test() throws FileNotFoundException {
		VendingMachine tester = new VendingMachine();
		double currentBalance = 0.0;
		double testInventory = tester.getCurrentBalance();
		assertEquals("currentBalance should be: ", 0.0, testInventory, 0.0001);
	}

	@SuppressWarnings("deprecation")
	@Test 
	public void update_balace_with_setter_test() throws IOException {
		VendingMachine tester = new VendingMachine();
		double currentBalance = 0.0;
		double moneyDeposited = 5.0;
		tester.setCurrentBalance(currentBalance + moneyDeposited);
		//currentBalance should reflect 5
		assertEquals(5.0, tester.getCurrentBalance(), 0.0001);
	}

	@Test
	public void return_change_test() throws IOException {
		VendingMachine tester = new VendingMachine();
		double currentBalance = 3.35;
		tester.setCurrentBalance(currentBalance);
		tester.finishTransaction(); //should dispense change and set balance to 0
		assertEquals(0, tester.getCurrentBalance(), 0.0001);
	}

}
