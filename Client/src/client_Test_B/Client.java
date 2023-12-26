package client_Test_B;

import java.io.IOException;
 
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;


public class Client {

	public static void main(String[] args) {
		// Create the client resource  
		ClientResource resourcePost = new ClientResource("http://localhost:8182/trains"); 
		ClientResource resourceGet = new ClientResource("http://localhost:8182/trains");  
 
		Form form = new Form();  
		//form.add("uid", "1234"); 
 
		// Write the response entity on the console
		try {
			System.out.println("Get");
			String responseGet = resourceGet.get().getText();
			System.out.println(responseGet);
			
			System.out.println("Post");
			String responsePost =resourcePost.post(form).getText();
			System.out.println(responsePost);
 
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

	}

}
