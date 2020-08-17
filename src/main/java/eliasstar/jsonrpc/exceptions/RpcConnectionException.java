package eliasstar.jsonrpc.exceptions;

public final class RpcConnectionException extends RpcException {

    private static final long serialVersionUID = -696027296937701613L;

    public RpcConnectionException(String message) {
        super(message);
    }

    public RpcConnectionException(Throwable cause) {
        super(cause);
    }

    public RpcConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

}