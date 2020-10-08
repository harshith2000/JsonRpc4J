package eliasstar.jsonrpc.objects.id;

import java.io.IOException;

import com.google.gson.stream.JsonWriter;

/**
 * Represents an id with a value of {@code null}.
 *
 * @author Elias*
 * @version 1.2.0
 * @since 0.1.0
 * @see <a href="https://www.jsonrpc.org/specification">JSON-RPC Specification</a>
 */
public final class NullId implements Id<Object> {

    /** The singleton instance. */
    private static NullId instance;

    private NullId() {
    }

    /**
     * Getter for singleton instance.
     * <p>
     * The instance is lazyly instanciated, which means the the singleton instance
     * is created when this method is invoked for the first time. Any consecutive calls
     * return the instance.
     *
     * @return The only instance of {@link NullId}
     */
    public static NullId instance() {
        if (instance == null)
            instance = new NullId();

        return instance;
    }

    /**
     * Getter for {@code null}.
     *
     * @return {@code null}
     */
    @Override
    public Object get() {
        return null;
    }

    /**
     * Writes a null literal to out.
     *
     * @param out The {@link JsonWriter} used as output
     * @throws IOException If serialization fails
     */
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