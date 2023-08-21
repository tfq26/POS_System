package testWindowBuilder;

public class Item {

	private String itemName;
	
	private double itemPrice;
	
	private int itemCode;
	
	private double itemQuantity;
	
	public Item() {
		
		itemName = "";
		
		itemPrice = 0.0;
		
	}
	
	public Item(String userName, double userPrice) {
		
		itemName = userName;
		
		itemPrice = userPrice;
		
	}
	
	public Item(String userName, double userPrice, int userItemCode) {
		
		itemName = userName;
		
		itemPrice = userPrice;
		
		itemCode = userItemCode;
		
	}
	
	public String getName() {
		
		return this.itemName;
		
	}
	
	public int getItemCode() {
		
		int returnCode = itemCode;
		
		return returnCode;
		
	}
	
	public double getItemQuantity() {
		
		return this.itemQuantity;
		
	}
	
	public void setItemQuantity(double newQuantity) {
		
		this.itemQuantity = newQuantity;
		
	}
	
	public void increment() {
		
		this.itemQuantity++;
		
	}
	
	public double getPrice() {
		
		return this.itemPrice;
		
	}
	
	
	public double getItem() {
		
		return this.itemPrice;
		
	}

	
	
	
}
