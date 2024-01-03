package objects;

import java.sql.Date;

public class Ride {
	private String rideID;
	private String trainID;
	private String departureStationID;
	private String ArrivalStationID;
	private Date departure_Date;
	private Date arrival_Date;
	
	public Ride(String rideID, String trainID, String departureStation, String arrivalStation, Date departure_Date,
			Date arrival_Date) {
		super();
		this.rideID = rideID;
		this.trainID = trainID;
		this.departureStationID = departureStation;
		ArrivalStationID = arrivalStation;
		this.departure_Date = departure_Date;
		this.arrival_Date = arrival_Date;
	}

	public String getRideID() {
		return rideID;
	}

	public void setRideID(String rideID) {
		this.rideID = rideID;
	}

	public String getTrain() {
		return trainID;
	}

	public void setTrain(String train) {
		this.trainID = train;
	}

	public String getDepartureStation() {
		return departureStationID;
	}

	public void setDepartureStation(String departureStation) {
		this.departureStationID = departureStation;
	}

	public String getArrivalStation() {
		return ArrivalStationID;
	}

	public void setArrivalStation(String arrivalStation) {
		ArrivalStationID = arrivalStation;
	}

	public Date getDeparture_Date() {
		return departure_Date;
	}

	public void setDeparture_Date(Date departure_Date) {
		this.departure_Date = departure_Date;
	}

	public Date getArrival_Date() {
		return arrival_Date;
	}

	public void setArrival_Date(Date arrival_Date) {
		this.arrival_Date = arrival_Date;
	}
	
	
}
