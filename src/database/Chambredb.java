package database;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import beans.Chambre;
import beans.Client;
import forms.ClientForms;
import forms.GeneralForms;

import java.util.List;

public class Chambredb {
	private String url = "jdbc:mysql://localhost:3306/tpJava"; // database URL
	private String username = "root";
	private String password = "1234";
	
	public Chambredb() {
		
	}
	public void ecriture(List<String> tab) throws SQLException {
		// TODO Auto-generated method stub
		String insertQuery = "INSERT INTO chambre (numero, type,state, prix) VALUES (?, ?, ?, ?)";
		try (Connection connection = DriverManager.getConnection(url, username, password);
	             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
			preparedStatement.setInt(1, Integer.parseInt(tab.get(0)));//Integer.parseInt(strNumber)
            preparedStatement.setInt(2, Integer.parseInt(tab.get(1)));
            preparedStatement.setInt(3,  Integer.parseInt(tab.get(2)));
            preparedStatement.setDouble(4, Double.parseDouble(tab.get(3)));
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

	
	public List<Chambre> lecture(String s,int j) {
		List<Chambre> chambres = new ArrayList<>();
		String query ="SELECT * FROM chambre WHERE numero = ?";
	 ;
		
        try (Connection connection = DriverManager.getConnection(url, username, password);
        		 PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setInt(1,Integer.parseInt(s) );

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int numero = resultSet.getInt("numero");
                        double prix = resultSet.getDouble("prix");
                        int state = resultSet.getInt("state");
                        int type = resultSet.getInt("type");
                        Chambre chambre = new Chambre(numero,type,state,prix);
                        chambres.add(chambre);
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chambres;	
		
	}
	public List<Chambre> lecture(String s) {
		List<Chambre> chambres = new ArrayList<>();
		String query ="SELECT * FROM chambre ";
	 ;
		
        try (Connection connection = DriverManager.getConnection(url, username, password);
        		 PreparedStatement preparedStatement = connection.prepareStatement(query)){
                

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int numero = resultSet.getInt("numero");
                        double prix = resultSet.getDouble("prix");
                        int state = resultSet.getInt("state");
                        int type = resultSet.getInt("type");
                        Chambre chambre = new Chambre(numero,type,state,prix);
                        chambres.add(chambre);
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chambres;	
		
	}

}