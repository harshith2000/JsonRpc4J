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

package eliasstar.jsonrpc.objects;

import java.util.Objects;
import java.util.Optional;

import com.google.gson.JsonElement;

/**
 * Represents a JSON-RPC error contained in a {@link Response}.
 *
 * @author Elias*
 * @since 0.1.0
 * @see <a href="https://www.jsonrpc.org/specification#error_object">JSON-RPC
 *      Specification</a>
 */
public final class Error {

    /** Indicates the error type that occurred. */
    private final long code;

    /** A short description of the error. */
    private final String message;

    /** Additional information about the error. */
    private final Optional<JsonElement> data;

    /** Used indirectly by GSON. */
    private Error() {
        this.code = 0;
        this.message = "";
        this.data = Optional.empty();
    }

    /**
     * Getter for error code field.
     *
     * @return The error code
     */
    public long code() {
        return code;
    }

    /**
     * Getter for error message field.
     *
     * @return The message
     */
    public String message() {
        return message;
    }

    /**
     * Getter for error data field.
     *
     * @return The additional data
     */
    public Optional<JsonElement> data() {
        return data;
    }

    /**
     * This method is implemented using {@code Objects.hash()}.
     * <p>
     * {@inheritDoc}
     *
     * @return The hash code for this {@link Error}
     */
    @Override
    public int hashCode() {
        return Objects.hash(code, message, data);
    }

    /**
     * This method returns {@code true} if the argument is a {@link Error} and all
     * properties are equal, otherwise returns {@code false}.
     * <p>
     * {@inheritDoc}
     *
     * @param obj The object to be checked
     * @return Whether {@code this} is the same as the {@link Object} argument
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Error) {
            var other = (Error) obj;

            return this == other || code == other.code && message.equals(other.message) && data.equals(other.data);
        }

        return false;
    }

    /**
     * The returned {@link String} is equal to the value of:
     * {@code "Error@" + Integer.toHexString(hashCode()) + " " + contentAsJsonString()}
     * where {@code contentAsJsonString()} returns a JSON-like {@link String} of
     * this {@link Error}.
     *
     * @return A {@link String} representation of this {@link Error}
     */
    @Override
    public String toString() {
        return "Error@" + Integer.toHexString(hashCode()) + " " + contentAsJsonString();
    }

    /**
     * The returned {@link String} represents the contents of this {@link Error}.
     *
     * @return A JSON-like {@link String} of this {@link Error}
     */
    protected String contentAsJsonString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        sb.append("\"code\": \"" + code + "\"");
        sb.append(", \"message\": " + message);
        data.ifPresent(d -> sb.append(", \"data\": " + d.toString()));
        sb.append("}");

        return sb.toString();
    }
}