package tps.booking.ws;

import db.MySQLAccess;

public class BookingWS {
	
    //MySQLAccess dao = new MySQLAccess();
    //dao.readDataBase();
	
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
