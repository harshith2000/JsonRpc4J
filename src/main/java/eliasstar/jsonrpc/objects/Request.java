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

package eliasstar.jsonrpc.objects;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import eliasstar.jsonrpc.objects.id.Id;
import eliasstar.jsonrpc.objects.id.NullId;
import eliasstar.jsonrpc.objects.id.NumberId;
import eliasstar.jsonrpc.objects.id.StringId;
import eliasstar.jsonrpc.objects.parameter.ArrayParameters;
import eliasstar.jsonrpc.objects.parameter.ObjectParameters;
import eliasstar.jsonrpc.objects.parameter.Parameters;

/**
 * Represents a JSON-RPC request.
 *
 * @author Elias*
 * @since 0.1.0
 * @see <a href="https://www.jsonrpc.org/specification#request_object">JSON-RPC
 *      Specification</a>
 */
public class Request {

    /**
     * Specifies the version of the JSON-RPC protocol.
     * <p>
     * It is always {@code "2.0"}.
     */
    private final String jsonrpc;

    /**
     * Specifies the id of this {@link Request}. If the {@code Optional} is empty
     * the {@link Request} is considered to be a {@link Notification}.
     */
    private final Optional<Id<?>> id;

    /** Containing the name of the method to be invoked. */
    private final String method;

    /** Specifies the parameters of this {@link Request}. */
    private final Optional<Parameters<?>> params;

    /** Used indirectly by GSON */
    @SuppressWarnings("unused")
    private Request() {
        this((Id<?>) null, "", (Parameters<?>) null);
    }

    /**
     * Creates a {@link Request}.
     *
     * @param id If null the {@link Request} is considered to be a
     *        {@link Notification}
     * @param method Must not be null
     * @param params If null the {@link Request} has no params
     */
    protected Request(Id<?> id, String method, Parameters<?> params) {
        this.jsonrpc = "2.0";
        this.id = Optional.ofNullable(id);
        this.method = Objects.requireNonNull(method);
        this.params = Optional.ofNullable(params);
    }

    /**
     * Creates a {@link Request} without parameters.
     * <p>
     * If id is {@code null} the request id will be serialized as the JSON
     * {@code null} value. If you want to omit the id, you should create a
     * {@link Notification}.
     *
     * @param id A id of type {@link String} or {@code null}
     * @param method The non-null method you want to invoke
     */
    public Request(String id, String method) {
        this(id != null ? new StringId(id) : NullId.instance(), method, null);
    }

    /**
     * Creates a {@link Request} with parameters in array format.
     * <p>
     * If id is {@code null} the request id will be serialized as the JSON
     * {@code null} value. If you want to omit the id you should create a
     * {@link Notification}.
     * <p>
     * If params is null a NullPointerException will be thrown. If you want to
     * create a request without parameters, you should use
     * {@link #Request(String, String)}
     *
     * @param id A id of type {@link String} or {@code null}
     * @param method The non-null method you want to invoke
     * @param params The parameters provided as {@link JsonArray}
     */
    public Request(String id, String method, JsonArray params) {
        this(id != null ? new StringId(id) : NullId.instance(), method, new ArrayParameters(params));
    }

    /**
     * Creates a {@link Request} with parameters in object format.
     * <p>
     * If id is {@code null} the request id will be serialized as the JSON
     * {@code null} value. If you want to omit the id you should create a
     * {@link Notification}.
     * <p>
     * If params is null a NullPointerException will be thrown. If you want to
     * create a request without parameters, you should use
     * {@link #Request(String, String)}
     *
     * @param id A id of type {@link String} or {@code null}
     * @param method The non-null method you want to invoke
     * @param params The parameters provided as {@link JsonObject}
     */
    public Request(String id, String method, JsonObject params) {
        this(id != null ? new StringId(id) : NullId.instance(), method, new ObjectParameters(params));
    }

    /**
     * Creates a {@link Request} without parameters.
     * <p>
     * If id is {@code null} the request id will be serialized as the JSON
     * {@code null} value. If you want to omit the id, you should create a
     * {@link Notification}.
     *
     * @param id A id of type {@link Number} or {@code null}
     * @param method The non-null method you want to invoke
     */
    public Request(Number id, String method) {
        this(id != null ? new NumberId(new BigDecimal(id.toString())) : NullId.instance(), method, null);
    }

