package eliasstar.jsonrpc.objects.parameter;

import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;

public final class ArrayParameter implements Parameter<JsonArray> {

    private final JsonArray params;

    public ArrayParameter(JsonArray params) {
        this.params = params;
    }

    @Override
    public JsonArray get() {
        return params;
    }

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
        if (obj != null && obj instanceof ArrayParameter other)
            return this == other || params.equals(other.params);

        return false;
    }

    @Override
    public String toString() {
        return params.toString();
    }

}