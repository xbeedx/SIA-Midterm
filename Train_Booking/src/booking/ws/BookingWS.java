package booking.ws;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import db.MySQLAccess;
import objects.Station;
import objects.Train;

public class BookingWS {

	public boolean authenticateUser(String username, String password) {
        // Implémenter la logique d'authentification
        // Retourner true si l'authentification réussit, sinon false
		return true;
    }

	public List<Station> getStations() throws Exception {
		MySQLAccess dao = new MySQLAccess();
		return dao.getStations();
    }

	public Station getStation(String stationId) throws Exception {
		MySQLAccess dao = new MySQLAccess();
		return dao.getStation(stationId);
    }

	public List<Train> searchTrains(String departureStation, String arrivalStation, Date departureDate, Date returnDate, int numTickets, String travelClass) {
        // Implémenter la logique de recherche de trains
        // Exemple simplifié : retourner une liste statique de trains
        List<Train> availableTrains = new ArrayList<>();
        // Ajouter d'autres trains
        return availableTrains;
    }

	public boolean reserveSeat(List<String> trainIds, String travelClass) {
        // Implémenter la logique de réservation de sièges
        // Exemple simplifié : toujours retourner true pour cet exemple
        return true;
    }
	
	public String sayTchouTchou() {
		ClientResource resource = new ClientResource("http://localhost:8182/trains");
		try {
			return resource.get().getText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return e.toString();
		}  

	}
}
