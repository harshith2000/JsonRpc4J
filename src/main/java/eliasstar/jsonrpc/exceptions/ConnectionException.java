package eliasstar.jsonrpc.exceptions;

/**
 * Indicates a failure while sending a {@link eliasstar.jsonrpc.objects.Request Request}.
 *
 * @author Elias*
 * @version 0.1.0
 * @since 0.1.0
 */
public final class ConnectionException extends RpcException {

    private static final long serialVersionUID = 696027296937701613L;

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(Throwable cause) {
        super(cause);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

}