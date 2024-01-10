package Filtering;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import db.MySQLAccess;
 
public class BookRessource extends ServerResource {

    @Post
    public Representation reserve(Representation entity) throws Exception {
        Representation result = new StringRepresentation("False", MediaType.TEXT_PLAIN);
        Form form = new Form(entity);
        String userId = form.getFirstValue("userId");
        String trainId = form.getFirstValue("trainId");
        String travelClass = form.getFirstValue("travelClass");
        String ticketType = form.getFirstValue("ticketType");
        String nbTickets = form.getFirstValue("nbTickets");


        MySQLAccess dao = new MySQLAccess();
        if(dao.book(userId,trainId,travelClass,ticketType,nbTickets))
            result = new StringRepresentation("True", MediaType.TEXT_PLAIN);

        return result;
    }
}