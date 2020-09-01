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

    @Override
    public String toString() {
        return "Error@" + Integer.toHexString(hashCode()) + " " + contentAsJsonString();
    }

    protected String contentAsJsonString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        sb.append("\"code\": \"" + code + "\"");
        sb.append(", \"message\": " + message);
        data.ifPresent(d -> sb.append(", \"data\": " + d.toString()));
        sb.append("}");

        return sb.toString();
    }
}