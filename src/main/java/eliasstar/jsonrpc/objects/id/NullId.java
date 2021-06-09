/*
 * Copyright (C) 2020-2021 Elias*
 *
 * This file is part of JsonRpc4J.
 *
 * JsonRpc4J is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or any later version.
 *
 * JsonRpc4J is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JsonRpc4J. If not, see <https://www.gnu.org/licenses/>.
 */

package eliasstar.jsonrpc.objects.id;

import java.io.IOException;

import com.google.gson.stream.JsonWriter;

/**
 * Represents an id with a value of {@code null}.
 *
 * @author Elias*
 * @since 0.1.0
 * @see <a href="https://www.jsonrpc.org/specification">JSON-RPC
 *      Specification</a>
 */
public final class NullId implements Id<Object> {

    /** The singleton instance. */
    private static NullId instance;

    /** Used once for singleton. */
    private NullId() {}

    /**
     * Getter for singleton instance.
     * <p>
     * The instance is lazyly instanciated, which means the the singleton instance
     * is created when this method is invoked for the first time. Any consecutive
     * calls return the instance.
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

    /**
     * The hash code is always zero because there is only one instance of
     * {@link NullId}.
     *
     * @return Always {@code 0}
     */
    @Override
    public int hashCode() {
        return 0;
    }

    /**
     * Every {@link NullId} is equal.
     *
     * @return {@code true} if the argument is an instance of {@link NullId}
     */
    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof NullId;
    }

    /**
     * Gets the literal string {@code "null"}.
     *
     * @return {@code "null"}
     */
    @Override
    public String toString() {
        return "null";
    }

}