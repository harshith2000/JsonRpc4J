package eliasstar.jsonrpc.objects;

import java.util.Objects;
import java.util.Optional;

import com.google.gson.JsonElement;

import eliasstar.jsonrpc.objects.id.Id;

/**
 * Represents a JSON-RPC response.
 *
 * @author Elias*
 * @version 1.2.0
 * @since 0.1.0
 * @see <a href="https://www.jsonrpc.org/specification#response_object">JSON-RPC Specification</a>
 */
public final class Response {

    private final String jsonrpc = "2.0";
    private final Id<?> id;
    private final Optional<JsonElement> result;
    private final Optional<Error> error;

    /** Used indirectly by GSON */
    private Response() {
        this.id = null;
        this.result = Optional.empty();
        this.error = Optional.empty();
    }

    public String jsonrpc() {
        return jsonrpc;
    }

    public Id<?> id() {
        return id;
    }

    public Optional<JsonElement> result() {
        return result;
    }

    public Optional<Error> error() {
        return error;
    }

    /**
     * Whether the request that generated this {@link Response} was successful.
     *
     * @return {@code true} if {@link Response#result result} field is present else {@code false}
     */
    public boolean isSuccessful() {
        return result.isPresent();
    }

    /**
    * Whether this {@link Response} contains an error.
    *
    * @return {@code true} if {@link Response#error error} field is present else {@code false}
    */
    public boolean isUnsuccessful() {
        return error.isPresent();
    }

    /**
     * This method is implemented using {@code Objects.hash()}.
     * <p>
     * {@inheritDoc}
     *
     * @return The hash code for this {@link Response}
     */
    @Override
    public int hashCode() {
        return Objects.hash(jsonrpc, id, result, error);
    }

    /**
    * This method returns {@code true} if the argument is a {@link Response}
    * and all properties are equal, otherwise returns {@code false}.
    * <p>
    * {@inheritDoc}
    *
    * @return Whether {@code this} is the same as the {@link Object} argument
    */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Response) {
            var other = (Response) obj;

            return this == other || jsonrpc.equals(other.jsonrpc) && id.equals(other.id) && result.equals(other.result) && error.equals(other.error);
        }

        return false;
    }

    /**
    * The returned {@link String} is equal to the value of:
    * {@code "Response@" + Integer.toHexString(hashCode()) + " " + contentAsJsonString()}
    * where {@code contentAsJsonString()} returns a JSON-like {@link String} of this
    * {@link Response}.
    *
    * @return A {@link String} representation of this {@link Response}
    */
    @Override
    public String toString() {
        return "Response@" + Integer.toHexString(hashCode()) + " " + contentAsJsonString();
    }

    /**
    * The returned {@link String} represents the contents of this {@link Response}.
    *
    * @return A JSON-like {@link String} of this {@link Response}
    */
    protected String contentAsJsonString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        sb.append("\"jsonrpc\": \"" + jsonrpc + "\"");
        sb.append(", \"id\": " + id);
        result.ifPresent(r -> sb.append(", \"result\": " + r));
        error.ifPresent(e -> sb.append(", \"error\": " + e));
        sb.append("}");

        return sb.toString();
    }

}