package db;

import java.sql.Connection;
import java.sql.Date;

import objects.Classe;
import objects.Ride;
import objects.Station;
import objects.Ticket;
import objects.Train;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    public MySQLAccess() throws Exception {
    	try {
    		// This will load the MySQL driver, each DB has its own driver
    		Class.forName("com.mysql.jdbc.Driver");
    		 // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost/?"+ "user=root&password=");
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
    	} catch (Exception e) {
            throw e;
    	}
    }
    
    public List<Train> getTrains() throws Exception {
    	try {
			List<Train> trains = new ArrayList<>();
			resultSet = statement.executeQuery("select * from SIA.Train");
    		 while (resultSet.next()) {
			 	String trainID = resultSet.getString("TrainID");
				String type = resultSet.getString("Type");
				String name = resultSet.getString("Name");
				int seats_capacity = resultSet.getInt("SeatsCapacity");
				trains.add(new Train(trainID, type, name, seats_capacity));
    	        }
    		 return trains;
    	} catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    public List<Ride> getRides() throws Exception {
    	try {
			List<Ride> rides = new ArrayList<>();
			resultSet = statement.executeQuery("select * from SIA.Ride");
    		 while (resultSet.next()) {
    			 String rideID = resultSet.getString("RideID");
    			 String trainID = resultSet.getString("Train");
    			 String departureStationID = resultSet.getString("DepartureStation");
    			 String ArrivalStationID = resultSet.getString("ArrivalStation");
    			 Date departure_Date = resultSet.getDate("DepartureDate");
    			 Date arrival_Date = resultSet.getDate("ArrivalDate");
    			 rides.add(new Ride(rideID,trainID,departureStationID,ArrivalStationID,departure_Date,arrival_Date) );
    		 }
    		 return rides;
    	} catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    public List<Station> getStations() throws Exception {
    	try {
			List<Station> stations = new ArrayList<>();
			resultSet = statement.executeQuery("select * from SIA.Station");
    		 while (resultSet.next()) {
    			 String stationID = resultSet.getString("StationID");
				 String station_Name = resultSet.getString("StationName");
				 String city = resultSet.getString("City");
				 String department = resultSet.getString("Department");
    			 stations.add(new Station(stationID,station_Name,city,department) );
    		 }
    		 return stations;
    	} catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    public List<Ticket> getTickets() throws Exception {
    	try {
			List<Ticket> tickets = new ArrayList<>();
			resultSet = statement.executeQuery("select * from SIA.Ticket");
    		 while (resultSet.next()) {
    			 String ticketID = resultSet.getString("TicketID");
    			 String userID = resultSet.getString("User");
    			 String travelClass = resultSet.getString("TravelClass");
    			 String rideID = resultSet.getString("Ride");
    			 String seat = resultSet.getString("Seat");
    			 float price = resultSet.getFloat("Price");
                tickets.add(new Ticket(ticketID,userID,travelClass,rideID,seat,price) );
    		 }
    		 return tickets;
    	} catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    public List<Classe> getClasses() throws Exception {
    	try {
			List<Classe> classes = new ArrayList<>();
			resultSet = statement.executeQuery("select * from SIA.Classe");
    		 while (resultSet.next()) {
                String classeID = resultSet.getString("ClasseID");
                String name = resultSet.getString("ClassName");
                int multiplier = resultSet.getInt("PriceMultiplier");
                classes.add(new Classe(classeID,name,multiplier) );
    		 }
    		 return classes;
    	} catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
   
    
    public Boolean bookTicket( String UserId, String TravelClass, String RideID, int Seat, String Type, Float Price) throws Exception {
    	try {
    		preparedStatement = connect
                    .prepareStatement("insert into  feedback.Ticket values (NULL, ?, ?, ?, ? , ?, ?)");
    		preparedStatement.setString(1, UserId);
            preparedStatement.setString(2, TravelClass);
            preparedStatement.setString(3, RideID);
            preparedStatement.setInt(4, Seat);
            preparedStatement.setFloat(5, Price);
            preparedStatement.setString(6, Type);
            preparedStatement.executeUpdate();
            return true;
    	} catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}