package eliasstar.jsonrpc.exceptions;

/**
 * Root exception with is extended by all jsonrpc-specific exceptions.
 *
 * @author Elias*
 * @version 1.0.0
 * @since 0.1.0
 */
public class RpcException extends Exception {

    private static final long serialVersionUID = 3063415865477314837L;

    RpcException(String message) {
        super(message);
    }

    RpcException(Throwable cause) {
        super(cause);
    }

    RpcException(String message, Throwable cause) {
        super(message, cause);
    }

}