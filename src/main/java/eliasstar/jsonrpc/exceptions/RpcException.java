package eliasstar.jsonrpc.exceptions;

public class RpcException extends Exception {

    private static final long serialVersionUID = 3063415865477314837L;

    public RpcException(String message) {
        super(message);
    }

    public RpcException(Throwable cause) {
        super(cause);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

}