package cors;

import java.util.HashMap;
import java.util.Map;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.receivers.AbstractMessageReceiver;

public class CORSMessageReceiver extends AbstractMessageReceiver {

    @Override
    protected void invokeBusinessLogic(MessageContext messageContext) throws AxisFault {
        
    }

    private Map<String, String> getCustomHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Access-Control-Allow-Origin", "*");
        headers.put("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        headers.put("Access-Control-Allow-Headers", "Content-Type, Accept");
        return headers;
    }
}