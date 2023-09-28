package testWindowBuilder;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.SpringLayout;

public class main_class {

	//private JFrame frame;
	
	public main_class() {
		
	
			
		
	}
	
	public static void main(String[] args) {
		
		POS posSystem = new POS();
		
		posSystem.importIntoDirectory(new File("Database.csv"));
		
		window w = new main_window(posSystem);
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					
					w.frame.setVisible(true);
					
					w.frame.setLocationRelativeTo(null);
					
					w.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
			}
		});
       
	}
	
}
