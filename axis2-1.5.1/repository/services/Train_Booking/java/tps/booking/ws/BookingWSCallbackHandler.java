
/**
 * BookingWSCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package tps.booking.ws;

    /**
     *  BookingWSCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class BookingWSCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public BookingWSCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public BookingWSCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for sayHello method
            * override this method for handling normal response from sayHello operation
            */
           public void receiveResultsayHello(
                    tps.booking.ws.BookingWSStub.SayHelloResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from sayHello operation
           */
            public void receiveErrorsayHello(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cm_to_inch method
            * override this method for handling normal response from cm_to_inch operation
            */
           public void receiveResultcm_to_inch(
                    tps.booking.ws.BookingWSStub.Cm_to_inchResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cm_to_inch operation
           */
            public void receiveErrorcm_to_inch(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for sayBye method
            * override this method for handling normal response from sayBye operation
            */
           public void receiveResultsayBye(
                    tps.booking.ws.BookingWSStub.SayByeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from sayBye operation
           */
            public void receiveErrorsayBye(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for inch_to_cm method
            * override this method for handling normal response from inch_to_cm operation
            */
           public void receiveResultinch_to_cm(
                    tps.booking.ws.BookingWSStub.Inch_to_cmResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from inch_to_cm operation
           */
            public void receiveErrorinch_to_cm(java.lang.Exception e) {
            }
                


    }
    