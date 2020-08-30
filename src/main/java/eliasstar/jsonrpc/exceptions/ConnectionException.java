package eliasstar.jsonrpc.exceptions;

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