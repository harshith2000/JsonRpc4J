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

package eliasstar.jsonrpc.objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eliasstar.jsonrpc.objects.id.NullId;
import eliasstar.utils.GsonProvider;

public final class RequestTests {

    private static Gson gson;
    private static Gson gsonWithNulls;

    @BeforeAll
    public static void initGson() {
        gson = GsonProvider.gson();
        gsonWithNulls = GsonProvider.gsonWithNulls();
    }

    @Test
    public void testRequestIdSerialization() {
        var cases = new HashMap<String, Request>();

        cases.put("{\"jsonrpc\":\"2.0\",\"id\":\"test\",\"method\":\"test\"}", new Request("test", "test"));
        cases.put("{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\"test\"}", new Request(0, "test"));
        cases.put("{\"jsonrpc\":\"2.0\",\"id\":null,\"method\":\"test\"}", new Request(NullId.instance(), "test", null));

        cases.forEach((e, a) -> assertAll(
                () -> assertEquals(gson.fromJson(e, JsonObject.class), gson.fromJson(gson.toJson(a), JsonObject.class)),
                () -> assertEquals(gsonWithNulls.fromJson(e, JsonObject.class), gsonWithNulls.fromJson(gsonWithNulls.toJson(a), JsonObject.class))));
    }

    @Test
    public void testRequestIdDeserialization() {
        var cases = new HashMap<Request, String>();

        cases.put(new Request("test", "test"), "{\"jsonrpc\":\"2.0\",\"id\":\"test\",\"method\":\"test\"}");
        cases.put(new Request(0, "test"), "{\"jsonrpc\":\"2.0\",\"id\":0,\"method\":\"test\"}");
        cases.put(new Request(NullId.instance(), "test", null), "{\"jsonrpc\":\"2.0\",\"id\":null,\"method\":\"test\"}");

        cases.forEach((e, a) -> assertEquals(e, gson.fromJson(a, Request.class)));
    }

    @Test
    public void testRequestParamsSerialization() {
        // Test array parameters
        JsonArray arrParams = new JsonArray();
        arrParams.add("test");
        arrParams.add(0);
        arrParams.add(JsonNull.INSTANCE);
        assertEquals(gson.fromJson("{\"jsonrpc\":\"2.0\",\"method\":\"test\",\"params\":[\"test\",0,null]}", JsonObject.class),
                gson.fromJson(gson.toJson(new Notification("test", arrParams)), JsonObject.class));

        // Test object parameters
        JsonObject objParams = new JsonObject();
        objParams.addProperty("string", "test");
        objParams.addProperty("number", 0);
        objParams.add("null", JsonNull.INSTANCE);
        assertEquals(gson.fromJson("{\"jsonrpc\":\"2.0\",\"method\":\"test\",\"params\":{\"string\":\"test\",\"number\":0}}", JsonObject.class),
                gson.fromJson(gson.toJson(new Notification("test", objParams)), JsonObject.class));
        assertEquals(gson.fromJson("{\"jsonrpc\":\"2.0\",\"method\":\"test\",\"params\":{\"string\":\"test\",\"number\":0,\"null\":null}}", JsonObject.class),
                gsonWithNulls.fromJson(gsonWithNulls.toJson(new Notification("test", objParams)), JsonObject.class));
    }

    @Test
    public void testRequestParamsDeserialization() {
        var arrParams = new JsonArray();
        arrParams.add("test");
        arrParams.add(0);
        arrParams.add(JsonNull.INSTANCE);

        assertEquals(new Notification("test", arrParams), gson.fromJson("{\"jsonrpc\":\"2.0\",\"method\":\"test\",\"params\":[\"test\",0,null]}", Request.class));

        var objParams = new JsonObject();
        objParams.addProperty("string", "test");
        objParams.addProperty("number", 0);
        objParams.add("null", JsonNull.INSTANCE);

        assertEquals(new Notification("test", objParams), gson.fromJson("{\"jsonrpc\":\"2.0\",\"method\":\"test\",\"params\":{\"string\":\"test\",\"number\":0,\"null\":null}}", Request.class));
    }

}