package eliasstar.jsonrpc.objects.id;

import java.io.IOException;

import com.google.gson.stream.JsonWriter;

public final class NullId implements Id<Object> {

    private static NullId instance;

    private NullId() {
    }

    public static NullId instance() {
        if (instance == null)
            instance = new NullId();

        return instance;
    }

    @Override
    public Object get() {
        return null;
    }

    @Override
    public void write(JsonWriter out) throws IOException {
        var serialize = out.getSerializeNulls();
        out.setSerializeNulls(true);

        out.nullValue();

        out.setSerializeNulls(serialize);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof NullId;
    }

    @Override
    public String toString() {
        return "null";
    }

}