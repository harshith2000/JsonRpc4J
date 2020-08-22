package eliasstar.jsonrpc.objects;

import java.math.BigDecimal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import eliasstar.jsonrpc.objects.id.NumberId;
import eliasstar.jsonrpc.objects.id.Id;
import eliasstar.jsonrpc.objects.id.StringId;

public class Request {

    private final String jsonrpc = "2.0";
    private final Id<?> id;
    private final String method;
    private final JsonElement params;

    public Request(String id, String method, JsonObject params) {
        this.id = new StringId(id);
        this.method = method;
        this.params = params;
    }

    public Request(String id, String method, JsonArray params) {
        this.id = new StringId(id);
        this.method = method;
        this.params = params;
    }

    public Request(BigDecimal id, String method, JsonObject params) {
        this.id = new NumberId(id);
        this.method = method;
        this.params = params;
    }

    public Request(BigDecimal id, String method, JsonArray params) {
        this.id = new NumberId(id);
        this.method = method;
        this.params = params;
    }

    public String jsonrpc() {
        return jsonrpc;
    }

    public Id<?> id() {
        return id;
    }

    public String method() {
        return method;
    }

    public JsonElement params() {
        return params;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Request other)
            return this == other || jsonrpc.equals(other.jsonrpc) && id.equals(other.id) && method.equals(other.method) && params.equals(other.params);

        return false;
    }

}