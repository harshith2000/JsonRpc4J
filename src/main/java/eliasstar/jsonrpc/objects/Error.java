package eliasstar.jsonrpc.objects;

import java.util.Optional;

import com.google.gson.JsonElement;

/**
 * Represents a JSON-RPC error contained in a {@link Response}.
 *
 * @param code Indicates the error type that occurred
 * @param message A short description of the error
 * @param data Additional information about the error
 *
 * @author Elias*
 * @version 0.1.0
 * @since 0.1.0
 * @see <a href="https://www.jsonrpc.org/specification#error_object">JSON-RPC Specification</a>
 */
public record Error(long code, String message, Optional<JsonElement> data) {

    /** Used indirectly by GSON */
    @SuppressWarnings("unused")
    private Error() {
        this(0, "", Optional.empty());
    }

    /**
    * The returned {@link String} is equal to the value of:
    * {@code "Error@" + Integer.toHexString(hashCode()) + " " + contentAsJsonString()}
    * where {@code contentAsJsonString()} returns a JSON-like {@link String} of this
    * {@link Error}.
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