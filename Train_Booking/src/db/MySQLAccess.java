package db;

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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public String authenticateUser(String username, String password) {
        try {
            // Prepared statement using ?
            preparedStatement = connect
                    .prepareStatement("select * from SIA.User where Username = ? and Password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            // Make sure resultSet is not null
            if (resultSet == null) {
                // Handle the situation when resultSet is unexpectedly null
                throw new IllegalStateException("ResultSet is unexpectedly null");
            }

            if (resultSet.next()) {
                return resultSet.getString("UserID");
            }
            return "";
        } catch (Exception e) {
            System.out.println("Error during MySQLAccess.authenticateUser: " + e.getMessage());
            e.printStackTrace();
            return "";
        } finally {
            close();
        }
    }

    public String createUser(String username, String password) {
        try {
            String uuid = UUID.randomUUID().toString();
            preparedStatement = connect
                    .prepareStatement("insert into SIA.User (UserID, Username, Password) values (?, ?, ?)");
            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            
            return authenticateUser(username, password);
        } catch (Exception e) {
            System.out.println("Error during MySQLAccess.createUser: " + e.getMessage());
            e.printStackTrace();
            return "";
        } finally {
            close();
        }
    }
    
   
    
    public List<Station> getStations() throws Exception {
        try {
            List<Station> stations = new ArrayList<>();
            
            // Make sure statement is not null
            if (statement == null) {
                // Initialize statement here or throw an exception
                throw new IllegalStateException("Statement is not initialized");
            }

            resultSet = statement.executeQuery("select * from SIA.Station");

            // Make sure resultSet is not null
            if (resultSet == null) {
                // Handle the situation when resultSet is unexpectedly null
                throw new IllegalStateException("ResultSet is unexpectedly null");
            }

            while (resultSet.next()) {
                String stationID = resultSet.getString("StationID");
                String station_Name = resultSet.getString("StationName");
                String city = resultSet.getString("City");
                String ZipCode = resultSet.getString("ZipCode");
                float Lat = resultSet.getFloat("Lat");
                float Lon = resultSet.getFloat("Lon");

                stations.add(new Station(stationID, station_Name, city, ZipCode, Lat, Lon));
            }
            return stations;
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public Station getStation(String stationId ) throws Exception {
        try {
            List<Station> stations = new ArrayList<>();
            
            // Make sure statement is not null
            if (statement == null) {
                // Initialize statement here or throw an exception
                throw new IllegalStateException("Statement is not initialized");
            }

            //prepared statement using ?
            preparedStatement = connect
                    .prepareStatement("select * from SIA.Station where StationID = ?");
            preparedStatement.setString(1, stationId);
            resultSet = preparedStatement.executeQuery();

            // Make sure resultSet is not null
            if (resultSet == null) {
                // Handle the situation when resultSet is unexpectedly null
                throw new IllegalStateException("ResultSet is unexpectedly null");
            }
            Station station = processSingleStation(resultSet);
            return station;
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    private Station processSingleStation(ResultSet resultSet2) {
        try {
            if (resultSet2.next()) {
                String stationID = resultSet2.getString("StationID");
                String station_Name = resultSet2.getString("StationName");
                String city = resultSet2.getString("City");
                String ZipCode = resultSet2.getString("ZipCode");
                float Lat = resultSet2.getFloat("Lat");
                float Lon = resultSet2.getFloat("Lon");

                return new Station(stationID, station_Name, city, ZipCode, Lat, Lon);
            }
            return null;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
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