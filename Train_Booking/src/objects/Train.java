package objects;

import java.util.Date;

public class Train {
    private int trainID;
    private String trainName;
    private String departureStation;
    private String arrivalStation;
    private Date outboundDepartureTime;
    private Date outboundArrivalTime;
    private Date returnDepartureTime;
    private Date returnArrivalTime;
    private int numTickets;
    private String travelClass;

    public Train(int trainID, String trainName, String departureStation, String arrivalStation,
                 Date outboundDepartureTime, Date outboundArrivalTime, Date returnDepartureTime,
                 Date returnArrivalTime, int numTickets, String travelClass) {
        this.trainID = trainID;
        this.trainName = trainName;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.outboundDepartureTime = outboundDepartureTime;
        this.outboundArrivalTime = outboundArrivalTime;
        this.returnDepartureTime = returnDepartureTime;
        this.returnArrivalTime = returnArrivalTime;
        this.numTickets = numTickets;
        this.travelClass = travelClass;
    }

    public int getTrainID() {
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

    public Date getOutboundDepartureTime() {
        return outboundDepartureTime;
    }

    public void setOutboundDepartureTime(Date outboundDepartureTime) {
        this.outboundDepartureTime = outboundDepartureTime;
    }

    public Date getOutboundArrivalTime() {
        return outboundArrivalTime;
    }

    public void setOutboundArrivalTime(Date outboundArrivalTime) {
        this.outboundArrivalTime = outboundArrivalTime;
    }

    public Date getReturnDepartureTime() {
        return returnDepartureTime;
    }

    public void setReturnDepartureTime(Date returnDepartureTime) {
        this.returnDepartureTime = returnDepartureTime;
    }

    public Date getReturnArrivalTime() {
        return returnArrivalTime;
    }

    public void setReturnArrivalTime(Date returnArrivalTime) {
        this.returnArrivalTime = returnArrivalTime;
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
}
