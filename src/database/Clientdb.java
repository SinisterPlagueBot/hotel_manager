package database;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import beans.Client;
import forms.ClientForms;
import forms.GeneralForms;

public class Clientdb  {
	ClientForms formulaire;
	private String url = "jdbc:mysql://localhost:3306/tpJava"; // database URL
	private String username = "root";
	private String password = "1234";
	public Clientdb() {
	}
	public void ecriture(List<String>tab) throws SQLException {
		String insertQuery = "INSERT INTO client (nom, ville, pays) VALUES (?, ?, ?)";
		try (Connection connection = DriverManager.getConnection(url, username, password);
	             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
			preparedStatement.setString(1, tab.get(0));
            preparedStatement.setString(2, tab.get(1));
            preparedStatement.setString(3, tab.get(2));
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Failed to insert data.");
            }
		}
		catch (SQLException e) {
        e.printStackTrace();
    }
		
	}

	public List<Client> lecture(String nom) throws SQLException {
		List<Client> clients = new ArrayList<>();
		String query = "SELECT * FROM client WHERE nom LIKE  ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
        		 PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1,"%"+ nom+"%");

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                    	int id =resultSet.getInt("id");
                        String name = resultSet.getString("nom");
                        String pays = resultSet.getString("pays");
                        String ville = resultSet.getString("ville");

                        Client client = new Client(id,name, pays, ville);
                        clients.add(client);
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;	
	}
	public int getLastClientId() throws SQLException {
	    String query = "SELECT MAX(id) as max_id FROM client";
	    
	    try (Connection connection = DriverManager.getConnection(url, username, password);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                int maxId = resultSet.getInt("max_id");
	                return maxId;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle the exception or rethrow it as needed
	    }
	    return 0; // Default value if no result is found
	}

}