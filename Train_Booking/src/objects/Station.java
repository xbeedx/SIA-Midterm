package objects;

public class Station {

	private String stationID;
	private String station_Name;
	private String city;
	private String ZipCode;
	public float Lat;	
	public float Lon;

	
	public Station(String stationID, String station_Name, String city, String ZipCode, float Lat, float Lon) {
		super();
		this.stationID = stationID;
		this.station_Name = station_Name;
		this.city = city;
		this.ZipCode = ZipCode;		
		this.Lat = Lat;
		this.Lon = Lon;

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
}
