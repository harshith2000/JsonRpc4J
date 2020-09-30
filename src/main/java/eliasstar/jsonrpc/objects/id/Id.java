package eliasstar.jsonrpc.objects.id;

import java.io.IOException;
import java.util.function.Supplier;

import com.google.gson.stream.JsonWriter;

/**
 * Represents the id in a JSON-RPC request.
 * <p>
 * Its three implementations {@link StringId}, {@link NumberId} and {@link NullId}
 * cover the possible JSON types of String, Number and null literal.
 *
 * @param <T> Type of id
 * @author Elias*
 * @version 1.0.0
 * @since 0.1.0
 * @see <a href="https://www.jsonrpc.org/specification">JSON-RPC Specification</a>
 */
public interface Id<T> extends Supplier<T> {

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null).
     *
     * @param out The {@link JsonWriter} used as output
     * @throws IOException If serialization fails
     */
    public void write(JsonWriter out) throws IOException;

}