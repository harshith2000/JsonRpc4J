package eliasstar.jsonrpc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.RepeatedTest;

public final class JsonRpcTests {

    @RepeatedTest(3)
    public void testConnectionIdIncrementation() {
        var consMade = JsonRpc.connectionsMade();

        var con = JsonRpc.connect("https://www.example.com");

        assertEquals(JsonRpc.CONNECTION_PREFIX + consMade, con.id().get());
    }

}