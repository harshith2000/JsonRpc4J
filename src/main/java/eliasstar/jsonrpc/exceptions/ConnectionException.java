package eliasstar.jsonrpc.exceptions;

/**
 * Indicates a failure while sending a {@link eliasstar.jsonrpc.objects.Request Request}.
 *
 * @author Elias*
 * @version 1.2.0
 * @since 0.1.0
 */
public final class ConnectionException extends RpcException {

    private static final long serialVersionUID = 696027296937701613L;

    /**
    * Creates a {@link ConnectionException}.
    *
    * @param message The error message
    */
    public ConnectionException(String message) {
        super(message);
    }

    /**
    * Creates a {@link ConnectionException}.
    *
    * @param cause The {@link Exception} which caused this {@link ConnectionException}
    */
    public ConnectionException(Throwable cause) {
        super(cause);
    }

    /**
    * Creates a {@link ConnectionException}.
    *
    * @param message The error message
    * @param cause The {@link Exception} which caused this {@link ConnectionException}
    */
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

}