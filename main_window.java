package testWindowBuilder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.geom.Line2D;
import java.io.File;
import java.text.AttributedString;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.List;
import javax.swing.JList;

public class main_window extends window{

	public POS posSystem;
	private JTextField codeField;
	private JLabel errorLabel;
	private JList itemList;
	private double totalPrice;
	private JScrollPane scrollPane = new JScrollPane(itemList);
	private JLabel directions;
	/**
	 * Create the application.
	 */
	
	public main_window(POS userPos) {
		
		super();
		
		posSystem = userPos;
		
		initialize();
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		setBackground(new Color(223,249,255)); 

		createList();
		
		createTitles();
				
		createEnterButton();
		
		TotalPriceLabel();
		
		createRemoveButton();
		
		createPayButton();
		
	}
	
	private void createList() {
		
		itemList = new JList();
		
		itemList.setLayoutOrientation(JList.VERTICAL);
		
		itemList.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane(itemList);
		layout.putConstraint(SpringLayout.WEST, listScroller,
	              50,
	              SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, listScroller,
	              125,
	              SpringLayout.NORTH, panel);
		listScroller.setPreferredSize(new Dimension(800, 700));
		itemList.setBackground(new Color(255, 236, 158));
		itemList.setOpaque(true);
		listScroller.setBorder(BorderFactory.createLineBorder(Color.black, 4));
		
		panel.add(listScroller);
		
	}
	
	private void createTitles() { // Create titles for the frame
		
		Font bold_italic = new Font("Lucida Grande", Font.BOLD | Font.ITALIC , 72);
		
		Font bold = new Font("Malayalam MN", Font.BOLD, 64);
		
		JLabel itemsTitle = createLabel(350, 20, 221, 86, "Items", bold_italic);
		
		JLabel itemsTitle_Underline = createLabel(350, 20, 221, 86, "______", bold_italic);
		
		codeField = createField(900, 125, 50, 100, bold, "Item Code", 6);
		
		codeField.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		codeField.setForeground(Color.LIGHT_GRAY);
		
		codeField.addMouseListener(new MouseAdapter() { 
	          
			public void mousePressed(MouseEvent me) { 

				codeField.setColumns(3);
				codeField.setText("");
				codeField.setForeground(Color.BLACK);
				codeField.setFont(bold);
				
				frame.invalidate();
				frame.validate();
				frame.repaint();
				
	        }
			
	          });
		
		//SwingUtilities.updateComponentTreeUI(frame);	
    	
		codeField.addKeyListener(new KeyAdapter() {
		    
			public void keyTyped(KeyEvent e) { 
		   
		        if (codeField.getText().length() >= 4 ) // limit textfield to 3 characters
		            e.consume(); 
		    }  
		});
		
		directions = createLabel(850, 10, 600, 400, "Please enter Item code below", (new Font("Malayalam MN", Font.BOLD, 50)));
		
		errorLabel = createLabel(900, 5, 600, 600, "", (new Font("Malayalam MN", Font.BOLD, 40)));
		
		errorLabel.setVisible(false);
		
	}
	
	private JLabel createItemLabel(ItemEntry userItem, int x, int y) {  // Create the item labels for the items
		
		String text = userItem.getItemDescription();
	
		Font f = new Font("Lucida Grande", Font.BOLD, 40);
		
		JLabel itemLabel = createLabelForList(x, y, 820, 50, text, f);
		
		itemLabel.setName(userItem.getItem().getName() + "ItemLabel");
			
		return itemLabel;
		
	}
	
	private void createEnterButton() {
		
		Font f = new Font("Apple Casual", Font.PLAIN, 50);
		
		JLabel priceLabel = new JLabel();
		
		priceLabel.setText(("$" + totalPrice));
		
		priceLabel.setFont(new Font(f.getFontName(), Font.BOLD, f.getSize()));
		
		priceLabel.setSize(new Dimension(555, 1030));
		
		layout.putConstraint(SpringLayout.WEST, priceLabel, 880,SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, priceLabel, 725, SpringLayout.NORTH, panel);
		
		
		priceLabel.setForeground(new Color(255, 204, 0));
		
		ActionListener al = (new ActionListener(){
			
			public void actionPerformed(ActionEvent ae) {
				
				onButtonClick();
				
				priceLabel.setText(("$" + totalPrice));
				
				frame.invalidate();
				frame.validate();
				frame.repaint();
				
				codeField.setText("Item Code");
				codeField.setColumns(6);
				codeField.setForeground(Color.LIGHT_GRAY);
				codeField.setFont(new Font("Malayalam MN", Font.BOLD, 64));
				
			}
			
		});
		
		JButton entrButton = createButton(900, 250, 400, 60, al, "Enter", f);
		
		entrButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		entrButton.setOpaque(true);
		entrButton.setBackground(Color.green);
		panel.add(priceLabel);
		
		
	}

