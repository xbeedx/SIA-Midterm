package objects;

public class Station {

	private String stationID;
	private String station_Name;
	private String city;
	private String department;
	
	public Station(String stationID, String station_Name, String city, String department) {
		super();
		this.stationID = stationID;
		this.station_Name = station_Name;
		this.city = city;
		this.department = department;
	}

	public String getStationID() {
		return stationID;
	}

	public void setStationID(String stationID) {
		this.stationID = stationID;
	}

	public String getStation_Name() {
		return station_Name;
	}

	public void setStation_Name(String station_Name) {
		this.station_Name = station_Name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
