/*
 * Copyright (C) 2020-2021 Elias*
 *
 * This file is part of JsonRpc4J.
 *
 * JsonRpc4J is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or any later version.
 *
 * JsonRpc4J is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JsonRpc4J. If not, see <https://www.gnu.org/licenses/>.
 */

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
     *        Request}
     * @param resId {@link Id} of the {@link eliasstar.jsonrpc.objects.Response
     *        Response}
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