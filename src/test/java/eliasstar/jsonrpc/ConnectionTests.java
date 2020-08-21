package eliasstar.jsonrpc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URI;
import java.net.http.HttpRequest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import eliasstar.jsonrpc.exceptions.RpcConnectionException;
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
        con = new Connection("test", client, HttpRequest.newBuilder().uri(URI.create("https://www.example.com")), new Gson().newBuilder());
    }

    @RepeatedTest(3)
    @DisplayName("requestIds should increment")
    public void testRequestIds() {
        try {
            var reqCount = con.requestsMade();

            client.setResponse("{\"jsonrpc\": \"2.0\",\"id\": \"test-" + reqCount + "\",\"result\": \"test\"}");
            con.callRemoteProcedure("method", new JsonObject());

            assertEquals(con.requestsMade(), ++reqCount);
        } catch (RpcErrorException e) {
            fail("client response not configured correctly", e);
        } catch (RpcConnectionException e) {
            fail("internal client error", e);
        } catch (RpcIdMismatchException e) {
            fail(e);
        }
    }

    @Test
    @DisplayName("request and response should correctly (de)serialize")
    public void testSendingRPC() {
        var response = "{\"jsonrpc\": \"2.0\",\"id\": \"test\",\"result\": \"test\"}";
        var request = new Request("test", "method", new JsonObject());
        client.setResponse(response);

        assertDoesNotThrow(() -> {
            assertEquals(con.sendRPCRequest(request), new Gson().fromJson(response, Response.class));
        });

        assertEquals(request, new Gson().fromJson(client.getRequest(), Request.class));
    }

    @Test
    @DisplayName("wrong response id should throw exception")
    public void testWrongResponseId() {
    }

    @Test
    @DisplayName("response with error should throw exception")
    public void testErrorResponse() {
    }
}