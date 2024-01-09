package db;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Date;

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
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SIA?useSSL=false&serverTimezone=UTC&user=root&password=");

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