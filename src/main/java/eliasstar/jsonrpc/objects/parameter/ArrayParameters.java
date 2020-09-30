package eliasstar.jsonrpc.objects.parameter;

import java.io.IOException;
import java.util.Objects;

import com.google.gson.JsonArray;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;

/**
 * Represents the params in array format.
 *
 * @author Elias*
 * @version 1.0.0
 * @since 0.1.0
 * @see <a href="https://www.jsonrpc.org/specification#request_object">JSON-RPC Specification</a>
 */
public final class ArrayParameters implements Parameters<JsonArray> {

    /** The actual parameters */
    private final JsonArray params;

    /**
     * Creates an {@link ArrayParameters}.
     *
     * @param params The parameters to be used
     */
    public ArrayParameters(JsonArray params) {
        this.params = Objects.requireNonNull(params);
    }

    /**
     * Getter for the actual parameters.
     *
     * @return The actual parameters
     */
    @Override
    public JsonArray get() {
        return params;
    }

    /**
     * Writes a {@link JsonArray} to out.
     *
     * @param out The {@link JsonWriter} used as output
     * @throws IOException If serialization fails
     */
    @Override
    public void write(JsonWriter out) throws IOException {
        Streams.write(params, out);
    }

    @Override
    public int hashCode() {
        return params.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof ArrayParameters other)
            return this == other || params.equals(other.params);

        return false;
    }

    @Override
    public String toString() {
        return params.toString();
    }

}