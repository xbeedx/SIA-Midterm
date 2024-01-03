package booking;

import java.util.List;

import db.MySQLAccess;
import objects.Train;

public class BookingWS {
	
    //MySQLAccess dao = new MySQLAccess();
    //dao.readDataBase();
	
	public void callTrainFilteringService(String departureStation, String arrivalStation,
            String outboundDateTime, String returnDateTime,
            int numTickets, String travelClass) {
		
	}
	
	public String sayHello(String input){
		return "Hello "+input;
	}	
	
	public String sayBye(String input){
		return "Bye "+input;
	}	
	
	public static double inch_to_cm (double inch){
		return 2.54*inch;
	}
 
	public static double cm_to_inch (double cm){
		return 0.3937*cm;
	}

}
