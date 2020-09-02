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
import eliasstar.jsonrpc.objects.parameter.ArrayParameter;
import eliasstar.jsonrpc.objects.parameter.ObjectParameter;
import eliasstar.jsonrpc.objects.parameter.Parameter;

/**
 * Represents a JSON-RPC request.
 *
 * @author Elias*
 * @version 1.0.0
 * @since 1.0.0
 * @see <a href="https://www.jsonrpc.org/specification"> JSON-RPC Specification</a>
 */
public class Request {

    /**
     * Specifies the version of the JSON-RPC protocol.
     * <p>
     * It is always {@code "2.0"}.
     */
    private final String jsonrpc;

    /**
     * Specifies the id of this {@link Request}.
     *
     * If the {@code Optional} is empty the {@link Request} is considered to be a
     * {@link Notification}.
     */
    private final Optional<Id<?>> id;

    /** Containing the name of the method to be invoked. */
    private final String method;

    /** Specifies the parameters of this {@link Request}. */
    private final Optional<Parameter<?>> params;

    @SuppressWarnings("unused") // Used indirectly by GSON
    private Request() {
        this((Id<?>) null, "", (Parameter<?>) null);
    }

    protected Request(Id<?> id, String method, Parameter<?> params) {
        this.jsonrpc = "2.0";
        this.id = Optional.ofNullable(id);
        this.method = Objects.requireNonNull(method);
        this.params = Optional.ofNullable(params);
    }

    public Request(String id, String method) {
        this(id != null ? new StringId(id) : NullId.instance(), method, null);
    }

    public Request(String id, String method, JsonArray params) {
        this(id != null ? new StringId(id) : NullId.instance(), method, new ArrayParameter(params));
    }

    public Request(String id, String method, JsonObject params) {
        this(id != null ? new StringId(id) : NullId.instance(), method, new ObjectParameter(params));
    }

    public Request(Number id, String method) {
        this(id != null ? new NumberId(new BigDecimal(id.toString())) : NullId.instance(), method, null);
    }

    public Request(Number id, String method, JsonArray params) {
        this(id != null ? new NumberId(new BigDecimal(id.toString())) : NullId.instance(), method, new ArrayParameter(params));
    }

    public Request(Number id, String method, JsonObject params) {
        this(id != null ? new NumberId(new BigDecimal(id.toString())) : NullId.instance(), method, new ObjectParameter(params));
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
    public Optional<Parameter<?>> params() {
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
    * This method returns {@code true} if the argument is a {@link Request}
    * and all properties are equal, otherwise returns {@code false}.
    * <p>
    * {@inheritDoc}
    *
    * @return Whether {@code this} is the same as the {@link Object} argument
    */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Request other)
            return this == other || jsonrpc.equals(other.jsonrpc) && id.equals(other.id) && method.equals(other.method) && params.equals(other.params);

        return false;
    }

    /**
    * This method returns a {@link String} representing this {@link Request}.
    * <p>
    * This method returns a {@link String} equal to the value of:
    * {@code "Request@" + Integer.toHexString(hashCode()) + " " + contentAsJsonString()}
    * where {@code contentAsJsonString()} returns a JSON-like {@link String} of this
    * {@link Request}.
    *
    * @return
    */
    @Override
    public String toString() {
        return "Request@" + Integer.toHexString(hashCode()) + " " + contentAsJsonString();
    }

    /**
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