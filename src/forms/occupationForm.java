package forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import beans.Reservation;
import database.Reservationdb;

public class occupationForm extends JPanel implements ActionListener{
	
	

LabeledTextField [] labelsTexts ;// to get the an array of labels in order to set there text or get there text 
 LabeledTextField searchR;
 DefaultTableModel tableModel;
public occupationForm() {
	JPanel p=new JPanel();
 searchR = new LabeledTextField(" occuper reservation[iD] : ",10,200);
 searchR.setPreferredSize(new Dimension(120,60));
	 JButton searchB = Buttons.createStyledButton("occuper");
	 searchB.addActionListener(this);
	 p.add(searchR);
	 p.add(searchB);
	p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
	setLayout(new BorderLayout());
	add(p,BorderLayout.CENTER);
	revalidate();
}
public occupationForm(int i) {
	
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
        tableModel.addColumn("Nom Client:");
        tableModel.addColumn("num Chambre");
        tableModel.addColumn("Date");
        tableModel.addColumn("Duree");

        for (Reservation reservation : searchResults) {
            Object[] rowData = {reservation.getClient().getNom(),reservation.getChambre().getNum(),reservation.getDate() ,reservation.getNbreJours(), };
            tableModel.addRow(rowData);
        }
    }

@Override
public void actionPerformed(ActionEvent e) {
	Reservationdb rdb = new Reservationdb();
	if(((JButton)e.getSource()).getText()=="occuper") {
		try {
			rdb.updateOccupiedStatus(Integer.parseInt(searchR.getText()));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	else if(((JButton)e.getSource()).getText() =="Show all") {
		 
		 try {
				updateTableModel(tableModel, rdb.lectureOccup());
			//	updateListModel(listModel,cdb.lecture(ltf.getText()) );
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
}
}