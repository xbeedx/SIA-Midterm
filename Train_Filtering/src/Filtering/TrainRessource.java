package Filtering;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
 
public class TrainRessource extends ServerResource {  	
	@Get  
	public String toString() {
		//String uid = (String) getRequestAttributes().get("uid");
		return "Tchou Tchou";  
	}  
	
	@Post
    public Representation acceptItem(Representation entity) {  
		Representation result = null;  
        // Parse the given representation and retrieve data
        Form form = new Form(entity);  
        
        //String uid = form.getFirstValue("uid");  
        //String uname = form.getFirstValue("uname");  
 
        //result = new StringRepresentation("User whose uid="+ uid +" is updated",  
        //MediaType.TEXT_PLAIN);

        result = new StringRepresentation("Hello World POST",  
            MediaType.TEXT_PLAIN);
 
        return result;  
	}

}  
