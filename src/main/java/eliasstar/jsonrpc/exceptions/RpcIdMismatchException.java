package eliasstar.jsonrpc.exceptions;

public final class RpcIdMismatchException extends RpcException {

    private static final long serialVersionUID = -8202555481659349004L;

    private final String reqId;
    private final String resId;

    public RpcIdMismatchException(String reqId, String resId) {
        super(String.format("Request id (%s) does not match response id (%s)", reqId, resId));

        this.reqId = reqId;
        this.resId = resId;
    }

    public RpcIdMismatchException(String reqId, String resId, Throwable cause) {
        super(String.format("Request id (%s) does not match response id (%s)", reqId, resId), cause);

        this.reqId = reqId;
        this.resId = resId;
    }

    public String getRequestId() {
        return reqId;
    }

    public String getResponseId() {
        return resId;
    }
}