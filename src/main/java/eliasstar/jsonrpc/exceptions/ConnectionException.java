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

/**
 * Indicates a failure while sending a {@link eliasstar.jsonrpc.objects.Request
 * Request}.
 *
 * @author Elias*
 * @since 0.1.0
 */
public final class ConnectionException extends RpcException {

    /** Used for serialization. */
    private static final long serialVersionUID = 696027296937701613L;

    /**
     * Creates a {@link ConnectionException}.
     *
     * @param message The error message
     */
    public ConnectionException(String message) {
        super(message);
    }

    /**
     * Creates a {@link ConnectionException}.
     *
     * @param cause The {@link Exception} which caused this
     *        {@link ConnectionException}
     */
    public ConnectionException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a {@link ConnectionException}.
     *
     * @param message The error message
     * @param cause The {@link Exception} which caused this
     *        {@link ConnectionException}
     */
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

}