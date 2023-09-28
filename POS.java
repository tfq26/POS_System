package testWindowBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class POS {

	private HashMap<Integer, Item> directory = new HashMap<>(); // Hashmap containing the Key with the associated item
	
	private ArrayList<ItemEntry> shoppingList = new ArrayList<>(); // ArrayList containing the item and the quantity of the item
	
	private String errorMsg = "";
	
	private double totalCost = 0.0;

	public POS() {
		
	}
	
	public ArrayList<ItemEntry> getShoppingList(){
		
		return shoppingList;
		
	}
	
	private ArrayList<Item> getShoppingListEntries(){
		
		ArrayList<Item> returnList = new ArrayList<>();
		
			for(int i = 0; i < shoppingList.size(); i++) {
				
				returnList.add(shoppingList.get(i).getItem());
				
			}
			
			return returnList;
		
	}
	
//	private void printShoppingList(){
//	
//		for(int i = 0; i < shoppingList.size(); i++) {
//			
//			Item temp = shoppingList.get(i).getItem();
//			
//			System.out.println(temp.getName() + " " + temp.getItemCount());
//			
//		}
//		
//		System.out.println();
//		
//	}
	
	public void addToDirectory(Item userItem) { // Using the code as the key for the item value
		
		this.directory.put(userItem.getItemCode(), userItem);
		
	}

	
	public void addToShoppingList(int itemCode) {
		
		System.out.println("Running through this method");
		
		if(containsKey(itemCode)) {
		
		Item tempItem = directory.get(itemCode);
		
		ItemEntry tempEntry = new ItemEntry(tempItem);;
		
		if(getShoppingListEntries().contains(tempItem)) {
			
			System.out.println("Already in list");
			
			int index = getShoppingListEntries().indexOf(tempItem);
			
			tempEntry = shoppingList.get(index);
			
			tempEntry.increment();
			
			System.out.println("Finished Increment, New Count: " + tempEntry.getCount()
			
					+ " ShoppingList Size: " + shoppingList.size());
			
		} else {
			
			shoppingList.add(tempEntry);
			
			System.out.println("Added to list."+ " ShoppingList Size: " + shoppingList.size());
			
		}
		
		totalCost += tempEntry.getItem().getPrice();
		
		}
	}
	
	private int generateCode() { // Generates a code for each item because each item is kept with the price in the map
		
		String returnCode = "";
		
		Random r = new Random();
		
		for(int i = 0; i < 7; i++) {
		
		int c = r.nextInt(26);
		
		returnCode += c;
		
		}
		
		return Integer.parseInt(returnCode);
		
	}
	
	public String getItemDescription(int userCode) {
		
		if(containsKey(userCode)) {
		
		for(int i = 0; i < shoppingList.size(); i++) {
			
			return shoppingList.get(i).getItem().getName() + "  ";
			
		}
		
		} else {
			
			System.exit(0);
			
			return null;
			
		}
		
		return "";
	}
	
	public Item getItem(int userCode) throws NullPointerException{
		
		if(containsKey(userCode)) {
			
			Item temp = directory.get(userCode);
			
			for(int i = 0; i < shoppingList.size(); i++) {
				
				if(shoppingList.get(i).getItem().equals(temp)) {
					
					return shoppingList.get(i).getItem();
					
				}
				
			}
			
		} else {
				
			System.exit(0);
				
			return null;
				
		}
		
		return null;
		
	}
	
	
	public String getTotalCost() {
		
		DecimalFormat df = new DecimalFormat("##.##");
		
		return df.format(totalCost);
		
	}
	
	private boolean containsKey(int userCode) {
		
		if(directory.containsKey(userCode)) {
			
			this.setError("");
			
			return true;
			
		} else {
			
			this.setError("<html>Item does not exist<br/>Please try again</html>");
			
			return false;
			
		}
		
	}
	
	public void importIntoDirectory(File importFile) {
		
		Scanner fileReader = null;
				
				try {
					
					fileReader = new Scanner(new FileInputStream(importFile));
					
				} catch(IOException e) {
					
					System.out.println("Error Reading file");
					
					e.printStackTrace();
					
					System.exit(0);
					
				}
				
				String cursor = fileReader.next();
			
				String[] itemsFromFile = null;
				
				while(fileReader.hasNext()) {
					
					cursor = fileReader.next();
					
					itemsFromFile = cursor.split(",");
					
					for(int i = 0; i < itemsFromFile.length; i += 4) {
						
						String tempName = itemsFromFile[0];
						
						double tempPrice = Double.parseDouble(itemsFromFile[1]);
						
						int tempCode = Integer.parseInt(itemsFromFile[2]);
						
						double tempQuantity = Double.parseDouble(itemsFromFile[3]);
						
						Item temp = new Item(tempName, tempPrice, tempCode, tempQuantity); // Name, Price, Code, Quantity
						
						this.directory.put(tempCode, temp);
						
					}
										
			}
				
				for(Integer i : this.directory.keySet()) {
					
					System.out.println("Code: " + i + ", Item: " + this.directory.get(i).getName() + " Quantity: " + this.directory.get(i).getItemQuantity());
					
				}
			
	}
	
	public boolean checkCard(String cardNumString) { // Luhns Algorithm with test value: 5113615648623237 
		
		boolean cardIsValid;
		
		cardNumString = cardNumString.replaceAll("\\s","");
		
		Long cardNum = 0L;
		
		try {
		
			cardNum = Long.parseLong(cardNumString);
		
		} catch (NumberFormatException e) {
			
			this.setError("<html>Card Number Invalid<br/>  Please try again</html>");
			
		}
		
		if(cardNumString.length() != 16) {
			
			cardIsValid = false;
			
			return cardIsValid;
			
		}
		
		long[] digits = new long[16]; // 5 1 1 3 6 1 5 6 4 8 6 2 3 2 3 7
		
		int count = 0;
		
			while(cardNum > 0) { // add all the digits of the input into an array
				
				long remainder = cardNum % 10;
				
				digits[count] = remainder;
						
				cardNum /= 10;
				
				count++;
				
			}
			
//			for(int j = 0; j < digits.length; j++) {
//				System.out.print(digits[j] + " ");	
//			}
//			
//			System.out.println();
			
			
		int[] everyOtherDigits = new int[digits.length / 2]; // 3 3 6 4 5 6 1 5
		
		count = 0;
		
			for(int i = 1; i < digits.length; i += 2) { // add every other digit into another array
				
				everyOtherDigits[count] = (int) digits[i];
				
				count++;
				
			}
			
			for(int j = 0; j < everyOtherDigits.length; j++) { // multiply every other digit by two
				
		//		System.out.print(everyOtherDigits[j] + " ");
				
				everyOtherDigits[j] *= 2; //6 6 12 8 10 12 2 10
				
				if(everyOtherDigits[j] > 9) { // if the value times 2 exceeds 10, add the two digits together
					
					int temp = (everyOtherDigits[j] % 10) + 1; // 6 6 3 8 1 3 2 1
					
					everyOtherDigits[j] = temp;
					
				}
				
			}
			
//			System.out.println();
//			
//			for(int j = 0; j < everyOtherDigits.length; j++) {
//				System.out.print("  " + everyOtherDigits[j] + " ");	
//			}
//			
			count = 0;
			
			for(int i = 1; i < digits.length; i += 2) { // put the new every other digits into the original array
				
				digits[i] = everyOtherDigits[count];
				
				count++;
				
			}
			
//			System.out.println();
		
			int digitsSum = 0;
			
			for(int j = 0; j < digits.length; j++) { // add up all the new final digits
				
//				System.out.print(digits[j] + " ");	
				
				digitsSum += digits[j]; 
				
			}

			if(digitsSum % 10 == 0) { // if the sum % 10 is 0, card is valid
				
				cardIsValid = true;
				
			} else {
				
				this.setError("<html>Card Number Invalid<br/>  Please try again</html>");
				cardIsValid = false;
				
			}
	
		return cardIsValid;
	
	}
	
	public Item getItemFromSearch(String userSearch) {
		
		for(Integer temp : directory.keySet()) {
			
			if(directory.get(temp).getName().equals(userSearch)) {
				
				return directory.get(temp);
				
			}
			
		}
		
		return null;
		
	}
	
	public void setError(String posError) {
		
		errorMsg = posError;
		
	}
	
	public String getError() {
		
		return this.errorMsg;
		
	}
	
	public void removeLastItem() {
		
		ItemEntry lastItem = shoppingList.get(shoppingList.size() - 1);
		
			if(lastItem.getCount() > 1) {
	
			lastItem.decrement();
		
			} else {
			
			shoppingList.remove(lastItem);
			
			}
			
		totalCost -= lastItem.getItem().getPrice();
	}
	
	public void exportToFile(File receiptFile) {
		
		DecimalFormat df = new DecimalFormat("##.##");
		
		double receiptNumber = 0.0;
		
		df.setRoundingMode(java.math.RoundingMode.CEILING);
		
		PrintWriter output = null;
		
		try {
			
			output = new PrintWriter(new FileWriter(receiptFile));
			
		} catch (IOException e) {
			
			System.out.println("Error writing to file");
			
		}
		
		receiptNumber = trackReceiptNumber(receiptFile) + 1;
		
		output.println(String.format("Receipt #: %04.0f", receiptNumber));
		
		for(int i = 0; i < getShoppingList().size(); i++) {
			
		//	String itemTotalPrice = df.format();
			
			ItemEntry printItemEntry = shoppingList.get(i);
			
			Item printItem = shoppingList.get(i).getItem();
			
			double itemTotalPrice = printItem.getPrice() * shoppingList.get(i).getCount();
			
			output.println("Item: " + printItem.getName() + " Quantity: " + printItemEntry.getCount()
			
			+ "\nPrice per item: $" + printItem.getPrice() + "\n");
			
		}
		
		output.println("Total: $" + this.getTotalCost());
		
		output.close();
		
	}

	public int trackReceiptNumber(File receiptFile) {
		
		Scanner fileInput = null;
		
		try {
			
			fileInput = new Scanner(new FileInputStream(receiptFile)).useDelimiter("[^0-9]+");
			
		} catch (IOException e) {
			
			System.out.println("Error reading file");
			
		}
		
		int receiptNum = 0;
		
		while(fileInput.hasNext()) {
		
			receiptNum = fileInput.nextInt();
		
		}
		
		if(!fileInput.hasNext()) {
			
			return 1;
			
		}
			fileInput.close();
			
		return receiptNum;
		
	}
	
	
	

}
