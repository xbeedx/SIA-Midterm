package objects;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Train {
    private int trainID;
    private String trainName;
    private String departureStation;
    private String arrivalStation;
    private Date departureDate;
    private Date arrivalDate;
    private int numTickets;
    private String travelClass;

    public Train(int trainID, String trainName, String departureStation, String arrivalStation,
                 Date departureDate, Date arrivalDate, int numTickets, String travelClass) {
        this.trainID = trainID;
        this.trainName = trainName;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.numTickets = numTickets;
        this.travelClass = travelClass;
    }

    public int getTrainId() {
        return trainID;
    }

    public void setTrainID(int trainID) {
        this.trainID = trainID;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setdepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return "Train{" +
                "trainID=" + trainID +
                ", trainName='" + trainName + '\'' +
                ", departureStation='" + departureStation + '\'' +
                ", arrivalStation='" + arrivalStation + '\'' +
                ", departureDate=" + dateFormat.format(departureDate) +
                ", arrivalDate=" + dateFormat.format(arrivalDate) +
                ", numTickets=" + numTickets +
                ", travelClass='" + travelClass + '\'' +
                '}';
    }
}
