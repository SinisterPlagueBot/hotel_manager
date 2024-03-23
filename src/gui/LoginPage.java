package gui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import forms.LoginForm;

public class LoginPage extends JFrame {
	
	public LoginPage() {
		super("LoginPage");
		setVisible(true);
		setSize(350,300);
		form();
		
		 Image iconImage = MainFrame.loadImage("logo4.png");

	        // Set the icon for the JFrame
	        setIconImage(iconImage);
	        
		revalidate();
		
	}
	private void form() {
		
	LoginForm l = new LoginForm();
	
	l.setOpaque(false);
	setContentPane(l);
	
}
}