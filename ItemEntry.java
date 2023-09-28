package testWindowBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ItemEntry {
	
	private Item item;
	
	private int count;
	
	public ItemEntry(Item userItem) {
		
		this.item = userItem;
		
		this.count = 1;
		
	}
	
	public void increment() {
		
		this.count++;
		
	}
	
	public void decrement() {
		
		this.count--;
		
	}
	
	public Item getItem() {
		
		return this.item;
		
	}
	
	public int getCount() {
		
		return this.count;
		
	}
	
	public String getItemDescription() {
		
		String returnText = "";
		
		returnText += getCount()+ "     ";
		returnText += getItem().getName() + "            ";
		returnText += getItem().getPrice();
		
		return returnText;
				
	}
	
}
