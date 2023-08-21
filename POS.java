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
	
	private ArrayList<Item> shoppingList = new ArrayList<>(); // ArrayList containing the item and the quantity of the item
	
	private String errorMsg = "";

	public POS() {
		
	}
	
	public ArrayList<Item> getShoppingList(){
		
		return shoppingList;
		
	}
	
	public void addToDirectory(Item userItem) { // Using the code as the key for the item value
		
		this.directory.put(userItem.getItemCode(), userItem);
		
	}

	
	public void addToShoppingList(int itemCode) {
		
		if(containsKey(itemCode)) {
			
			Item temp = directory.get(itemCode);
			
			if(shoppingList.contains(temp)) {
			
				temp.increment();
			
			} else {
			
			temp.setItemQuantity(1);
			
			this.shoppingList.add(temp);
			
			}
		
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
		
		return (directory.get(userCode).getName() + "   " + shoppingList.get(userCode).getItemQuantity())	;
		
		} else {
			
			System.exit(0);
			
			return null;
			
		}
	}
	
	public Item getItem(int userCode) throws NullPointerException{
		
		if(containsKey(userCode)) {
			
			Item temp = directory.get(userCode);
			
			for(int i = 0; i < shoppingList.size(); i++) {
				
				if(shoppingList.get(i).equals(temp)) {
					
					return shoppingList.get(i);
					
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
		
		df.setRoundingMode(java.math.RoundingMode.CEILING);
		
		double totalCost = 0.0;
		
		for (int i = 0; i < shoppingList.size(); i++) {
		   
			totalCost += shoppingList.get(i).getPrice() * shoppingList.get(i).getItemQuantity();
			
		//	System.out.println("Total: " + totalCost + " = item price (" + key.getPrice() + ") * item quantity(" + shoppingList.get(key) + ")");
			
		}
		
		return df.format(totalCost);
		
	}
	
	private boolean containsKey(int userCode) {
		
		if(directory.containsKey(userCode)) {
			
			return true;
			
		} else {
			
			this.setError("<html>Item does not exist<br/>  Please try again</html>");
			
			return false;
			
		}
		
	}
	

	public void exportToFile() {
		
		DecimalFormat df = new DecimalFormat("##.##");
		
		df.setRoundingMode(java.math.RoundingMode.CEILING);
		
		PrintWriter output = null;
		
		double receiptNumber = 0;
		
		File receiptFile = new File("Receipt" + java.time.LocalDate.now() + ".txt");
		
			try {
			
		output = new PrintWriter(new FileWriter(receiptFile));
			
		} catch (IOException e) {
			
			System.out.println("Error writing to file");
			
		}
		
		output.println(String.format("Receipt #: %04.0f", receiptNumber));
		
		for(int i = 0; i < shoppingList.size(); i++) {
			
			String itemTotalPrice = df.format(shoppingList.get(i).getPrice() * shoppingList.get(i).getItemQuantity());
			
			output.println("Item: " + shoppingList.get(i).getName() + " Quantity: " + this.shoppingList.get(i)
			
			+ "\nPrice per item: $" + shoppingList.get(i).getPrice() + ", total cost for item: $" + itemTotalPrice + "\n");
			
		}
		
		output.println("Total: $" + this.getTotalCost());
		
		trackReceiptNumber(receiptFile);
		
		output.close();
		
	}
	
	private void trackReceiptNumber(File inputFile) {
		
		Scanner fileInput = null;
		
		try {
			
			fileInput = new Scanner((inputFile));
			
		} catch (IOException e) {
			
			System.out.println("Error reading file");
			
		}
		
		String currentNum = "";
		
		while(fileInput.hasNextLine()) {
			
			System.out.println(fileInput.nextLine());
			
		}
		
		System.out.println(currentNum);
		
	}
	
	public ArrayList<Item> getFromShoppingList() {
		
		ArrayList<Item> returnList = new ArrayList <Item>();
		
		for(int i = 0; i < shoppingList.size(); i++) {
			
			returnList.add(shoppingList.get(i));
			
		}
		
		return returnList;
		
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
			
				while(fileReader.hasNext()) {
					
					System.out.println(cursor);
					
				}
		
	}
	
	public boolean checkCard(String cardNumString) { // Luhns Algorithm with test value: 5113615648623237 
		
		boolean cardIsValid;
		
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
	
	public void setError(String posError) {
		
		errorMsg = posError;
		
	}
	
	public String getError() {
		
		return this.errorMsg;
		
	}

}
