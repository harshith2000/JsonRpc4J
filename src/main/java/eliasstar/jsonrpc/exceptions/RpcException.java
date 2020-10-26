package eliasstar.jsonrpc.exceptions;

/**
 * Root exception with is extended by all jsonrpc-specific exceptions.
 *
 * @author Elias*
 * @since 0.1.0
 */
public class RpcException extends Exception {

    /** Used for serialization. */
    private static final long serialVersionUID = 3063415865477314837L;

    /**
     * Creates a {@link RpcException}.
     *
     * @param message The error message
     */
    RpcException(String message) {
        super(message);
    }

    /**
     * Creates a {@link RpcException}.
     *
     * @param cause The {@link Exception} which caused this {@link RpcException}
     */
    RpcException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a {@link RpcException}.
     *
     * @param message The error message
     * @param cause   The {@link Exception} which caused this {@link RpcException}
     */
    RpcException(String message, Throwable cause) {
        super(message, cause);
    }

}