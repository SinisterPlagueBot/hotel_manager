package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Chambre;
import beans.Client;
import beans.Reservation;
import forms.ReservationForms;

public class Reservationdb {

	ReservationForms formulaire;
	private String url = "jdbc:mysql://localhost:3306/tpJava"; // database URL
	private String username = "root";
	private String password = "1234";
	public void ecriture(List<String>tab, String date) throws SQLException {
		String insertQuery = "INSERT INTO reservation (idClient,idChambre,duree,date) VALUES (?, ?, ?, ?)";
		try (Connection connection = DriverManager.getConnection(url, username, password);
	             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
			preparedStatement.setInt(1, Integer.parseInt(tab.get(0)));
            preparedStatement.setInt(2, Integer.parseInt(tab.get(1)));
            preparedStatement.setInt(3, Integer.parseInt(tab.get(2)));
            preparedStatement.setString(4, date);// date from jcombox box
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
	public List<Reservation> lectureReservation() throws SQLException {
		List<Reservation> reservations = new ArrayList<>();
		 String query = "SELECT reservation.*, client.*, chambre.* " +
                 "FROM reservation  " +
                 "JOIN client  ON reservation.idClient= client.id " +
                 "JOIN chambre  ON reservation.idChambre = chambre.numero " +
                 "WHERE reservation.occupied = 0 ";
        try (Connection connection = DriverManager.getConnection(url, username, password);
        		 PreparedStatement preparedStatement = connection.prepareStatement(query)){
               

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                    	int id =resultSet.getInt("id");
                        String  nomComp = resultSet.getString("nom");
                        String pays = resultSet.getString("pays");
                        String ville = resultSet.getString("ville");
                        Client c =new Client(id,nomComp,ville,pays);
                        int num =resultSet.getInt("numero");
                        int type=resultSet.getInt("type");
                        int state=resultSet.getInt("state");
                        double prix =resultSet.getDouble("prix");
                        Chambre  ch = new Chambre(num,type,state,prix);
                        String dateS = resultSet.getString("date");
                        int DureeS = resultSet.getInt("duree");
                        Reservation reservation = new Reservation(c,ch,dateS,DureeS);
                        reservations.add(reservation);
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;	
	}
	public List<Reservation> lectureOccup() throws SQLException {
		List<Reservation> reservations = new ArrayList<>();
		 String query = "SELECT reservation.*, client.*, chambre.* " +
                 "FROM reservation  " +
                 "JOIN client  ON reservation.idClient= client.id " +
                 "JOIN chambre  ON reservation.idChambre = chambre.numero " +
                 "WHERE reservation.occupied = 1 ";
        try (Connection connection = DriverManager.getConnection(url, username, password);
        		 PreparedStatement preparedStatement = connection.prepareStatement(query)){
               

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                    	int id =resultSet.getInt("id");
                        String  nomComp = resultSet.getString("nom");
                        String pays = resultSet.getString("pays");
                        String ville = resultSet.getString("ville");
                        Client c =new Client(id,nomComp,ville,pays);
                        int num =resultSet.getInt("numero");
                        int type=resultSet.getInt("type");
                        int state=resultSet.getInt("state");
                        double prix =resultSet.getDouble("prix");
                        Chambre  ch = new Chambre(num,type,state,prix);
                        String dateS = resultSet.getString("date");
                        int DureeS = resultSet.getInt("duree");
                        Reservation reservation = new Reservation(c,ch,dateS,DureeS);
                        reservations.add(reservation);
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;	
	}
	public int getLastReservationId() throws SQLException {
	    String query = "SELECT MAX(idReservation) as max_id FROM reservation";
	    
	    try (Connection connection = DriverManager.getConnection(url, username, password);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                int maxId = resultSet.getInt("max_id");
	                return maxId+1;
	            }
	            else {
	            	return 0;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle the exception or rethrow it as needed
	    }
	    return 0; // Default value if no result is found
	}
	public void updateOccupiedStatus(int reservationId) throws SQLException {
        String query = "UPDATE reservation SET occupied = 1 WHERE idReservation = ?";
        String updateRoomQuery = "UPDATE chambre SET state = 1 WHERE numero = (SELECT idChambre FROM reservation WHERE idReservation = ?)";
        
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query) ;
        	 PreparedStatement updateRoomStatement = connection.prepareStatement(updateRoomQuery)){
            preparedStatement.setInt(1, reservationId);
            updateRoomStatement.setInt(1, reservationId);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Occupied status updated successfully!");
            } else {
                System.out.println("No reservation found with ID: " + reservationId);
            }
        }
    }

}
