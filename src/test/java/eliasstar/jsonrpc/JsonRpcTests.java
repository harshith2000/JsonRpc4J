package eliasstar.jsonrpc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public final class JsonRpcTests {

    @Test
    @DisplayName("connectionIds should increment")
    public void testConnectionIds() {
        var startCons = JsonRpc.connectionsMade();

        var con = JsonRpc.connect("https://www.example.com");
        assertEquals(con.id, JsonRpc.CONNECTION_PREFIX + startCons);

        var con2 = JsonRpc.connect("https://www.example.com");
        assertEquals(con2.id, JsonRpc.CONNECTION_PREFIX + (startCons + 1));

        assertEquals(startCons + 2, JsonRpc.connectionsMade());
    }
}