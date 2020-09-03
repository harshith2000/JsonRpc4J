package eliasstar.jsonrpc.objects;

import java.util.Optional;

import com.google.gson.JsonElement;

import eliasstar.jsonrpc.objects.id.Id;

/**
 * Represents a JSON-RPC response.
 *
 * @author Elias*
 * @version 0.1.0
 * @since 0.1.0
 * @see <a href="https://www.jsonrpc.org/specification#response_object">JSON-RPC Specification</a>
 */
public record Response(String jsonrpc, Id<?> id, Optional<JsonElement> result, Optional<Error> error) {

    /** Used indirectly by GSON */
    @SuppressWarnings("unused")
    private Response() {
        this("2.0", null, Optional.empty(), Optional.empty());
    }

    /**
     * Whether the request that generated this {@link Response} was successful.
     *
     * @return {@code true} if {@link #result} field is present else {@code false}
     */
    public boolean isSuccessful() {
        return result.isPresent();
    }

    /**
    * Whether this {@link Response} contains an error.
    *
    * @return {@code true} if {@link #error} field is present else {@code false}
    */
    public boolean isUnsuccessful() {
        return error.isPresent();
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