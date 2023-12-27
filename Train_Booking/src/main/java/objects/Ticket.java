package objects;

public class Ticket {
	 private String ticketID;
	 private String userID;
	 private String travelClass;
	 private String rideId;
	 private String seat;
	 private float price;
	 
	public Ticket(String ticketID, String userID, String travelClass, String rideId, String seat, float price) {
		super();
		this.ticketID = ticketID;
		this.userID = userID;
		this.travelClass = travelClass;
		this.rideId = rideId;
		this.seat = seat;
		this.price = price;
	}

	public String getTicketID() {
		return ticketID;
	}

	public void setTicketID(String ticketID) {
		this.ticketID = ticketID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getTravelClass() {
		return travelClass;
	}

	public void setTravelClass(String travelClass) {
		this.travelClass = travelClass;
	}

	public String getRideId() {
		return rideId;
	}

	public void setRideId(String rideId) {
		this.rideId = rideId;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	 
	 
}
