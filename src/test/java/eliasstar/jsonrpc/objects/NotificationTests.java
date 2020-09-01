package eliasstar.jsonrpc.objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eliasstar.utils.GsonProvider;

public final class NotificationTests {

    private static Gson gson;
    private static Gson gsonWithNulls;

    @BeforeAll
    public static void initGson() {
        gson = GsonProvider.gson();
        gsonWithNulls = GsonProvider.gsonWithNulls();
    }

    @Test
    public void testNotificationSerialization() {
        var json = "{\"jsonrpc\":\"2.0\",\"method\":\"test\"}";
        var obj = new Notification("test");

        assertAll(
                () -> assertEquals(json, gson.toJson(obj)),
                () -> assertEquals(json, gsonWithNulls.toJson(obj)));
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