	package testWindowBuilder;
	
	import java.awt.Color;
	import java.awt.Container;
	import java.awt.Dimension;
	import java.awt.Font;
	import java.awt.Frame;
	import java.awt.Toolkit;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
	import java.io.File;
	import java.util.Timer;
	import java.util.TimerTask;
	import javax.swing.BorderFactory;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JList;
	import javax.swing.JTextField;
	import javax.swing.SwingConstants;
	import javax.swing.SwingUtilities;
	
	public class pay_Window extends window{
		
		private POS posSystem;
		
		private Container panel;
		
		private String cardNum;
		
		public pay_Window(POS userPOS) {
		
			super();
				
			posSystem = userPOS;
			
			System.out.println("Entered Payment mode");
			
			cardNum = "";
			
			initialize();
			
		}	
			
		private void initialize() {
			
			super.getPanel().setBackground(new Color(255, 235, 122));		
			
			createPayButton();
			
			createPayButton();
			
			createExpDateField();
			
			createCVVField();
			
			createCardField();
		
			createBackButton();
			
		}
				
		private void paymentProcess() {
		
			
	
	}	
	
	
//	private JLabel createInstructionsLabel() {
//		
//		Font f = new Font("Lucida Grande", Font.BOLD, 30);
//		
//		JLabel cardProcessed = createLabel(275, 0 , 900, 900, returnText, f);
//		cardProcessed.setVerticalAlignment(SwingConstants.TOP);
//		
//		cardProcessed.setHorizontalAlignment(SwingConstants.CENTER);
//		cardInstructions.setVerticalAlignment(SwingConstants.TOP);
//		cardInstructions.setFont();
//		cardInstructions.setHorizontalAlignment(SwingConstants.CENTER);
//		cardInstructions.setBounds(350, 180 , 855, 930); //(int x, int y, width, height)
//		panel.add(cardInstructions);
//		return cardInstructions;
//	}
	
	
	private void createCardStatusLabel(boolean status) {
		
		String returnText = "";
		
		if(status){ 
			
			setBackground(new Color(66, 255, 164)); // Success Green
		
			returnText = ("<html>Payment for <br/><br/>$" + 
					
						posSystem.getTotalCost() + 
							
						"<br/><br/>Successful<br/><br/>Have a Nice Day!</html>");
				
		} else {	
	
			setBackground(new Color(255, 127, 107)); // Failure Red
			
			returnText = ("<html>Payment for <br/><br/>$" + 
					
							posSystem.getTotalCost() + 
							
							"<br/><br/>Declined <br/> <br/>Please Try again</html>");
				
		}
	
		Font f = new Font("Lucida Grande", Font.BOLD, 100);
		
		JLabel cardProcessed = createLabel(275, 0 , 900, 900, returnText, f);
		cardProcessed.setVerticalAlignment(SwingConstants.TOP);
		
		cardProcessed.setHorizontalAlignment(SwingConstants.CENTER);
	
	}
		
	
	private void createExpDateField() {
		
		Font f = new Font("Malayalam MN", Font.BOLD, 60);
		JTextField expDateField = createField(480, 530, 50,60, f, "MM/YYYY", 6);
		expDateField.addMouseListener(new MouseAdapter() { 
	          
			public void mousePressed(MouseEvent me) { 

				Font nf = new Font("Malayalam MN", Font.BOLD, 60);
				expDateField.setColumns(3);
				expDateField.setText("");
				expDateField.setFont(nf);
				expDateField.setForeground(Color.BLACK);
				
				frame.invalidate();
				frame.validate();
				frame.repaint();
				
	        }
			
	          });
		
		//SwingUtilities.updateComponentTreeUI(frame);	
    	
		expDateField.addKeyListener(new KeyAdapter() {
		    
			public void keyTyped(KeyEvent e) { 
		   
		        if (expDateField.getText().length() >= 5 ) // limit textfield to 3 characters
		            e.consume(); 
		    }  
		});
		
	}
	
	private void createCVVField() {
		
		Font f = new Font("Malayalam MN", Font.BOLD, 60);
		JTextField CVVField = createField(820, 530, 30, 60, f, "CVV", 4);
		CVVField.setHorizontalAlignment(JTextField.CENTER);
	
		CVVField.addMouseListener(new MouseAdapter() { 
	          
			public void mousePressed(MouseEvent me) { 

				Font nf = new Font("Malayalam MN", Font.BOLD, 60);
				CVVField.setColumns(3);
				CVVField.setText("");
				CVVField.setFont(nf);
				CVVField.setForeground(Color.BLACK);
				
				frame.invalidate();
				frame.validate();
				frame.repaint();
				
	        }
			
	          });
		
		//SwingUtilities.updateComponentTreeUI(frame);	
    	
		CVVField.addKeyListener(new KeyAdapter() {
		    
			public void keyTyped(KeyEvent e) { 
		   
		        if (CVVField.getText().length() >= 3 ) // limit textfield to 3 characters
		            e.consume(); 
		    }  
		});
	}
	
	private void createCardField() {
		
		Font f = new Font("Malayalam MN", Font.BOLD, 100);
		JTextField cardField = createField(260, 270, 905, 120, f, "Card Number", 10);
		cardField.setHorizontalAlignment(JTextField.CENTER);
		
		cardField.addMouseListener(new MouseAdapter() { 
	          
			public void mousePressed(MouseEvent me) { 

				Font nf = new Font("Malayalam MN", Font.BOLD, 60);
				cardField.setColumns(3);
				cardField.setText("");
				cardField.setFont(nf);
				cardField.setForeground(Color.BLACK);
				
				frame.invalidate();
				frame.validate();
				frame.repaint();
				
	        }
			
	          });
		
		//SwingUtilities.updateComponentTreeUI(frame);	
    	
		cardField.addKeyListener(new KeyAdapter() {
		    
			public void keyTyped(KeyEvent e) { 
		   
		        if (cardField.getText().length() >= 16) // limit textfield to 16 characters
		        	
		            e.consume();
		        
		    }  
		});
		
	}
	
	private void noPurchase() {
		
		setBackground(new Color(255, 235, 122));
		
		Font f = new Font("Lucida Grande", Font.BOLD, 140);
		JLabel message = createLabel(-110, 250 , 1650, 1080, "", f);
		message.setText("<html>No Purchase Made<br/>Have a Nice day!</html>");
		message.setHorizontalAlignment(JTextField.CENTER);
		SwingUtilities.updateComponentTreeUI(super.getPanel()); // Refresh the frame to display the label
		
		endProgram(2);
		
	}
	
	private void createBackButton() {
		
		Font f = (new Font("Apple Casual", Font.PLAIN, 70));
		
		ActionListener al = new ActionListener(){
			
			public void actionPerformed(ActionEvent ae) {
				
				main_window mw = new main_window(posSystem);
				
			}
			
		};
		
		JButton backButton = createButton(940, 430, 175, 100, al,"Back",  f);
		
		backButton.setForeground(new Color(255, 132, 0));
		
	}
	
	private JButton createPayButton() {
		
		ActionListener al = new ActionListener(){
			
			public void actionPerformed(ActionEvent ae) {
				
				if(!cardNum.equals(null) || cardNum.isBlank()) {
					
					if(posSystem.checkCard(cardNum)) {
						
						File receiptFile = new File("Receipt" + java.time.LocalDate.now() + ".txt");
			    		
						posSystem.exportToFile(receiptFile);
						
	//							frame.getContentPane().remove(endButton);
	//							frame.getContentPane().remove(backButton);
	//							frame.getContentPane().remove(expDateField);
	//							frame.getContentPane().remove(CVVField);
	//							cardInstructions.setVisible(false);
	//						//	displayPrice.setVisible(false);
	//							cardField.setVisible(false);
	//							frame.getContentPane().remove(payAmountButton);	
						
						getPanel().removeAll();
					
						createCardStatusLabel(true);
						
						endProgram(5);
							
						
						} else {
							
//							panel.remove(createBackButton());
//							panel.remove(expDateField);
//							panel.remove(CVVField);
//							cardInstructions.setVisible(false);
//						//	displayPrice.setVisible(false);
//							cardField.setVisible(false);
//							panel.remove(payAmountButton);
//							cardInstructions.setVisible(false);
//						//	displayPrice.setVisible(false);
							getPanel().removeAll();
							
							createCardStatusLabel(false);
							
								Timer timer = new Timer();
							
								timer.schedule(new TimerTask() {
						    	
									public void run() {
									
										getPanel().removeAll();
										
										paymentProcess();
						    	
									}
						    	
								}, 3000); 
							
							}
					
				} else {
					
					System.out.println("Error, item is null");
					
				}
				
				
			}
			
		};
		
		Font f = new Font("Apple Casual", Font.PLAIN, 70);
		
		JButton payButton = createButton(730, 430, 175, 100, al, "Pay", f);
				
			payButton.setForeground(new Color(73, 222, 113));
			
			return payButton;
		
	}
	
	private JButton createEndButton() {
		
		ActionListener al = new ActionListener(){
			
			public void actionPerformed(ActionEvent ae) {
				
				endProgram(0);
				
			}
			
		};
		
		Font f = new Font("Malayalam MN", Font.PLAIN, 30);
		
		JButton endButton = createButton(810, 550, 175, 60, al, "End", f);
	
	//	frame.getContentPane().add(endButton);
			
		return endButton;
			
	}
		
}
	
	
	
