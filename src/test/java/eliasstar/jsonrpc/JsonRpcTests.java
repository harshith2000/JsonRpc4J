package eliasstar.jsonrpc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

public final class JsonRpcTests {

    @RepeatedTest(3)
    @DisplayName("connectionIds should increment")
    public void testConnectionIds() {
        var conCount = JsonRpc.connectionsMade();
        var con = JsonRpc.connect("https://www.example.com");

        assertEquals(con.id, JsonRpc.CONNECTION_PREFIX + conCount++);
        assertEquals(conCount, JsonRpc.connectionsMade());
    }
}