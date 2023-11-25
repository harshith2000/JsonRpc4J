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

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eliasstar.utils.GsonProvider;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class NotificationTests {

    private static Gson gson;
    private static Gson gsonWithNulls;

    @BeforeAll
    public static void initGson() {
        gson = GsonProvider.gson();
        gsonWithNulls = GsonProvider.gsonWithNulls();
    }

    private String normalizeJson(String json) {
        return Arrays.stream(json.replaceAll("[{}\"]", "").split(","))
                .sorted()
                .collect(Collectors.joining(","));
    }

    @Test
    public void testNotificationSerialization() {
        var json = "{\"jsonrpc\":\"2.0\",\"method\":\"test\"}";
        var obj = new Notification("test");

        assertAll(
                () -> assertEquals(normalizeJson(json), normalizeJson(gson.toJson(obj))),
                () -> assertEquals(normalizeJson(json), normalizeJson(gsonWithNulls.toJson(obj))));
    }

    @Test
    public void testNotificationDeserialization() {
        var json = "{\"jsonrpc\":\"2.0\",\"method\":\"test\"}";
        var obj = new Notification("test");

        assertAll(
                () -> assertEquals(obj, gson.fromJson(json, Notification.class)),
                () -> assertEquals(obj, gson.fromJson(json, Request.class)));
    }

}