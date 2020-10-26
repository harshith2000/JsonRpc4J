package eliasstar.jsonrpc.objects.id;

import java.io.IOException;
import java.util.Objects;

import com.google.gson.stream.JsonWriter;

/**
 * Represents an id with a type of {@link String}.
 *
 * @author Elias*
 * @since 0.1.0
 * @see <a href="https://www.jsonrpc.org/specification">JSON-RPC
 *      Specification</a>
 */
public final class StringId implements Id<String> {

    /** The actual id */
    private final String id;

    /**
     * Creates an {@link StringId}.
     *
     * @param id The id to be used
     */
    public StringId(String id) {
        this.id = Objects.requireNonNull(id);
    }

    /**
     * Getter for the actual id.
     *
     * @return The actual id
     */
    @Override
    public String get() {
        return id;
    }

    /**
     * Writes a {@link String} to out.
     *
     * @param out The {@link JsonWriter} used as output
     * @throws IOException If serialization fails
     */
    @Override
    public void write(JsonWriter out) throws IOException {
        out.value(id);
    }

    /**
     * Returns the hash code for this {@link StringId}.
     *
     * @return The hash code of the underlying id
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * Two {@link StringId} objects are equal if their underlying id is equal.
     *
     * @param obj The object to be checked
     * @return {@code true} if the object is equal as described above
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof StringId) {
            var other = (StringId) obj;

            return this == other || id.equals(other.id);
        }

        return false;
    }

    /**
     * Returns the string representation for this {@link StringId}.
     *
     * @return The underlying id
     */
    @Override
    public String toString() {
        return '"' + id + '"';
    }

}