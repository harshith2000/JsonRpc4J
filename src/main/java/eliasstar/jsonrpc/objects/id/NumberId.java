package eliasstar.jsonrpc.objects.id;

import java.io.IOException;
import java.math.BigDecimal;

import com.google.gson.stream.JsonWriter;

public final class NumberId implements Id<BigDecimal> {

    private final BigDecimal id;

    public NumberId(BigDecimal id) {
        this.id = id;
    }

    @Override
    public BigDecimal get() {
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
        if (obj != null && obj instanceof NumberId other)
            return this == other || id.equals(other.id);

        return false;
    }

    @Override
    public String toString() {
        return id.toString();
    }

}