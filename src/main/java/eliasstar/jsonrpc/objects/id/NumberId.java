package eliasstar.jsonrpc.objects.id;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

import com.google.gson.stream.JsonWriter;

/**
 * Represents an id with a type of {@link Number} concrete {@link BigDecimal}.
 *
 * @author Elias*
 * @version 0.1.0
 * @since 0.1.0
 * @see <a href="https://www.jsonrpc.org/specification">JSON-RPC Specification</a>
 */
public final class NumberId implements Id<BigDecimal> {

    /** The actual id */
    private final BigDecimal id;

    /**
     * Creates a {@link NumberId}.
     *
     * @param id The id to be used
     */
    public NumberId(BigDecimal id) {
        this.id = Objects.requireNonNull(id);
    }

    /**
     * Getter for the actual id.
     *
     * @return The actual id
     */
    @Override
    public BigDecimal get() {
        return id;
    }

    /**
     * Writes a {@link Number} to out.
     *
     * @param out The {@link JsonWriter} used as output
     * @throws IOException If serialization fails
     */
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
        if (obj != null && obj instanceof NumberId other)
            return this == other || id.equals(other.id);

        return false;
    }

    @Override
    public String toString() {
        return id.toString();
    }

}