package Filtering;

import java.util.Arrays;
import java.util.HashSet;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.engine.application.CorsFilter;
import org.restlet.routing.Router;
import org.restlet.service.CorsService;
 
public class RouterApplication extends Application{
	/**
	 * Creates a root Restlet that will receive all incoming calls.
	 */
	@Override
	public synchronized Restlet createInboundRoot() {
		// Create a router Restlet that routes each call to a new respective instance of resource.
		Router router = new Router(getContext());

		CorsFilter corsFilter = new CorsFilter(getContext());
		corsFilter.setNext(router);
		CorsService cs = new CorsService();
		cs.setAllowedOrigins(new HashSet(Arrays.asList("*")));
		cs.setAllowedCredentials(true);
		cs.setAllowedHeaders(new HashSet(Arrays.asList("Content-Type", "X-Requested-With", "Accept", "Origin")));
		corsFilter.setNext(router);
		getServices().add(cs);
		

		// Defines only two routes
		router.attach("/trains/{departureStation}/{arrivalStation}/{departureDate}/{returnDate}/{numTickets}/{travelClass}", TrainRessource.class);
		router.attach("/book/{userId}/{trainId}/{travelClass}/{ticketType}", BookRessource.class);

		return router;
	}
}