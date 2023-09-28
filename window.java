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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
	import javax.swing.SwingUtilities;

public class window {
	
	protected JFrame frame;
	
	protected Container panel;
	
	protected SpringLayout layout;
	
	public window() {
		
		frame = new JFrame();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		layout = new SpringLayout();
		
		panel = frame.getContentPane();
		
		panel.setLayout(layout);
		
		frame.setVisible(true);
		
	}
	
	public JLabel createLabel(int x, int y, int width, int height, String text, Font userFont) {
		
		JLabel returnLabel = new JLabel();
		returnLabel.setText(text);
		returnLabel.setFont(userFont);
		returnLabel.setBounds(x, y , width, height); //(int x, int y, width, height)
		layout.putConstraint(SpringLayout.WEST, returnLabel, x,SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, returnLabel, y, SpringLayout.NORTH, panel);
		panel.add(returnLabel);
		return returnLabel;
		
	}
	
	public JLabel createLabelForList(int x, int y, int width, int height, String text, Font userFont) {
		
		JLabel returnLabel = new JLabel(text);
		returnLabel.setFont(userFont);
		returnLabel.setBounds(x, y , width, height); //(int x, int y, width, height)
		return returnLabel;
		
	}
	
	public JButton createButton(int x, int y, int width, int height, ActionListener al, String userText, Font userFont) {
		
		JButton userButton = new JButton(userText);
		
		userButton.setBounds(x, y, width, height); //(int x, int y, width, height)
		
		layout.putConstraint(SpringLayout.WEST, userButton,
	              x,
	              SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, userButton,
	              y,
	              SpringLayout.NORTH, panel);
		
		userButton.setFont(userFont);
	
		userButton.addActionListener(al);
		
		userButton.setName(userText + "button");
		
		panel.add(userButton);
		
		return userButton;
		
	}
	
	
	
	public JTextField createField(int x, int y, int width, int height, Font userFont, String userText,  int columnLength) {
		
		JTextField returnField = new JTextField(columnLength);
		returnField.setFont(userFont);
		returnField.setBounds(x, y, width - 20, height); //int x, int y, width, height
		returnField.setPreferredSize(new Dimension(width, height));
		layout.putConstraint(SpringLayout.WEST, returnField, x,SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, returnField, y, SpringLayout.NORTH, panel);
		returnField.setText(userText);
		returnField.setHorizontalAlignment(JTextField.CENTER);
		panel.add(returnField);
		
		return returnField;
		
	}
	
	public JList createList(int x, int y, int width, int height) {
		
		JList returnList = new JList();
		
		returnList.setBounds(x, y, width, height);
		
		layout.putConstraint(SpringLayout.WEST, returnList, x,SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, returnList, y, SpringLayout.NORTH, panel);
		returnList.setVisible(true);
		panel.add(returnList);
		
		return returnList;
		
	}
	
	public void endProgram(int time) {
		
		Timer timer = new Timer();
		
	    timer.schedule(new TimerTask() {
	    	
	    	public void run() {
			
			System.exit(0);
	    	
	    	}
	    	
	    }, time * 1000); 
				
	}
	
	public JFrame getFrame() {
		
		return frame;
		
	}
	
	public Container getPanel() {
		
		return panel;
	}
	
	public void setBackground(Color c) {
		
		panel.setBackground(c);
		
	}

}
