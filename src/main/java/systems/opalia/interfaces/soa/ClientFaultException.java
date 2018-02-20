package systems.opalia.interfaces.soa;


public class ClientFaultException
        extends Exception {

    public ClientFaultException(String message) {

        super(message);
    }

    public ClientFaultException(String message, Throwable cause) {

        super(message, cause);
    }

    public ClientFaultException(Throwable cause) {

        super(cause);
    }
}
