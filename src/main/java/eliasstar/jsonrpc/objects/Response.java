package eliasstar.jsonrpc.objects;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public record Response(String jsonrpc, String id, JsonObject result, Error error) {

    public static record Error(int code, String message, JsonElement data) {
    }

    public boolean isSuccessful() {
        return result != null;
    }

}