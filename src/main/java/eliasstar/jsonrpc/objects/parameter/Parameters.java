package eliasstar.jsonrpc.objects.parameter;

import java.io.IOException;
import java.util.function.Supplier;

import com.google.gson.stream.JsonWriter;

/**
 * Represents the params in a JSON-RPC request.
 * <p>
 * Its two implementations {@link ArrayParameters} and {@link ObjectParameters}
 * cover the possible JSON types of Object and Array.
 *
 * @param <T> Type of parameter
 * @author Elias*
 * @version 1.2.0
 * @since 0.1.0
 * @see <a href="https://www.jsonrpc.org/specification#request_object">JSON-RPC Specification</a>
 */
public interface Parameters<T> extends Supplier<T> {

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null).
     *
     * @param out The {@link JsonWriter} used as output
     * @throws IOException If serialization fails
     */
    public void write(JsonWriter out) throws IOException;

}