	private void createSearchButton() {
	
		Font f = new Font("Apple Casual", Font.PLAIN, 50);
		
		ActionListener al = (new ActionListener(){
			
			public void actionPerformed(ActionEvent ae) {
				
				onButtonClick();
			
			}
			
			});
		
		JButton searchButton = createButton(900, 510, 400, 60, al, "Search", f);
		
		searchButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		searchButton.setOpaque(true);
		searchButton.setBackground(Color.yellow);
		
		
}
	
		private JButton createPayButton() {
		
		Font f = new Font("Apple Casual", Font.PLAIN, 50);
		
		ActionListener al = (new ActionListener(){
			
			public void actionPerformed(ActionEvent ae) {
				
				pay_Window pw = new pay_Window(posSystem);
			
				}
			
			});
		
		JButton payButton = createButton(900, 325, 400, 60, al, "Pay", f);
		
		payButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		payButton.setOpaque(true);
		payButton.setBackground(new Color(70, 188, 255));
		
		return payButton;
		
	}

	private JButton createRemoveButton() {
	
		Font f = new Font("Apple Casual", Font.PLAIN, 50);
		
		ActionListener al = (new ActionListener(){
			
			public void actionPerformed(ActionEvent ae) {
				
				if(posSystem.getShoppingList().size() != 0) {
					
					try {
					
						posSystem.removeLastItem();
						
						addToList();
					
					} catch (IndexOutOfBoundsException e) {
						
						posSystem.setError(("Error removing Item"));
						
					}
					
				} else {
					
					posSystem.setError(("No Items to remove"));
					
				}
					SwingUtilities.updateComponentTreeUI(frame); // Refresh the frame to display the label
					
				}
			
			});
		
		JButton removeButton = createButton(900, 400, 400, 60, al, "Remove", f);
		
		removeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		removeButton.setOpaque(true);
		removeButton.setBackground(Color.red);
		
		return removeButton;
	
}
	
	private ArrayList<JLabel> ListLabels() {
	
		int x = 10;
		
		int y = 0;
		
		Component[] compList = panel.getComponents();
		
		for (int i = 0; i < compList.length; i++)
		
		{
			String compName = compList[i].getName();
	       
			if (compName != null && compName.contains("ItemLabel"))
			
			{
			
				panel.remove(compList[i]);
			
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
	
	
	
	private void TotalPriceLabel() { // Create the total price label initially
		
		totalPrice = Double.parseDouble(posSystem.getTotalCost());
		
		Font f = new Font("Lucida Grande", Font.BOLD, 60);
		
		JLabel totalLabel = createLabel(880, 650 , 555, 1030, "Total Cost", f);
	
	}
	
	
	private void onButtonClick() { 
		
			int code = 0;
			
			try {
			
				code = Integer.parseInt(codeField.getText());
	
			} catch (NumberFormatException e) {
					
				posSystem.setError("Invalid item code, Please try again");
				
				codeField.setText("");
					
			}
			
			SwingUtilities.updateComponentTreeUI(frame); // Refresh the frame to display the label
			
			posSystem.addToShoppingList(code);
			
			createError(posSystem.getError());
		
			addToList();
			
			TotalPriceLabel();
		
			//System.out.println();
	
			codeField.setText("");
			
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
		
		for(int i = 0; i < ListLabels().size(); i++) {
			
			itemList.add(ListLabels().get(i));
			
		}
		
		SwingUtilities.updateComponentTreeUI(frame); // Refresh the frame to display the label
		
	}
	
	private void createError(String errorText) {
		
		if(errorText.isBlank() || posSystem.getError().isBlank()) {
			
			directions.setVisible(true);
			
			errorLabel.setVisible(false);
			
			return;	
			
		} else {
		
			directions.setVisible(false);
			
			errorLabel.setText(errorText);
			
			errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
			
			errorLabel.setVerticalAlignment(SwingConstants.CENTER);
		
			errorLabel.setVisible(true);
		
		}
	}
	
}


