package gui;
import forms.* ;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import beans.*;

public class MainFrame extends JFrame{
	JPanel mainPanel ;
	
	CardLayout cardLayout =new CardLayout() ;
	public MainFrame() {
		super("myHotel v1.0.0");
		setVisible(true);
		setSize(600,600);
		this.setBackground(Color.black);
		 Image iconImage = loadImage("logo4.png");

	        // Set the icon for the JFrame
	        setIconImage(iconImage);

		Build();
		
		
		revalidate();
		
	}
	 static Image loadImage(String imagePath) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            return image.getScaledInstance(32, 32, Image.SCALE_SMOOTH); // Adjust size as needed
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	private void Build() {
		
		
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setBackground(Color.black);
		
		mainPanel.setLayout(cardLayout);
		
		Forms();
	}
	 private JPanel home() {
	        JPanel homePanel = new JPanel(new BorderLayout());

	        // Welcome Message
	     //   JLabel welcomeLabel = new JLabel("Hotel DashBoard", JLabel.CENTER);
	      //  welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
	      //  homePanel.add(welcomeLabel, BorderLayout.NORTH);

	        // Image
	         // Replace with your image path
	        JLabel imageLabel = new JLabel();
	        homePanel.add(imageLabel, BorderLayout.CENTER);

	        ImageIcon backgroundImage = new ImageIcon("logo3.jpg"); // Remplacez par votre chemin d'image
	        JLabel backgroundLabel = new JLabel(backgroundImage);
	        homePanel.add(backgroundLabel, BorderLayout.CENTER);

	        return homePanel;
	    }
	private void Forms() {
		
		JPanel HomePane = home();
		
		JPanel clientForms = new ClientForms(new Client());
		JPanel clientSearch=new ClientForms(new Client(),1);
		JPanel reservationForms = new ReservationForms(new Reservation(new Client(),new Chambre(4,1,1,4),"",0));
		JPanel ChambreForms =new ChambreForms();
		JPanel ChambreS =new ChambreForms(new Chambre(), 1);
		JPanel occupationForm = new occupationForm();
		JPanel occupationS = new occupationForm(1);
		JPanel reservationSearch =new ReservationForms();
		Menu m = new Menu(HomePane,clientForms, reservationForms, mainPanel,occupationForm, cardLayout);
		setJMenuBar(m.getMainMenu());
		mainPanel.add(HomePane,"home");
		mainPanel.add(clientForms,"client");
		mainPanel.add(clientSearch,"clientS");
		mainPanel.add(reservationForms,"reservation");
		mainPanel.add(ChambreForms,"Chambre");
		mainPanel.add(ChambreS,"chambreS");
		mainPanel.add(occupationForm,"occupationForm");
		mainPanel.add(occupationS,"occupationS");
		mainPanel.add(reservationSearch,"reservationS");
		
		
	}

}
