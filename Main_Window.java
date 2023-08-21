package testWindowBuilder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.awt.geom.Line2D;
import java.io.File;
import java.text.AttributedString;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.List;
import javax.swing.JList;

public class Main_Window {

	private JFrame frame;
	public POS posSystem;
	private JTextField codeField;
	private JTextField cardField;
	private JButton entrButton;
	private JLabel totalPriceLabel;
	private JButton endButton;
	private JButton payButton;
	private JLabel errorLabel;
	private JButton backButton;
	private JList itemList;
	private boolean end = false;
	private JScrollPane scrollPane = new JScrollPane(itemList);
	
	public int numOfItems = 0;
	
/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Window window = new Main_Window();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
					window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main_Window() {
		
		posSystem = new POS();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		frame = new JFrame();
		frame.setBounds(200, 200, 1100, 900); // (int x, int y, int width, int height)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		createTitles();
		
		errorLabel = new JLabel();
		errorLabel.setVerticalAlignment(SwingConstants.TOP);
		errorLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setBounds(470, 200 , 855, 930); //(int x, int y, width, height)
		frame.getContentPane().add(errorLabel);
		errorLabel.setVisible(false);
		
		posSystem.addToDirectory(new Item("Apple", 0.69, 4879));
		
		posSystem.addToDirectory(new Item("Water", 0.99, 1234));
		
		posSystem.addToDirectory(new Item("Ice Cream", 3.49, 0003));
		
		posSystem.addToDirectory(new Item("Bag", 0.80, 0002));
		
		posSystem.addToDirectory(new Item("Ice", 0.89, 3876));
		
		posSystem.addToDirectory(new Item("Banana", 1.29, 8723));
		
		posSystem.addToDirectory(new Item("Snickers", 2.49, 3345));
		
		posSystem.addToDirectory(new Item("Twix", 2.49, 3346));
		
		posSystem.addToDirectory(new Item("Sandwich", 3.99, 9834));
		
		posSystem.addToDirectory(new Item("Sushi", 3.49, 9844));
		
		posSystem.addToDirectory(new Item("Gatorade", 1.99, 8822));
		
		posSystem.addToDirectory(new Item("Tea", 1.99, 5678));
		
		posSystem.addToDirectory(new Item("Coffee", 1.99, 9012));
		
		posSystem.addToDirectory(new Item("Bisweet", 3.99, 3456));
		
		entrButton = new JButton("Enter");
	
		entrButton.setBounds(650, 500, 150, 50); //(int x, int y, width, height)
		
		entrButton.setFont(new Font("Malayalam MN", Font.PLAIN, 30));
	
		entrButton.addActionListener(new ActionListener(){
	
			public void actionPerformed(ActionEvent ae) {
				
				onButtonClick();
			
			}
			
			});
		
		frame.getContentPane().add(entrButton);

		
		payButton = new JButton("Pay");
		
		payButton.setBounds(810, 500, 150, 50); //(int x, int y, width, height)
	
		payButton.setFont(new Font("Malayalam MN", Font.PLAIN, 30));
		
		payButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent ae) {
				
				showPaymentScreen();
				
			}
			
		});
		
		frame.getContentPane().add(payButton);
		
		}
	
	private JLabel createItemLabel(Item userItem, int x, int y) {  // Create the item labels for the items
		
		String text = userItem.getName() + " " + userItem.getItemQuantity() + " " + userItem.getPrice();
		
		JLabel itemLabel = new JLabel(text);
		
		itemLabel.setVerticalAlignment(SwingConstants.TOP);
		itemLabel.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		itemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		itemLabel.setName(userItem.getName() + "ItemLabel");
		itemLabel.setBounds(x, y , 455, 930); //(int x, int y, width, height)
	
		return itemLabel;
		
	}
	
	private ArrayList<JLabel> createListLabels() {
	
		int x = 20;
		
		int y = 20;
		
		Component[] compList = frame.getContentPane().getComponents();
		
		for (int i = 0; i < compList.length; i++)
		
		{
			String compName = compList[i].getName();
	       
			if (compName != null && compName.contains("ItemLabel"))
			
			{
			
				frame.getContentPane().remove(compList[i]);
			
			}
		
		}
		
		SwingUtilities.updateComponentTreeUI(frame); // Refresh the frame to display the label
		
		ArrayList<JLabel> labelsList = new ArrayList<>();
		
		int yoffset = 0;
		
		for(int i = 0; i < posSystem.getShoppingList().size(); i++) {
			
			labelsList.add(createItemLabel(posSystem.getShoppingList().get(i), x, y + yoffset));
			
			yoffset += 50;
			
			SwingUtilities.updateComponentTreeUI(frame); // Refresh the frame to display the label
			
		}
		
		return labelsList;
		
	}
	
	private void createTitles() { // Create titles for the frame
		
		Font plain = new Font("Lucida Grande", Font.BOLD | Font.ITALIC , 36);
		
		JLabel itemsTitle = new JLabel("Items");
		
		totalPriceLabel = new JLabel();
		
		itemList = new JList<JLabel>();
		
		itemList.setBounds(65, 144, 495, 650);
		
//		scrollPane.setViewportView(itemList);
//		
//		itemList.setLayoutOrientation(JList.VERTICAL);
//		
//		frame.getContentPane().add(scrollPane);
//		
		frame.getContentPane().add(itemList);
		
		itemsTitle.setFont(plain);
		
		itemsTitle.setHorizontalAlignment(SwingConstants.CENTER);
		itemsTitle.setBounds(20, 48, 221, 86);
		frame.getContentPane().add(itemsTitle);
		
		JLabel lblNewLabel_1_underline = new JLabel("______");
		
		lblNewLabel_1_underline.setFont(plain);
		
		lblNewLabel_1_underline.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_underline.setBounds(25, 48, 221, 86);
		frame.getContentPane().add(lblNewLabel_1_underline);
		
		codeField = new JTextField();
		codeField.setBounds(750, 400, 110, 90);
		codeField.setFont(new Font("Malayalam MN", Font.BOLD, 42));
		frame.getContentPane().add(codeField);
		
		codeField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Please enter the Code for the item");
		lblNewLabel_2.setFont(new Font("Malayalam MN", Font.PLAIN, 30)); 
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(500, -150, 600, 400); //(int x, int y, width, height)
		frame.getContentPane().add(lblNewLabel_2);
		
		
	}
	
	private void createTotalPriceLabel() { // Create the total price label initially
		
		totalPriceLabel.setText("Total Cost: $" + posSystem.getTotalCost());
		
		totalPriceLabel.setVerticalAlignment(SwingConstants.TOP);
		totalPriceLabel.setFont(new Font("Lucida Grande", Font.BOLD, 56));
		totalPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalPriceLabel.setBounds(550, 700 , 555, 1030); //(int x, int y, width, height)
		frame.getContentPane().add(totalPriceLabel);
		
	}
	
	
	private void onButtonClick() {
		
			int code = 0;
			
				try {
			
					code = Integer.parseInt(codeField.getText());
	
				} catch (NumberFormatException e) {
					
					posSystem.setError("Invalid item code, Please try again");
					
					errorLabel.setVisible(true);
					
					codeField.setText("");
					
				}
			
			posSystem.importIntoDirectory(new File("Database.csv"));
			
			posSystem.addToShoppingList(code);
			
			numOfItems++;
			
			addToList();
			
			errorLabel.setText(posSystem.getError());
			
			errorLabel.setVisible(true);
		
		//	posSystem.addItemNums(code);
		
			//System.out.println(code + " " + posSystem.getItem(code).getName() + " " + numOfItems);
			
			createTotalPriceLabel();

				//	boolean isNew = posSystem.checkIfAdded(code);
				
			//createListLabels();
				
				 // incrementing the number of items for padding
					
				codeField.setText("");
			
		//	updateTotalPrice(totalPrice, totalPriceLabel);
				
			SwingUtilities.updateComponentTreeUI(frame); // Refresh the frame to display the label
			
			
			
		}
	
	private void addToList() {
		
		Component[] compList = itemList.getComponents();
		
		for (int i = 0; i < compList.length; i++)
		
		{
			String compName = compList[i].getName();
	       
			if (compName != null && compName.contains("ItemLabel"))
			
			{
				itemList.remove(compList[i]);
				
				SwingUtilities.updateComponentTreeUI(frame); // Refresh the frame to display the label
			}
		
		}
		
		for(int i = 0; i < createListLabels().size(); i++) {
			
			itemList.add(createListLabels().get(i));
			
		}
		
		SwingUtilities.updateComponentTreeUI(frame); // Refresh the frame to display the label
		
	}
	
	private void showPaymentScreen() {
		
		frame.getContentPane().removeAll();
		frame.revalidate();
		frame.repaint();
		
//		JLabel itWorked = new JLabel("It worked, the board is clear!");
//		
//		itWorked.setVerticalAlignment(SwingConstants.TOP);
//		itWorked.setFont(new Font("Lucida Grande", Font.BOLD, 50));
//		itWorked.setHorizontalAlignment(SwingConstants.CENTER);
//		itWorked.setBounds(150, 400 , 855, 930); //(int x, int y, width, height)
//	
		
//		frame.getContentPane().add(itWorked);
		
		goBack();
		
		paymentProcess();
		
		
	}
	
	private void paymentProcess() {
	
		addEndButton();
		JLabel displayPrice = new JLabel("Total: $" + posSystem.getTotalCost());
		displayPrice.setVerticalAlignment(SwingConstants.TOP);
		displayPrice.setFont(new Font("Lucida Grande", Font.BOLD, 50));
		displayPrice.setHorizontalAlignment(SwingConstants.CENTER);
		displayPrice.setBounds(130, 100 , 855, 800); //(int x, int y, width, height)
		frame.getContentPane().add(displayPrice);
		
		JLabel cardProcessed = new JLabel();
		cardProcessed.setVerticalAlignment(SwingConstants.TOP);
		cardProcessed.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		cardProcessed.setHorizontalAlignment(SwingConstants.CENTER);
		cardProcessed.setBounds(130, 100 , 855, 800); //(int x, int y, width, height)
		frame.getContentPane().add(cardProcessed);
		cardProcessed.setVisible(false);
		
		JLabel cardInstructions = new JLabel("Please enter your Debit or Credit card number");
		cardInstructions.setVerticalAlignment(SwingConstants.TOP);
		cardInstructions.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		cardInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		cardInstructions.setBounds(130, 180 , 855, 930); //(int x, int y, width, height)
		frame.getContentPane().add(cardInstructions);
		
		cardField = new JTextField();
		cardField.setBounds(400, 270, 380, 110); //int x, int y, width, height
		cardField.setFont(new Font("Malayalam MN", Font.BOLD, 42));
		frame.getContentPane().add(cardField);
	
		JButton payAmountButton = new JButton("Pay");
		
		payAmountButton.setBounds(810, 500, 150, 50); //(int x, int y, width, height)
	
		payAmountButton.setFont(new Font("Malayalam MN", Font.PLAIN, 30));
		
		frame.getContentPane().add(payAmountButton);
		
		payAmountButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent ae) {
				
				if(posSystem.checkCard(cardField.getText())) {
					
					end = true;
					
					errorLabel.setVisible(false);

					SwingUtilities.updateComponentTreeUI(frame);
					frame.getContentPane().remove(endButton);

					SwingUtilities.updateComponentTreeUI(frame);
					frame.getContentPane().remove(backButton);

					SwingUtilities.updateComponentTreeUI(frame);
					cardInstructions.setVisible(false);

					SwingUtilities.updateComponentTreeUI(frame);
					displayPrice.setVisible(false);

					SwingUtilities.updateComponentTreeUI(frame);
					cardField.setVisible(false);

					cardProcessed.setText("<html>Payment for $" + posSystem.getTotalCost() + " Successful<br/> Have a Nice Day!</html>");
					
					SwingUtilities.updateComponentTreeUI(frame);	
					cardProcessed.setVisible(true);
				
					SwingUtilities.updateComponentTreeUI(frame);
					
					frame.getContentPane().remove(payAmountButton);
					
					SwingUtilities.updateComponentTreeUI(frame); // Refresh the frame to display the label
					
				//	endProgram();
			
					} else {
					
					SwingUtilities.updateComponentTreeUI(frame);
					cardInstructions.setVisible(false);

					SwingUtilities.updateComponentTreeUI(frame);
					displayPrice.setVisible(false);

					cardProcessed.setText("<html>Payment for $" + posSystem.getTotalCost() + " Declined<br/> Please Try again</html>");
					
					cardProcessed.setVisible(true);
										
					SwingUtilities.updateComponentTreeUI(frame);
					
					
				}
				
			}
			
			
		});
		
		
					
	}
	
	private void goBack() {
		
		backButton = new JButton("Back");
		
		backButton.setBounds(600, 400, 150, 50); //(int x, int y, width, height)
	
		backButton.setFont(new Font("Malayalam MN", Font.PLAIN, 30));
		
		backButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent ae) {
				
				frame.getContentPane().removeAll();
				frame.revalidate();
				frame.repaint();
				
				createTitles();

				addToList();
				
				frame.getContentPane().add(codeField);
				frame.getContentPane().add(entrButton);
				frame.getContentPane().add(payButton);
				frame.getContentPane().add(totalPriceLabel);
				createTotalPriceLabel();
				
			}
			
		});
		
		frame.getContentPane().add(backButton);
		
		SwingUtilities.updateComponentTreeUI(frame); // Refresh the frame to display the label;
		
	}
	
	private void addEndButton() {
		
		endButton = new JButton("End");
		
		endButton.setBounds(400, 400, 150, 50); //(int x, int y, width, height)
	
		endButton.setFont(new Font("Malayalam MN", Font.PLAIN, 30));
		
		endButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent ae) {
				
				endProgram();
				
			}
			
		});
		
		frame.getContentPane().add(endButton);
		
		SwingUtilities.updateComponentTreeUI(frame); // Refresh the frame to display the label;
		
	}
	
	private void endProgram() {
		
		if(end == true) {
			
			System.out.println("Endprogram complete");
			
			posSystem.exportToFile();
			
		}
		
		waitForTime(2);
				
	}
	
	private void waitForTime(int n) {
		
		try {

			TimeUnit.SECONDS.sleep(n);
				
		} catch (InterruptedException e) {
				
			posSystem.setError("Error with delay");
			
			errorLabel.setVisible(true);
			
		}
	}
	
}


