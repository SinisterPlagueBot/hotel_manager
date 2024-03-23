package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener{
	JPanel Home ;
	JMenuBar mainBar;
	JMenuItem AClient , MClient;
	JMenuItem Chambres , AChambres, IDChambre;
	JMenuItem Reservation , CReservation ,MReservation ;
	JMenuItem AttChambre,VerOccup ,ChambreOccupe;
	JPanel occupForm;
	 JPanel clientForms;
	 JPanel chambreForms;
	    JPanel reservationForms;
	    CardLayout cardLayout;
	    JPanel mainPanel ;
	    JButton HomeMen;
	public Menu(JPanel Home,JPanel clientForms ,JPanel reservationForms,JPanel mainPanel ,JPanel occupForm,CardLayout cardLayout) {
		// MAIN MENU
		mainBar = new JMenuBar();
		
		this.Home = Home;
		this.clientForms =clientForms;
    	this.reservationForms=reservationForms;
    	this.occupForm =occupForm ;
    	this.mainPanel = mainPanel ;
    	this.cardLayout = cardLayout;
    	
		HomeMen = new JButton("home");
		HomeMen.setForeground(new Color(0,51,0));
		HomeMen.setBackground(Color.white);
		HomeMen.addActionListener(this);
		HomeMen.setFocusable(false);
		 HomeMen.setFocusPainted(false);
		// CLIENT MENU
			// service client
		AClient = new JMenuItem("Ajouter Client");
		MClient = new JMenuItem("chercher Client");
			// action listener
		AClient.addActionListener(this);
		MClient.addActionListener(this);
			//ajout des elements
		JMenu clientMenu = new JMenu("Clients");
		clientMenu.setForeground(Color.white);
		clientMenu.add(AClient);
		clientMenu.addSeparator();
		clientMenu.add(MClient);
		
		
		
		// CHAMBRE MENU
		JMenu chambreMenu = new JMenu("Chambres");
		chambreMenu.setForeground(Color.white);
			// Services :  ,CChambres, IDChambre;
		Chambres = new JMenuItem("Ajouter Chambre");
		Chambres.addActionListener(this);
		AChambres = new JMenuItem("Afficher infos chambre");
		AChambres.addActionListener(this);
		IDChambre = new JMenuItem("ID client dans la chambre");
		IDChambre.addActionListener(this);
			// ajout des éléments
		chambreMenu.add(Chambres);
		chambreMenu.addSeparator();
		chambreMenu.add(AChambres);
		chambreMenu.addSeparator();
		chambreMenu.add(IDChambre);
		
		
		
		// RESERVATION MENU
		JMenu reservationMenu = new JMenu("Reservations");
		reservationMenu.setForeground(Color.white);
			//services :Reservation , CReservation ,MReservation
		Reservation = new JMenuItem("Reserver chambre");
		Reservation.addActionListener(this);
		CReservation = new JMenuItem("Chercher Reservation");
		CReservation.addActionListener(this);
		
		
			// ajout des éléments
		reservationMenu.add(Reservation);
		reservationMenu.addSeparator();
		reservationMenu.add(CReservation);
		
		
		
		// OCCUPATION MENU
		JMenu occupationMenu = new JMenu("Occupations");
		occupationMenu.setForeground(Color.white);
			// services :AttChambre,VerOccup ,ChambreOccupe;
		
		VerOccup = new JMenuItem("occuper chambre");
		VerOccup.addActionListener(this);
		ChambreOccupe = new JMenuItem("Chambres occupées");
		ChambreOccupe.addActionListener(this);
		
		occupationMenu.add(VerOccup);
		occupationMenu.addSeparator();
		occupationMenu.add(ChambreOccupe );
		
		// add menu to menubar
		mainBar.setBackground(new Color(0,0,0));
		
		mainBar.add(HomeMen);
		mainBar.add(clientMenu);
		mainBar.add(chambreMenu);
		mainBar.add(reservationMenu);
		mainBar.add(occupationMenu);
        this.add(mainBar);
	}
	
	public JMenuBar getMainMenu() {
		
		return mainBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        // Handle button click events here
        if (e.getSource() == AClient) {
            cardLayout.show(mainPanel,"client");
            
        }
        else if (e.getSource() == MClient) {
            // Handle Modifier Client button click
        	cardLayout.show(mainPanel,"clientS");
        }
        else if (e.getSource() == Reservation) {
            // Handle Modifier Client button click
        	cardLayout.show(mainPanel,"reservation");
        }
        else if (e.getSource() == Chambres) {
            // Handle Modifier Client button click
        	cardLayout.show(mainPanel,"Chambre");
        }
        else if (e.getSource() == AChambres) {
            // Handle Modifier Client button click
        	cardLayout.show(mainPanel,"chambreS");
        }
        else if (e.getSource() == HomeMen) {
            // Handle Modifier Client button click
        	cardLayout.show(mainPanel,"home");
        	
        }
        else if ((JMenuItem)e.getSource() == VerOccup) {
            // Handle Modifier Client button click
        	cardLayout.show(mainPanel,"occupationForm");
        	
        }
        else if ((JMenuItem)e.getSource() == ChambreOccupe ) {
            // Handle Modifier Client button click
        	cardLayout.show(mainPanel,"occupationS");
        	
        }
        else if ((JMenuItem)e.getSource() == CReservation) {
            // Handle Modifier Client button click
        	cardLayout.show(mainPanel,"reservationS");
        	
        }
        
        
        // ... Repeat the above if conditions for other buttons ...
    }

}