package eliasstar.jsonrpc.objects;

import java.util.Optional;

import com.google.gson.JsonElement;

import eliasstar.jsonrpc.objects.id.Id;

public record Response(String jsonrpc, Id<?> id, Optional<JsonElement> result, Optional<Error> error) {

    @SuppressWarnings("unused") // Used indirectly by GSON
    private Response() {
        this("2.0", null, Optional.empty(), Optional.empty());
    }

    public boolean isSuccessful() {
        return result.isPresent();
    }

    public boolean isUnsuccessful() {
        return error.isPresent();
    }

    @Override
    public String toString() {
        return "Response@" + Integer.toHexString(hashCode()) + " " + contentAsJsonString();
    }

    protected String contentAsJsonString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        sb.append("\"jsonrpc\": \"" + jsonrpc + "\"");
        sb.append(", \"id\": " + id);
        result.ifPresent(r -> sb.append(", \"result\": " + r));
        error.ifPresent(e -> sb.append(", \"error\": " + e));
        sb.append("}");

        return sb.toString();
    }

}