package corsII;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.handlers.AbstractHandler;

public class CorsHandler extends AbstractHandler {

    @Override
    public InvocationResponse invoke(MessageContext messageContext) throws AxisFault {
        // Set CORS headers here
        messageContext.setProperty("HTTP_HEADERS", "Access-Control-Allow-Origin: http://127.0.0.1:8081");
        messageContext.setProperty("HTTP_HEADERS", "Access-Control-Allow-Methods: POST");
        messageContext.setProperty("HTTP_HEADERS", "Access-Control-Allow-Headers: Content-Type");

        return InvocationResponse.CONTINUE;
    }
}
