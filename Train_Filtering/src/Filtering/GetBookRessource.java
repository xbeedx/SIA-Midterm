package Filtering;

import java.time.LocalDate;
import java.util.List;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import db.MySQLAccess;
import objects.Reservation;
 
public class GetBookRessource extends ServerResource {  	
	@Get  
	public String toString() {
        String userId = getAttribute("userId");

        MySQLAccess dao = new MySQLAccess();
        List<Reservation> reservations = dao.getReservations(userId);

		// Process the list of trains or return a response based on your requirements
		// String result = convertReservationsToString(reservations);
		String result = convertReservationsToJsonString(reservations);

		// Return the result as a String
		return result;
	}

	// Method to convert reservations to a string
	private String convertReservationsToString(List<Reservation> reservations) {
		// Check if the list is empty
		if (reservations.isEmpty()) {
			return "No reservations found.";
		}

		// Build a formatted string with reservation details
		StringBuilder resultBuilder = new StringBuilder("Filtered Reservations:\n");
		for (Reservation reservation : reservations) {
			resultBuilder.append("Reservation ID: ").append(reservation.getReservationID())
					.append(", Train ID: ").append(reservation.getTrainID())
					.append(", Train Name: ").append(reservation.getTrainName())
					.append(", Departure Stop: ").append(reservation.getDepartureStopName())
					.append(", Arrival Stop: ").append(reservation.getArrivalStopName())
					.append(", Departure Time: ").append(reservation.getDepartureTime())
					.append(", Arrival Time: ").append(reservation.getArrivalTime())
					.append("\n");
		}

		return resultBuilder.toString();
	}

	// Method to convert reservations to a JSON-like string
	private String convertReservationsToJsonString(List<Reservation> reservations) {
		// Check if the list is empty
		if (reservations.isEmpty()) {
			return "{\"message\": \"No reservations found.\"}";
		}

		// Build a JSON-like string with reservation details
		StringBuilder resultBuilder = new StringBuilder("{\"reservations\": [");
		for (int i = 0; i < reservations.size(); i++) {
			Reservation reservation = reservations.get(i);

			resultBuilder.append("{")
					.append("\"reservationID\":").append(reservation.getReservationID()).append(",")
					.append("\"trainID\":\"").append(reservation.getTrainID()).append("\",")
					.append("\"trainName\":\"").append(reservation.getTrainName()).append("\",")
					.append("\"departureStop\":\"").append(reservation.getDepartureStopName()).append("\",")
					.append("\"arrivalStop\":\"").append(reservation.getArrivalStopName()).append("\",")
					.append("\"departureTime\":\"").append(reservation.getDepartureTime()).append("\",")
					.append("\"arrivalTime\":\"").append(reservation.getArrivalTime()).append("\"")
					.append("}");

			// Add a comma if it's not the last reservation
			if (i < reservations.size() - 1) {
				resultBuilder.append(",");
			}
		}
		resultBuilder.append("]}");

		return resultBuilder.toString();
	}
}  
