package objects;

public class Train {
	private String trainID;
	private String type;
	private String name;
	private int seats_capacity;
	
	public Train(String trainID, String type, String name, int seats_capacity) {
		super();
		this.trainID = trainID;
		this.type = type;
		this.name = name;
		this.seats_capacity = seats_capacity;
	}
	
	public String getTrainID() {
		return trainID;
	}
	public void setTrainID(String trainID) {
		this.trainID = trainID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSeats_capacity() {
		return seats_capacity;
	}
	public void setSeats_capacity(int seats_capacity) {
		this.seats_capacity = seats_capacity;
	}
}

