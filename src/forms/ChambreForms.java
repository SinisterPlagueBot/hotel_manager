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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import beans.Chambre;
import beans.Client;
import database.Chambredb;
import database.Clientdb;

public class ChambreForms  extends GeneralForms implements ActionListener{
	private static Chambre g =new Chambre(4,1,1,4);
	private static final long serialVersionUID = 1L;
	DefaultListModel listModel ;

public ChambreForms(Chambre g,int j) {
	setBackground(Color.WHITE);
		LabeledTextField searchBar =new LabeledTextField("Search By Num",10,90);
		JButton searchB = Buttons.createStyledButton("search");
		searchB.addActionListener(this);
		JButton searchALL = Buttons.createStyledButton("show all");
		searchALL.addActionListener(this);
		listModel = new DefaultListModel<>();
		JList<Chambre> ResultList =new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(ResultList);
		
		this.add(searchBar);
		this.add(searchB);
		add(searchALL);
		add(ResultList);
		add(scrollPane);
		revalidate();
	}
	   private static void updateListModel(DefaultListModel<Chambre> listModel, List<Chambre> searchResults) {
	        // Clear the current contents of the listModel
	        listModel.clear();

	        // Add the search results to the listModel
	        for (Chambre obj : searchResults) {
	            listModel.addElement(obj);
	        }
	    }
	public ChambreForms() {
		super(g);
		setBackground(Color.WHITE);
		
		JPanel p= new JPanel();
		
		p.setLayout(new FlowLayout(FlowLayout.LEFT));
		p.setBackground(Color.white);
		
		JPanel p1=new JPanel();
		Buttons bGroup =new Buttons(new String[] {"Enregistrer","Reinitialiser","Quitter"});
		p1.add(bGroup);
		p1.setBackground(Color.white);
		for ( Component button :bGroup.getComponents()) {
			((JButton )button).addActionListener(this);
	}


		add(BorderLayout.SOUTH, p1);
		add(BorderLayout.NORTH, p);
		revalidate();
		
	}
	public void actionPerformed(ActionEvent e) {
		Chambredb cdb =new Chambredb();
		if (((JButton)e.getSource()).getText() =="Enregistrer") {
			List<String> tab=new ArrayList<String>();
			for( LabeledTextField l :labelsTexts) {
				tab.add(l.getText());}
			
			
			try {
				cdb.ecriture(tab);
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
		 else if(((JButton)e.getSource()).getText() =="search") {
			JButton b =(JButton)e.getSource();
			JPanel p =(JPanel) b.getParent();
			LabeledTextField ltf = (LabeledTextField)p.getComponent(0);
			
			
				
				updateListModel(listModel,cdb.lecture(ltf.getText(),0) );
			
		 }
		 else if(((JButton)e.getSource()).getText() =="show all") {
				JButton b =(JButton)e.getSource();
				JPanel p =(JPanel) b.getParent();
				LabeledTextField ltf = (LabeledTextField)p.getComponent(0);
				
				
					
					updateListModel(listModel,cdb.lecture(ltf.getText()) );
				
			 }
		
	}
}
	
	

