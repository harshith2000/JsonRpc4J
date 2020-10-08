package eliasstar.jsonrpc.gson;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import eliasstar.jsonrpc.objects.parameter.ArrayParameters;
import eliasstar.jsonrpc.objects.parameter.ObjectParameters;
import eliasstar.jsonrpc.objects.parameter.Parameters;

/**
 * Gson {@link TypeAdapter} for classes implementing {@link Parameters}.
 *
 * @author Elias*
 * @version 1.2.0
 * @since 0.1.0
 */
final class ParameterTypeAdapter extends TypeAdapter<Parameters<?>> {

    /** The singleton instance. */
    private static ParameterTypeAdapter instance;

    private ParameterTypeAdapter() {
    }

    /**
    * Getter for singleton instance.
    * <p>
    * The instance is lazyly instanciated, which means the the singleton instance
    * is created when this method is invoked for the first time. Any consecutive calls
    * return the instance.
    *
    * @return The only instance of {@link ParameterTypeAdapter}
    */
    static ParameterTypeAdapter instance() {
        if (instance == null)
            instance = new ParameterTypeAdapter();

        return instance;
    }

    /**
    * Writes one JSON value (an array, object, string, number, boolean or null) for value.
    *
    * @param out The {@link JsonWriter} used as output
    * @param value The {@link Parameters} which is serialized
    * @throws IOException If serialization fails
    */
    @Override
    public void write(JsonWriter out, Parameters<?> value) throws IOException {
        value.write(out);
    }

    /**
    * Reads one JSON value (an array, object, string, number, boolean or null) and
    * converts it to a {@link Parameters}.
    *
    * @param in The {@link JsonReader} used as input
    * @return A {@link Parameters} containing the read value
    * @throws IOException If deserialization fails
    */
    @Override
    public Parameters<?> read(JsonReader in) throws IOException {
        switch (in.peek()) {
        case BEGIN_ARRAY:
            return new ArrayParameters(Streams.parse(in).getAsJsonArray());

        case BEGIN_OBJECT:
            return new ObjectParameters(Streams.parse(in).getAsJsonObject());

        default:
            throw new RuntimeException("Expected array or object, not " + in.peek());
        }
    }

}