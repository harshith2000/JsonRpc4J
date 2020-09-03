package eliasstar.jsonrpc.gson;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import eliasstar.jsonrpc.objects.parameter.ArrayParameter;
import eliasstar.jsonrpc.objects.parameter.ObjectParameter;
import eliasstar.jsonrpc.objects.parameter.Parameter;

/**
 * Gson {@link TypeAdapter} for classes implementing {@link Parameter}.
 *
 * @author Elias*
 * @version 0.1.0
 * @since 0.1.0
 */
final class ParameterTypeAdapter extends TypeAdapter<Parameter<?>> {

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
    * @param value The {@link Parameter} which is serialized
    * @throws IOException If serialization fails
    */
    @Override
    public void write(JsonWriter out, Parameter<?> value) throws IOException {
        value.write(out);
    }

    /**
    * Reads one JSON value (an array, object, string, number, boolean or null) and
    * converts it to a {@link Parameter}.
    *
    * @param in The {@link JsonReader} used as input
    * @return A {@link Parameter} containing the read value
    * @throws IOException If deserialization fails
    */
    @Override
    public Parameter<?> read(JsonReader in) throws IOException {
        switch (in.peek()) {
        case BEGIN_ARRAY:
            return new ArrayParameter(Streams.parse(in).getAsJsonArray());

        case BEGIN_OBJECT:
            return new ObjectParameter(Streams.parse(in).getAsJsonObject());

        default:
            throw new RuntimeException("Expected array or object, not " + in.peek());
        }
    }

}