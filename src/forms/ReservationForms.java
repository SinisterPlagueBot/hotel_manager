package forms;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import beans.Client;
import beans.Reservation;
import database.Clientdb;
import database.Reservationdb;

public class ReservationForms extends GeneralForms implements ActionListener{

	JLabel idLabel;
	JComboBox<String> JOUR ;
	JComboBox<String> MOIS ;
	JComboBox<String> ANNEE ;
	DefaultTableModel tableModel;
	public ReservationForms() {
		
	setBackground(Color.WHITE);

	JButton allSearch = Buttons.createStyledButton("Show all");
	
	allSearch.addActionListener(this);
	/* listModel = new DefaultListModel<>();
	JList<Client> ResultList =new JList<>(listModel);
	JScrollPane scrollPane = new JScrollPane(ResultList);
	*/
	tableModel = new DefaultTableModel();
     JTable resultTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(resultTable);

	
	add(allSearch);
//	add(ResultList);
	add(scrollPane);
}
  /* private static void updateListModel(DefaultListModel<Client> listModel, List<Client> searchResults) {
        // Clear the current contents of the listModel
        listModel.clear();

        // Add the search results to the listModel
        for (Client obj : searchResults) {
            listModel.addElement(obj);
        }
    }
    */
   private void updateTableModel(DefaultTableModel tableModel, List<Reservation> searchResults) {
        // Clear the current contents of the tableModel
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);

        // Add the search results to the tableModel
        // Assuming Client class has getNom(), getPays(), getVille() methods
       // tableModel.addColumn("Id reservation :");
        tableModel.addColumn("Nom Client:");
        tableModel.addColumn("num Chambre");
        tableModel.addColumn("Date");
        tableModel.addColumn("Duree");

        for (Reservation reservation : searchResults) {
            Object[] rowData = {reservation.getClient().getNom(),reservation.getChambre().getNum(),reservation.getDate() ,reservation.getNbreJours(), };
            tableModel.addRow(rowData);
        }
    }
	public ReservationForms(Reservation g) {
		super(g);
		Reservationdb rdb = new Reservationdb();
		try {
			idLabel =new JLabel("Id : "+(rdb.getLastReservationId()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 idLabel.setBackground(new Color(0,51,0));
		
		 JPanel p= new JPanel();
		JLabel l=new JLabel("Date");
		p.add(idLabel);
		l.setBorder(new EmptyBorder(0,0,0,60));
		
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		
		String[] jours= {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
		JOUR=new JComboBox<>(jours);
		String[] mois= {"1","2","3","4","5","6","7","8","9","10","11","12"};
		MOIS=new JComboBox<>(mois);
		String[] annnes= {"2023","2024","2025","2026","2027","2028","2029","2030"};
		 ANNEE=new JComboBox<>(annnes);

		
		JPanel p2= new JPanel();p2.add(l); 
		p2.add(JOUR); p2.add(MOIS); p2.add(ANNEE); 
		p.add(p2);
		
		JPanel p1=new JPanel();
		Buttons bgroup =new Buttons(new String[] {"Réservation","Reset","Quitter"});
		p1.add(bgroup);
		
		this.add(BorderLayout.SOUTH, p1);
		this.add(BorderLayout.NORTH, p);
		for ( Component button :bgroup.getComponents()) {
			((JButton )button).addActionListener( this);
	}
		
	}

	public void actionPerformed(ActionEvent e) {
		
		if (((JButton)e.getSource()).getText() =="Réservation") {
			Reservationdb Rdb =new Reservationdb();
			 String jour = (String) JOUR.getSelectedItem();
		     String mois = (String) MOIS.getSelectedItem();
		     String annee = (String) ANNEE.getSelectedItem();
		        
		        // Concatenate the selected values into a date string
		     String date = annee + "-" + mois + "-" + jour;
			List<String> tab=new ArrayList<String>();
			for( LabeledTextField l :labelsTexts) {
				tab.add(l.getText());
				}
			
			try {
				
				Rdb.ecriture(tab,date);
				idLabel.setText("id :"+Rdb.getLastReservationId());
				
				revalidate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}}
			else if(((JButton)e.getSource()).getText() =="Reset"){
				
				for( LabeledTextField l :labelsTexts) {
					l.setText("");
								}
			}
			else if(((JButton)e.getSource()).getText() =="Quitter") {
				 JFrame f =(JFrame) SwingUtilities.getWindowAncestor(this);
	        	 f.dispose();
			}
			else if(((JButton)e.getSource()).getText() =="Show all") {
				 Reservationdb rdb = new Reservationdb();
				 
				 try {
						updateTableModel(tableModel, rdb.lectureReservation());
					//	updateListModel(listModel,cdb.lecture(ltf.getText()) );
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
	
				
		}
					
	}
	
	 

