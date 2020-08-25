package eliasstar.jsonrpc.objects.parameter;

import java.io.IOException;
import java.util.Objects;

import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;

public final class ObjectParameter implements Parameter<JsonObject> {

    private final JsonObject params;

    public ObjectParameter(JsonObject params) {
        this.params = Objects.requireNonNull(params);
    }

    @Override
    public JsonObject get() {
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
        if (obj != null && obj instanceof ObjectParameter other)
            return this == other || params.equals(other.params);

        return false;
    }

    @Override
    public String toString() {
        return params.toString();
    }

}