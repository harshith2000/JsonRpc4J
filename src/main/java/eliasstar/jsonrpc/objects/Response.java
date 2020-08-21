package eliasstar.jsonrpc.objects;

import com.google.gson.JsonElement;

public record Response(String jsonrpc, String id, JsonElement result, Error error) {

    public static record Error(int code, String message, JsonElement data) {
    }

    public boolean isSuccessful() {
        return result != null;
    }

}