    /**
     * Creates a {@link Request} with parameters in array format.
     * <p>
     * If id is {@code null} the request id will be serialized as the JSON
     * {@code null} value. If you want to omit the id you should create a
     * {@link Notification}.
     * <p>
     * If params is null a NullPointerException will be thrown. If you want to
     * create a request without parameters, you should use
     * {@link #Request(Number, String)}
     *
     * @param id A id of type {@link Number} or {@code null}
     * @param method The non-null method you want to invoke
     * @param params The parameters provided as {@link JsonArray}
     */
    public Request(Number id, String method, JsonArray params) {
        this(id != null ? new NumberId(new BigDecimal(id.toString())) : NullId.instance(), method, new ArrayParameters(params));
    }

    /**
     * Creates a {@link Request} with parameters in object format.
     * <p>
     * If id is {@code null} the request id will be serialized as the JSON
     * {@code null} value. If you want to omit the id you should create a
     * {@link Notification}.
     * <p>
     * If params is null a NullPointerException will be thrown. If you want to
     * create a request without parameters, you should use
     * {@link #Request(Number, String)}
     *
     * @param id A id of type {@link Number} or {@code null}
     * @param method The non-null method you want to invoke
     * @param params The parameters provided as {@link JsonObject}
     */
    public Request(Number id, String method, JsonObject params) {
        this(id != null ? new NumberId(new BigDecimal(id.toString())) : NullId.instance(), method, new ObjectParameters(params));
    }

    /**
     * Getter for jsonrpc field.
     * <p>
     * It is always {@code "2.0"}.
     *
     * @return The version of the JSON-RPC protocol
     */
    public String jsonrpc() {
        return jsonrpc;
    }

    /**
     * Getter for id field.
     * <p>
     * If the {@code Optional} is empty the {@link Request} is considered to be a
     * {@link Notification}.
     *
     * @return The id of this {@link Request}
     */
    public Optional<Id<?>> id() {
        return id;
    }

    /**
     * Getter for method field.
     *
     * @return The name of the method to be invoked
     */
    public String method() {
        return method;
    }

    /**
     * Getter for params field.
     *
     * @return The parameters of this {@link Request}
     */
    public Optional<Parameters<?>> params() {
        return params;
    }

    /**
     * This method is implemented using {@code Objects.hash()}.
     * <p>
     * {@inheritDoc}
     *
     * @return The hash code for this {@link Request}
     */
    @Override
    public int hashCode() {
        return Objects.hash(jsonrpc, id, method, params);
    }

    /**
     * This method returns {@code true} if the argument is a {@link Request} and all
     * properties are equal, otherwise returns {@code false}.
     * <p>
     * {@inheritDoc}
     *
     * @param obj The object to be checked
     * @return Whether {@code this} is the same as the {@link Object} argument
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Request) {
            var other = (Request) obj;

            return this == other || jsonrpc.equals(other.jsonrpc) && id.equals(other.id) && method.equals(other.method) && params.equals(other.params);
        }

        return false;
    }

    /**
     * The returned {@link String} is equal to the value of:
     * {@code "Request@" + Integer.toHexString(hashCode()) + " " + contentAsJsonString()}
     * where {@code contentAsJsonString()} returns a JSON-like {@link String} of
     * this {@link Request}.
     *
     * @return A {@link String} representation of this {@link Request}
     */
    @Override
    public String toString() {
        return "Request@" + Integer.toHexString(hashCode()) + " " + contentAsJsonString();
    }

    /**
     * The returned {@link String} represents the contents of this {@link Request}.
     *
     * @return A JSON-like {@link String} of this {@link Request}
     */
    protected String contentAsJsonString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        sb.append("\"jsonrpc\": \"" + jsonrpc + "\"");

        id.ifPresent(i -> sb.append(", \"id\": " + i));

        sb.append(", \"method\": \"" + method + "\"");

        params.ifPresent(p -> sb.append(", \"params\": " + p));
        sb.append("}");

        return sb.toString();
    }

}