package eliasstar.jsonrpc.objects.id;

import java.io.IOException;

import com.google.gson.stream.JsonWriter;

public final class StringId implements Id<String> {

    private final String id;

    public StringId(String id) {
        this.id = id;
    }

    @Override
    public String get() {
        return id;
    }

    @Override
    public void write(JsonWriter out) throws IOException {
        out.value(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StringId other)
            return this == other || id.equals(other.id);

        return false;
    }

}