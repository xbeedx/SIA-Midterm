package db;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;

import objects.Reservation;
import objects.Station;
import objects.Train;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    public MySQLAccess() {
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://mysql-container:3306/SIA?useSSL=false&serverTimezone=UTC&user=root&password=");

            statement = connect.createStatement();
    	} catch (Exception e) {
    		 System.out.println("Error during MySQLAccess initialization: " + e.getMessage());
	        e.printStackTrace();
    	}
    }

    public List<Train> getTrains(String departureStation, String arrivalStation, String departureDate, String returnDate, String numTickets, String travelClass) {
        List<Train> trains = new ArrayList<>();
    
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime parsedDepartureDate = LocalDate.parse(departureDate, formatter).atStartOfDay();
            LocalDateTime parsedReturnDate = LocalDate.parse(returnDate, formatter).atStartOfDay().plusDays(1);

            departureStation = URLDecoder.decode(departureStation, "UTF-8");
            arrivalStation = URLDecoder.decode(arrivalStation, "UTF-8");
            travelClass = URLDecoder.decode(travelClass, "UTF-8");

            preparedStatement = connect.prepareStatement(
                "SELECT * FROM Train " +
                "WHERE DepartureStation = ? AND ArrivalStation = ? " +
                "AND departureDate >= ? AND arrivalDate <= ? " +
                "AND NumTickets >= ? AND TravelClass = ?"
            );

            // Set the parameters in the prepared statement
            preparedStatement.setString(1, departureStation);
            preparedStatement.setString(2, arrivalStation);
            preparedStatement.setObject(3, parsedDepartureDate);
            preparedStatement.setObject(4, parsedReturnDate);
            preparedStatement.setString(5, numTickets);
            preparedStatement.setString(6, travelClass);

            System.out.println(preparedStatement.toString());
    
            // Execute the query
            resultSet = preparedStatement.executeQuery();

            System.out.println(resultSet.toString());

    
            // Process the result set and populate the list of trains
            while (resultSet.next()) {
                int trainId = resultSet.getInt("TrainID");
                String trainName = resultSet.getString("TrainName");
                String resdepartureStation = resultSet.getString("departureStation");
                String resarrivalStation = resultSet.getString("arrivalStation");
                Date resdepartureDate = resultSet.getDate("departureDate");
                Date resarrivalDate = resultSet.getDate("arrivalDate");
                int resnumTickets = resultSet.getInt("numTickets");
                String restravelClass = resultSet.getString("travelClass");

                // Create a Train object and add it to the list
                Train train = new Train(trainId, trainName, resdepartureStation, resarrivalStation, resdepartureDate, resarrivalDate, resnumTickets, restravelClass);
                trains.add(train);
            }
        } catch (SQLException | UnsupportedEncodingException e) {
            System.out.println("Error during getTrains: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the resources
            close();
        }
    
        return trains;
    }

    public boolean book(String userId, String trainId, String travelClass, String ticketType, String nbTicket) {
        try {
            // Retrieve train information
            preparedStatement = connect.prepareStatement(
                    "SELECT TrainName, DepartureStation, ArrivalStation, " +
                            "departureDate, arrivalDate " +
                            "FROM Train WHERE TrainID = ? AND TravelClass = ?"
            );
            preparedStatement.setString(1, trainId);
            preparedStatement.setString(2, travelClass);
            resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                String trainName = resultSet.getString("TrainName");
                String departureStation = resultSet.getString("DepartureStation");
                String arrivalStation = resultSet.getString("ArrivalStation");
                LocalDate departureDate = resultSet.getDate("departureDate").toLocalDate();
                LocalDate arrivalDate = resultSet.getDate("arrivalDate").toLocalDate();
    
                // Retrieve DepartureStopName and ArrivalStopName from the Station table
                String departureStopName = getStopName(departureStation);
                String arrivalStopName = getStopName(arrivalStation);
    
                // Insert the reservation details into the Reservation table with the associated user
                preparedStatement = connect.prepareStatement(
                    "INSERT INTO Reservation (UserID, TrainID, TrainName, " +
                    "DepartureStopID, DepartureStopName, ArrivalStopID, ArrivalStopName, " +
                    "DepartureTime, ArrivalTime) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
                );
                
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, trainId);
                preparedStatement.setString(3, trainName);
                preparedStatement.setString(4, departureStation);
                preparedStatement.setString(5, departureStopName);
                preparedStatement.setString(6, arrivalStation);
                preparedStatement.setString(7, arrivalStopName);
                preparedStatement.setObject(8, Timestamp.valueOf(departureDate.atStartOfDay()));
                preparedStatement.setObject(9, Timestamp.valueOf(arrivalDate.atStartOfDay()));        
    
                // Execute the insert query
                preparedStatement.executeUpdate();

                //update nbtickets
                preparedStatement = connect.prepareStatement(
                    "UPDATE Train SET NumTickets = NumTickets - ? WHERE TrainID = ?"
                );
                preparedStatement.setString(1, nbTicket);
                preparedStatement.setString(2, trainId);
                preparedStatement.executeUpdate();

                return true; // Booking successful
            }
        } catch (SQLException e) {
            System.out.println("Error during booking: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the resources
            close();
        }
    
        return false; // Booking failed
    }

    public List<Reservation> getReservations(String userId) {
        List<Reservation> reservations = new ArrayList<>();

        try {
            // Retrieve reservation information for the specified user
            preparedStatement = connect.prepareStatement(
                "SELECT * FROM Reservation WHERE userID = ?"
            );
            preparedStatement.setString(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int reservationID = resultSet.getInt("ReservationID");
                String userID = userId;
                String trainID = resultSet.getString("TrainID");
                String trainName = resultSet.getString("TrainName");
                String departureStopID = resultSet.getString("DepartureStopID");
                String departureStopName = resultSet.getString("DepartureStopName");
                String arrivalStopID = resultSet.getString("ArrivalStopID");
                String arrivalStopName = resultSet.getString("ArrivalStopName");
                Date departureTime = resultSet.getDate("DepartureTime");
                Date arrivalTime = resultSet.getDate("ArrivalTime");

                Reservation reservation = new Reservation(reservationID, userID, trainID, trainName, departureStopID, departureStopName, arrivalStopID, arrivalStopName, departureTime, arrivalTime);
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            System.out.println("Error during reservation retrieval: " + e.getMessage());
            e.printStackTrace();
        } finally {
            close();
        }

        return reservations;
    }
    
    private String getStopName(String stationID) throws SQLException {
        preparedStatement = connect.prepareStatement(
                "SELECT StationName FROM Station WHERE StationID = ?"
        );
        preparedStatement.setString(1, stationID);
        resultSet = preparedStatement.executeQuery();
    
        if (resultSet.next()) {
            return resultSet.getString("StationName");
        }
    
        return null; // Handle this case based on your application's logic
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