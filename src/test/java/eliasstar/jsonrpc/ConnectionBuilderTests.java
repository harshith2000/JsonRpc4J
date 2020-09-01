package eliasstar.jsonrpc;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import org.junit.jupiter.api.Test;

public final class ConnectionBuilderTests {

    @Test
    public void testBuildWithNoUri() {
        var builder = new ConnectionBuilder(HttpClient.newHttpClient());
        var reqBuilder = HttpRequest.newBuilder();

        assertAll(
                () -> assertThrows(IllegalStateException.class, () -> builder.build()),
                () -> assertThrows(IllegalStateException.class, () -> builder.setRequestBuilder(reqBuilder).build()));
    }
}