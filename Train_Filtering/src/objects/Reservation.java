package objects;

import java.sql.Date;

public class Reservation {
    private int reservationID;
    private String userID;
    private String trainID;
    private String trainName;
    private String departureStopID;
    private String departureStopName;
    private String arrivalStopID;
    private String arrivalStopName;
    private Date departureTime;
    private Date arrivalTime;

    public Reservation(int reservationID, String userID, String trainID, String trainName,
                String departureStopID, String departureStopName, String arrivalStopID,
                String arrivalStopName, Date departureTime, Date arrivalTime) {
        this.reservationID = reservationID;
        this.userID = userID;
        this.trainID = trainID;
        this.trainName = trainName;
        this.departureStopID = departureStopID;
        this.departureStopName = departureStopName;
        this.arrivalStopID = arrivalStopID;
        this.arrivalStopName = arrivalStopName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public String getTrainID() {
        return trainID;
    }

    public void setTrainID(String trainID) {
        this.trainID = trainID;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDepartureStopID() {
        return departureStopID;
    }

    public void setDepartureStopID(String departureStopID) {
        this.departureStopID = departureStopID;
    }

    public String getDepartureStopName() {
        return departureStopName;
    }

    public void setDepartureStopName(String departureStopName) {
        this.departureStopName = departureStopName;
    }

    public String getArrivalStopID() {
        return arrivalStopID;
    }

    public void setArrivalStopID(String arrivalStopID) {
        this.arrivalStopID = arrivalStopID;
    }

    public String getArrivalStopName() {
        return arrivalStopName;
    }

    public void setArrivalStopName(String arrivalStopName) {
        this.arrivalStopName = arrivalStopName;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
