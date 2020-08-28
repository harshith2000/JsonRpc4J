package eliasstar.jsonrpc.exceptions;

class RpcException extends Exception {

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