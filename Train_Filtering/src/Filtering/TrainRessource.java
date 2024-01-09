package Filtering;

import java.util.List;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import db.MySQLAccess;
import objects.Train;
 
public class TrainRessource extends ServerResource {  	
	@Get  
	public String toString() {

        String departureStation = getAttribute("departureStation");
		String arrivalStation = getAttribute("arrivalStation");
		String departureDate = getAttribute("departureDate");
		String returnDate = getAttribute("returnDate");
		String numTickets = getAttribute("numTickets");
		String travelClass = getAttribute("travelClass");

        MySQLAccess dao = new MySQLAccess();
		List<Train> trains = dao.getTrains(departureStation, arrivalStation, departureDate, returnDate, numTickets, travelClass);
		
		// Process the list of trains or return a response based on your requirements
		String result = processTrains(trains);
		
		// Return the result as a String
		return result;
	}  

    private String processTrains(List<Train> trains) {
		// Implement your logic to process trains
		// For example, you can build a String representation of the result
		StringBuilder resultBuilder = new StringBuilder("Filtered Trains:\n");
		for (Train train : trains) {
			resultBuilder.append(train.toString()).append("\n");
		}
		return resultBuilder.toString();
	}

}  
