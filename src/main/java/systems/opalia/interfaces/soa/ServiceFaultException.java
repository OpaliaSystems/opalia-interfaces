package systems.opalia.interfaces.soa;


public class ServiceFaultException
        extends Exception {

    public ServiceFaultException(String message) {

        super(message);
    }

    public ServiceFaultException(String message, Throwable cause) {

        super(message, cause);
    }

    public ServiceFaultException(Throwable cause) {

        super(cause);
    }
}
