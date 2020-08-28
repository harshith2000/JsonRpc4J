package eliasstar.jsonrpc.objects;

import java.util.Optional;

import com.google.gson.JsonElement;

import eliasstar.jsonrpc.objects.id.Id;

public record Response(String jsonrpc, Id<?> id, Optional<JsonElement> result, Optional<Error> error) {

    @SuppressWarnings("unused") // Used indirectly by GSON
    private Response() {
        this.jsonrpc = "2.0";
        this.id = null;
        this.result = Optional.empty();
        this.error = Optional.empty();
    }

    public boolean isSuccessful() {
        return result.isPresent();
    }

    public boolean isUnsuccessful() {
        return error.isPresent();
    }

}