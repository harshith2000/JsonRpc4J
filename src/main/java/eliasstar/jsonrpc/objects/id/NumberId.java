package eliasstar.jsonrpc.objects.id;

import java.io.IOException;

import com.google.gson.stream.JsonWriter;

public final class NumberId implements Id<Number> {

    private final Number id;

    public NumberId(Number id) {
        this.id = id;
    }

    @Override
    public Number get() {
        return id;
    }

    @Override
    public void write(JsonWriter out) throws IOException {
        out.value(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NumberId other)
            return this == other || id.equals(other.id);

        return false;
    }

}