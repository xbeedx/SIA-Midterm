package objects;

public class Station {

    private String stationID;
    private String stationName;
    private String city;
    private String zipCode;
    private float lat;
    private float lon;

    public Station(String stationID, String stationName, String city, String zipCode, float lat, float lon) {
        this.stationID = stationID;
        this.stationName = stationName;
        this.city = city;
        this.zipCode = zipCode;
        this.lat = lat;
        this.lon = lon;
    }

    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }
}
