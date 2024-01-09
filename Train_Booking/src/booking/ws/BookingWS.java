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

    private static final String TRAINS_API_URL = "http://localhost:8182/trains/";
    private static final String BOOK_SEAT_API_URL = "http://localhost:8182/book/";

    public String authenticateUser(String username, String password) {
        MySQLAccess dao = new MySQLAccess();
        return dao.authenticateUser(username, password);
    }

    public String createUser(String username, String password) {
        MySQLAccess dao = new MySQLAccess();
        return dao.createUser(username, password);
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
        // String apiUrl = String.format("%sdepartureStation=%s&arrivalStation=%s&departureDate=%s&returnDate=%s&numTickets=%d&travelClass=%s",
        //         TRAINS_API_URL, departureStation, arrivalStation, departureDate, returnDate, numTickets, travelClass);

		String apiUrl = String.format("%s%s/%s/%s/%s/%d/%s",
                TRAINS_API_URL, departureStation, arrivalStation, departureDate, returnDate, numTickets, travelClass);

		ClientResource resource = new ClientResource(apiUrl);
        try {
            return resource.get().getText();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    public boolean bookSeat(String userId, String trainId, String travelClass, String ticketType) {
        String apiUrl = String.format("%strainId=%s&travelClass=%s&ticketType=%s", BOOK_SEAT_API_URL, trainId, travelClass, ticketType);

		ClientResource resource = new ClientResource(apiUrl);
        try {
            String result = resource.get().getText();
            return "true".equals(result);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
