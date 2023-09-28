package testWindowBuilder;

public class Item {

	private String itemName;
	
	private double itemPrice;
	
	private int itemCode;
	
	private double itemQuantity;
	
	public Item() {
		
		itemName = "";
		
		itemPrice = 0.0;
		
		itemQuantity = 0;
		
	}
	
	public Item(String userName, double userPrice) {
		
		itemName = userName;
		
		itemPrice = userPrice;
		
	}
	
	public Item(String userName, double userPrice, int userItemCode) {
		
		itemName = userName;
		
		itemPrice = userPrice;
		
		itemCode = userItemCode;
		
		itemQuantity = 1;
		
	}
	
	public Item(String userName, double userPrice, int userItemCode, double userQuantity) {
		
		itemName = userName;
		
		itemPrice = userPrice;
		
		itemCode = userItemCode;
		
		itemQuantity = userQuantity;
		
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
	
	public double getPrice() {
		
		return this.itemPrice;
		
	}
	
	public double getItem() {
		
		return this.itemPrice;
		
	}
	
}
