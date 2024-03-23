package forms;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import beans.Client;

import database.Clientdb;

public class ClientForms extends GeneralForms implements ActionListener{

	
	JLabel l1 ;
	int count =0;
	Client g ;
	DefaultListModel<Client> listModel;
	DefaultTableModel tableModel;
	JLabel idLabel;
	public ClientForms(Client g,int j) {
		setBackground(Color.WHITE );
		LabeledTextField searchBar =new LabeledTextField("Nom :",15,100);
		JButton searchB = Buttons.createStyledButton("Search");
		JButton allSearch = Buttons.createStyledButton("Show all");
		searchB.addActionListener(this);
		allSearch.addActionListener(this);
		/* listModel = new DefaultListModel<>();
		JList<Client> ResultList =new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(ResultList);
		*/
		tableModel = new DefaultTableModel();
         JTable resultTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable);

		this.add(searchBar);
		
		this.add(searchB);
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
	   private void updateTableModel(DefaultTableModel tableModel, List<Client> searchResults) {
	        // Clear the current contents of the tableModel
	        tableModel.setRowCount(0);
	        tableModel.setColumnCount(0);

	        // Add the search results to the tableModel
	        // Assuming Client class has getNom(), getPays(), getVille() methods
	        tableModel.addColumn("ID:");
	        tableModel.addColumn("Nom Complet");
	        tableModel.addColumn("Pays");
	        tableModel.addColumn("Ville");

	        for (Client client : searchResults) {
	            Object[] rowData = {client.getId(),client.getNom(), client.getPays(), client.getVille()};
	            tableModel.addRow(rowData);
	        }
	    }
	public ClientForms(Client g) {
		
		super(g);
		this.setBackground(Color.white);
		try {
			Clientdb cdb =new Clientdb();
		 idLabel =new JLabel("Id :"+(cdb.getLastClientId()+1));
		 idLabel.setBackground(new Color(0,51,0));
		
		 revalidate();
		}
		catch(SQLException e) {}
		setBackground(Color.WHITE);
		
		JPanel p=new JPanel();		
		p.setLayout(new FlowLayout(FlowLayout.LEFT));
		p.add(idLabel);
		p.setBackground(Color.white);
		
		
		JPanel p2=new JPanel();
		Buttons buttons = new Buttons(new String[] {"Enregistrer","Quitter","Reinitialiser"});
		p2.add(buttons );
		p2.setBackground(Color.white);
		add(p,BorderLayout.NORTH);
		add(p2,BorderLayout.SOUTH);
		
		
		for ( Component button : buttons.getComponents()) {
				((JButton )button).addActionListener(this);
		}
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Clientdb cdb =new Clientdb();
		if (((JButton)e.getSource()).getText() =="Enregistrer") {
			List<String> tab=new ArrayList<String>();
			for( LabeledTextField l :labelsTexts) {
				tab.add(l.getText());}
			
			
			try {
				
				cdb.ecriture(tab);
				idLabel.setText("id :"+(cdb.getLastClientId()+1));
				
				revalidate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
				
		}
					
				
		else if(((JButton)e.getSource()).getText() =="Reinitialiser") {
			for( LabeledTextField l :labelsTexts) {
				l.setText("");
							}
		}
		else if(((JButton)e.getSource()).getText() =="Quitter") {
			 JFrame f =(JFrame) SwingUtilities.getWindowAncestor(this);
        	 f.dispose();
		}
		else if(((JButton)e.getSource()).getText() =="Search" ||
				((JButton)e.getSource()).getText() =="Show all") {
			JButton b =(JButton)e.getSource();
			JPanel p =(JPanel) b.getParent();
			LabeledTextField ltf = (LabeledTextField)p.getComponent(0);
			
			try {
				updateTableModel(tableModel, cdb.lecture(ltf.getText()));
			//	updateListModel(listModel,cdb.lecture(ltf.getText()) );
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}}


