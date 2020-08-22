package eliasstar.jsonrpc.exceptions;

import eliasstar.jsonrpc.objects.id.Id;

public final class RpcIdMismatchException extends RpcException {

    private static final long serialVersionUID = -8202555481659349004L;

    private final Id<?> reqId;
    private final Id<?> resId;

    public RpcIdMismatchException(Id<?> reqId, Id<?> resId) {
        super(String.format("Request id (%s) does not match response id (%s)", reqId, resId));

        this.reqId = reqId;
        this.resId = resId;
    }

    public RpcIdMismatchException(Id<?> reqId, Id<?> resId, Throwable cause) {
        super(String.format("Request id (%s) does not match response id (%s)", reqId, resId), cause);

        this.reqId = reqId;
        this.resId = resId;
    }

    public Id<?> getRequestId() {
        return reqId;
    }

    public Id<?> getResponseId() {
        return resId;
    }

}