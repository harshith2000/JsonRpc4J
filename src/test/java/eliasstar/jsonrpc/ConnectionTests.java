package eliasstar.jsonrpc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.URI;
import java.net.http.HttpRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import eliasstar.jsonrpc.exceptions.RpcErrorException;
import eliasstar.jsonrpc.exceptions.RpcIdMismatchException;
import eliasstar.jsonrpc.objects.Request;
import eliasstar.jsonrpc.objects.Response;
import eliasstar.utils.mocks.HttpClientMock;

public final class ConnectionTests {

    private static HttpClientMock client;
    private static Connection con;

    @BeforeAll
    public static void initConnection() {
        client = new HttpClientMock();
        con = new Connection("test", client, HttpRequest.newBuilder().uri(URI.create("https://www.example.com")), new GsonBuilder());
    }

    @RepeatedTest(3)
    @DisplayName("requestIds should increment")
    public void testRequestIds() {
        var reqCount = con.requestsMade();
        client.setResponse("{\"jsonrpc\": \"2.0\", \"id\": \"test-" + reqCount + "\", \"result\": \"test\"}");

        assertDoesNotThrow(() -> {
            con.callRemoteProcedure("method", new JsonObject());
        });

        assertEquals(reqCount + 1, con.requestsMade());
    }

    @Test
    @DisplayName("request and response should correctly (de)serialize")
    public void testSendingRPC() {
        var response = "{\"jsonrpc\": \"2.0\", \"id\": \"test\", \"result\": \"test\"}";
        var request = new Request("test", "method", new JsonObject());
        client.setResponse(response);

        assertDoesNotThrow(() -> {
            assertEquals(new Gson().fromJson(response, Response.class), con.sendRPCRequest(request));
        });

        assertEquals(request, new Gson().fromJson(client.getRequest(), Request.class));
    }

    @Test
    @DisplayName("wrong response id should throw exception")
    public void testWrongResponseId() {
        client.setResponse("{\"jsonrpc\": \"2.0\", \"id\": \"wrong\", \"result\": \"test\"}");

        assertThrows(RpcIdMismatchException.class, () -> {
            con.callRemoteProcedure("method", new JsonObject());
        });
    }

    @Test
    @DisplayName("response with error should throw exception")
    public void testErrorResponse() {
        client.setResponse("{\"jsonrpc\": \"2.0\", \"id\": \"null\", \"error\": {\"code\": -32000, \"message\": \"test\"}}");

        assertThrows(RpcErrorException.class, () -> {
            con.callRemoteProcedure("method", new JsonObject());
        });
    }
}