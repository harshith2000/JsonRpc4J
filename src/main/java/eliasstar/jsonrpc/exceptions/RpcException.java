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
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JsonRpc4J. If not, see <https://www.gnu.org/licenses/>.
 */

package eliasstar.jsonrpc.exceptions;

/**
 * Root exception with is extended by all jsonrpc-specific exceptions.
 *
 * @author Elias*
 * @since 0.1.0
 */
public class RpcException extends Exception {

    /** Used for serialization. */
    private static final long serialVersionUID = 3063415865477314837L;

    /**
     * Creates a {@link RpcException}.
     *
     * @param message The error message
     */
    RpcException(String message) {
        super(message);
    }

    /**
     * Creates a {@link RpcException}.
     *
     * @param cause The {@link Exception} which caused this {@link RpcException}
     */
    RpcException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a {@link RpcException}.
     *
     * @param message The error message
     * @param cause The {@link Exception} which caused this {@link RpcException}
     */
    RpcException(String message, Throwable cause) {
        super(message, cause);
    }

}