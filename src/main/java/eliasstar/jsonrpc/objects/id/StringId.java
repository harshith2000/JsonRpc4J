package eliasstar.jsonrpc.objects.id;

import java.io.IOException;
import java.util.Objects;

import com.google.gson.stream.JsonWriter;

public final class StringId implements Id<String> {

    private final String id;

    public StringId(String id) {
        this.id = Objects.requireNonNull(id);
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
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof StringId other)
            return this == other || id.equals(other.id);

        return false;
    }

    @Override
    public String toString() {
        return id;
    }

}