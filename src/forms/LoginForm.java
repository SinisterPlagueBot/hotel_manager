package forms;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import gui.MainFrame;

public class LoginForm extends JPanel  implements ActionListener{
	LabeledTextField username;
	LabeledTextField password ;
	public LoginForm() {
		username =new LabeledTextField("  Username : ",10,120);
		password =new LabeledTextField("  Password : ",10,120);
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setBackground(Color.GREEN);
		setOpaque(true);
		
	//	username.setText("admin1");
	//	password.setText("pass1");
		add(username);
		add(password);
		JPanel buttons =new JPanel();
		JButton login = createStyledButton("Login ");
		buttons.add(login);
		JButton reset = 
				createStyledButton("Reset");
		login.addActionListener(this);
		reset.addActionListener(this);
		buttons.add(reset);
		JButton quit = 
				createStyledButton(" Quit  ");
		quit.addActionListener(this);
		buttons.add(quit);
		add(buttons);
	}
	private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(59, 89, 182)); // Customize background color
        button.setFocusPainted(false); // Remove focus border
        button.setBorderPainted(false); // Remove button border
        button.setPreferredSize(new Dimension(100, 40)); // Set preferred size

        // Add hover effect
       
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.white);
                button.setForeground(Color.black);// Change color on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(59, 89, 182)); // Restore original color
                button.setForeground(Color.white);
            }
        });
        return button;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (((JButton) e.getSource()).getText()=="Login ") {
	            // Perform authentication
	            if (authenticateUser(username.getText(), password.getText())) {
	            	MainFrame m = new MainFrame();
	            	 JFrame f =(JFrame) SwingUtilities.getWindowAncestor(this);
	            	 f.dispose();
	            	//JOptionPane.showMessageDialog(this, "Login Successful");
	                // Add code to open a new window or perform other actions upon successful login
	            } else {
	                JOptionPane.showMessageDialog(this, "Login Failed. Please check your credentials.");
	            }}
		else if(((JButton )e.getSource()).getText()=="Reset"){
			username.setText("");
			password.setText("");
		}
		else if(((JButton )e.getSource()).getText()==" Quit  "){
			JFrame f =(JFrame) SwingUtilities.getWindowAncestor(this);
       	 f.dispose();
		}
	}
	

	private boolean authenticateUser(String enteredUsername, String enteredPassword) {
		 // Database connection details
        String url = "jdbc:mysql://localhost:3306/tpJava";
        String user = "root";
        String dbPassword = "1234";

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            Connection connection = DriverManager.getConnection(url, user, dbPassword);

            // Prepare the SQL statement
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, enteredUsername);
                statement.setString(2, enteredPassword);

                // Execute the query
                ResultSet resultSet = statement.executeQuery();

                // Check if the user exists and the password is correct
                return resultSet.next();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
		return false;
	}
}}