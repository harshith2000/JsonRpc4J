package eliasstar.jsonrpc.objects;

import com.google.gson.JsonElement;

import eliasstar.jsonrpc.objects.id.Id;

public record Response(String jsonrpc, Id<?> id, JsonElement result, Error error) {

    public record Error(long code, String message, JsonElement data) {
    }

    public boolean isSuccessful() {
        return result != null;
    }

}