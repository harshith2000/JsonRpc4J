package eliasstar.jsonrpc;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import eliasstar.jsonrpc.exceptions.ConnectionException;
import eliasstar.jsonrpc.exceptions.ErrorResponseException;
import eliasstar.jsonrpc.exceptions.IdMismatchException;
import eliasstar.jsonrpc.objects.Request;
import eliasstar.utils.GsonProvider;
import eliasstar.utils.mocks.HttpClientMock;

public final class ConnectionTests {

    private static HttpClientMock client;
    private static Connection connection;
    private static Gson gson;

    @BeforeAll
    public static void initConnection() {
        client = new HttpClientMock();
        connection = new ConnectionBuilder(client, "https://www.example.com").build();
        gson = GsonProvider.gson();
    }

    @Test
    public void testRequestSending() {

    }

    @Test
    public void testConnectionWithId() {

    }

    @Test
    public void testRequestIdIncrementation() {

    }

    @Test
    public void testRemoteProcedureCalling() {
        var cases = new Executable[] {
                () -> {
                    var id = connection.requestsMade();
                    client.setResponse("{\"jsonrpc\":\"2.0\",\"id\":" + id + ",\"result\":\"test\"}");
                    connection.callRemoteProcedure("test");
                    assertEquals(new Request(id, "test"), gson.fromJson(client.getRequest(), Request.class));
                },
                () -> {
                    var id = connection.requestsMade();
                    client.setResponse("{\"jsonrpc\":\"2.0\",\"id\":" + id + ",\"result\":\"test\"}");
                    connection.callRemoteProcedure("test", new JsonArray());
                    assertEquals(new Request(id, "test", new JsonArray()), gson.fromJson(client.getRequest(), Request.class));
                },
                () -> {
                    var id = connection.requestsMade();
                    client.setResponse("{\"jsonrpc\":\"2.0\",\"id\":" + id + ",\"result\":\"test\"}");
                    connection.callRemoteProcedure("test", new JsonObject());
                    assertEquals(new Request(id, "test", new JsonObject()), gson.fromJson(client.getRequest(), Request.class));
                }
        };

        assertAll(cases);
    }

    @Test
    public void testCorrectResponse() throws ConnectionException, ErrorResponseException, IdMismatchException {
        client.setResponse("{\"jsonrpc\":\"2.0\",\"id\":" + connection.requestsMade() + ",\"result\":\"test\"}");
        assertEquals("test", connection.callRemoteProcedure("test").getAsString());
    }

    @Test
    public void testResponseWithWrongId() {
        client.setResponse("{\"jsonrpc\":\"2.0\",\"id\":\"wrong\",\"result\":\"test\"}");

        assertThrows(IdMismatchException.class, () -> {
            connection.callRemoteProcedure("method");
        });
    }

    @Test
    public void testResponseWithError() {
        client.setResponse("{\"jsonrpc\":\"2.0\",\"id\":\"null\",\"error\":{\"code\":-32000,\"message\":\"test\"}}");

        assertThrows(ErrorResponseException.class, () -> {
            connection.callRemoteProcedure("method");
        });
    }

    @Test
    public void testNotificationSending() {
        client.setResponse("");

        var cases = new Executable[] {
                () -> {
                    connection.sendNotification("test");
                    assertEquals("{\"jsonrpc\":\"2.0\",\"method\":\"test\"}", client.getRequest());
                },
                () -> {
                    connection.sendNotification("test", new JsonArray());
                    assertEquals("{\"jsonrpc\":\"2.0\",\"method\":\"test\",params:[]}", client.getRequest());
                },
                () -> {
                    connection.sendNotification("test", new JsonObject());
                    assertEquals("{\"jsonrpc\":\"2.0\",\"method\":\"test\",params:{}}", client.getRequest());
                }
        };

        assertAll(cases);
    }

    @Test
    public void testBatchRequestSending() {

    }

}