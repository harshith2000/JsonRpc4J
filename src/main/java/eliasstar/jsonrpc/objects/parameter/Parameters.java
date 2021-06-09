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
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JsonRpc4J. If not, see <https://www.gnu.org/licenses/>.
 */

package eliasstar.jsonrpc.objects.parameter;

import java.io.IOException;
import java.util.function.Supplier;

import com.google.gson.stream.JsonWriter;

/**
 * Represents the params in a JSON-RPC request.
 * <p>
 * Its two implementations {@link ArrayParameters} and {@link ObjectParameters}
 * cover the possible JSON types of Object and Array.
 *
 * @param <T> Type of parameter
 * @author Elias*
 * @since 0.1.0
 * @see <a href="https://www.jsonrpc.org/specification#request_object">JSON-RPC
 *      Specification</a>
 */
public interface Parameters<T> extends Supplier<T> {

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null).
     *
     * @param out The {@link JsonWriter} used as output
     * @throws IOException If serialization fails
     */
    public void write(JsonWriter out) throws IOException;

}