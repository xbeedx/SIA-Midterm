package booking.ws;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import db.MySQLAccess;
import objects.Station;
import objects.Train;

public class BookingWS {

	public String authenticateUser(String username, String password) {
		MySQLAccess dao = new MySQLAccess();
		return dao.authenticateUser(username,password);
    }

	public String createUser(String username, String password)
	{
		MySQLAccess dao = new MySQLAccess();
		return dao.createUser(username,password);
	}

	public List<Station> getStations() throws Exception {
		MySQLAccess dao = new MySQLAccess();
		return dao.getStations();
    }

	public Station getStation(String stationId) throws Exception {
		MySQLAccess dao = new MySQLAccess();
		return dao.getStation(stationId);
    }

	public String searchTrains(String departureStation, String arrivalStation, Date departureDate, Date returnDate, int numTickets, String travelClass) {

		String apiUrl = String.format("http://localhost:8182/trains/departureStation=%s&arrivalStation=%s" +
                            "&departureDate=%s&returnDate=%s&numTickets=%d&travelClass=%s",
                    departureStation, arrivalStation, departureDate, returnDate,
                    numTickets, travelClass);
       ClientResource resource = new ClientResource(apiUrl);
		try {
        	String trains = resource.get().getText();
			return trains;
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return "";
	}

	public boolean bookSeat(String userId, String trainId, String travelClass, String ticketType) {
		String apiUrl = String.format("http://localhost:8182/book/trainId=%s&travelClass=%s&ticketType=%s",
					trainId, travelClass, ticketType);
		ClientResource resource = new ClientResource(apiUrl);
		try {
			String result = resource.get().getText();
			if (result.equals("true")) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return true;
    }
}
