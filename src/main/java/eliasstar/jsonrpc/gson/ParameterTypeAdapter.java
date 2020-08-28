package eliasstar.jsonrpc.gson;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import eliasstar.jsonrpc.objects.parameter.ArrayParameter;
import eliasstar.jsonrpc.objects.parameter.ObjectParameter;
import eliasstar.jsonrpc.objects.parameter.Parameter;

final class ParameterTypeAdapter extends TypeAdapter<Parameter<?>> {

    private static ParameterTypeAdapter instance;

    private ParameterTypeAdapter() {
    }

    static ParameterTypeAdapter instance() {
        if (instance == null)
            instance = new ParameterTypeAdapter();

        return instance;
    }

    @Override
    public void write(JsonWriter out, Parameter<?> value) throws IOException {
        value.write(out);
    }

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