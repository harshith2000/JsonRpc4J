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

package eliasstar.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import eliasstar.gson.OptionalTypeAdapterFactory;
import eliasstar.jsonrpc.gson.RpcTypeAdapterFactory;

public final class GsonProvider {

    private static Gson gson;
    private static Gson gsonWithNulls;

    public static Gson gson() {
        if (gson == null)
            gson = new GsonBuilder().registerTypeAdapterFactory(OptionalTypeAdapterFactory.instance()).registerTypeAdapterFactory(RpcTypeAdapterFactory.instance()).create();

        return gson;
    }

    public static Gson gsonWithNulls() {
        if (gsonWithNulls == null)
            gsonWithNulls = new GsonBuilder().registerTypeAdapterFactory(OptionalTypeAdapterFactory.instance()).registerTypeAdapterFactory(RpcTypeAdapterFactory.instance()).serializeNulls().create();

        return gsonWithNulls;
    }

}