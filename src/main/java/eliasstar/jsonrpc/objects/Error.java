package eliasstar.jsonrpc.objects;

import java.util.Optional;

import com.google.gson.JsonElement;

public record Error(long code, String message, Optional<JsonElement> data) {

    @SuppressWarnings("unused") // Used indirectly by GSON
    private Error() {
        this.code = 0;
        this.message = "";
        this.data = Optional.empty();
    }
}