package tps.booking.ws;

import java.io.IOException;
import java.rmi.RemoteException;

import org.restlet.data.Form;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import tps.booking.ws.BookingWSStub.SayHello;
 
public class Client {
 
	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		
		
		// Create the client resource  
		ClientResource resourcePost = new ClientResource("http://localhost:8182/trains"); 
		ClientResource resourceGet = new ClientResource("http://localhost:8182/trains");  
		
		BookingWSStub hwp = new BookingWSStub();
		SayHello s = new SayHello();
		s.setInput("from client");
 
		Form form = new Form();  
		//form.add("uid", "1234"); 
 
		// Write the response entity on the console
		try {
			System.out.println("<------------------REST------------------>");
			System.out.println("Get");
			String responseGet = resourceGet.get().getText();
			System.out.println(responseGet);
			
			System.out.println("Post");
			String responsePost =resourcePost.post(form).getText();
			System.out.println(responsePost+"\n");
			
			System.out.println("<------------------SOAP------------------>");
			String responseStub = hwp.sayHello(s).get_return();
			System.out.print(responseStub);
 
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}