package eliasstar.jsonrpc.exceptions;

import eliasstar.jsonrpc.objects.id.Id;

/**
 * Indicates that the {@link Id} of the
 * {@link eliasstar.jsonrpc.objects.Response Response} received does not match
 * the one sent in the {@link eliasstar.jsonrpc.objects.Request Request}.
 *
 * @author Elias*
 * @since 0.1.0
 */
public final class IdMismatchException extends RpcException {

    /** Used for serialization. */
    private static final long serialVersionUID = 8202555481659349004L;

    /** {@link Id} of the {@link eliasstar.jsonrpc.objects.Request Request} */
    private final Id<?> reqId;

    /** {@link Id} of the {@link eliasstar.jsonrpc.objects.Response Response} */
    private final Id<?> resId;

    /**
     * Creates a {@link IdMismatchException}.
     *
     * @param reqId {@link Id} of the {@link eliasstar.jsonrpc.objects.Request
     *              Request}
     * @param resId {@link Id} of the {@link eliasstar.jsonrpc.objects.Response
     *              Response}
     */
    public IdMismatchException(Id<?> reqId, Id<?> resId) {
        super(String.format("Request id (%s) does not match response id (%s)", reqId, resId));

        this.reqId = reqId;
        this.resId = resId;
    }

    /**
     * Getter for request id.
     *
     * @return {@link Id} of the {@link eliasstar.jsonrpc.objects.Request Request}
     */
    public Id<?> getRequestId() {
        return reqId;
    }

    /**
     * Getter for response id.
     *
     * @return {@link Id} of the {@link eliasstar.jsonrpc.objects.Response Response}
     */
    public Id<?> getResponseId() {
        return resId;
    }

}