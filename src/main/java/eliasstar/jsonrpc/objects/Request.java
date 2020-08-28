package eliasstar.jsonrpc.objects;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import eliasstar.jsonrpc.objects.id.Id;
import eliasstar.jsonrpc.objects.id.NullId;
import eliasstar.jsonrpc.objects.id.NumberId;
import eliasstar.jsonrpc.objects.id.StringId;
import eliasstar.jsonrpc.objects.parameter.ArrayParameter;
import eliasstar.jsonrpc.objects.parameter.ObjectParameter;
import eliasstar.jsonrpc.objects.parameter.Parameter;

public class Request {

    private final String jsonrpc = "2.0";
    private final Optional<Id<?>> id;
    private final String method;
    private final Optional<Parameter<?>> params;

    @SuppressWarnings("unused") // Used indirectly by GSON
    private Request() {
        this.id = Optional.empty();
        this.method = "";
        this.params = Optional.empty();
    }

    protected Request(Id<?> id, String method, Parameter<?> params) {
        this.id = Optional.ofNullable(id);
        this.method = Objects.requireNonNull(method);
        this.params = Optional.ofNullable(params);
    }

    public Request(String id, String method) {
        this(id != null ? new StringId(id) : NullId.instance(), method, null);
    }

    public Request(String id, String method, JsonArray params) {
        this(id != null ? new StringId(id) : NullId.instance(), method, new ArrayParameter(params));
    }

    public Request(String id, String method, JsonObject params) {
        this(id != null ? new StringId(id) : NullId.instance(), method, new ObjectParameter(params));
    }

    public Request(Number id, String method) {
        this(id != null ? new NumberId(new BigDecimal(id.toString())) : NullId.instance(), method, null);
    }

    public Request(Number id, String method, JsonArray params) {
        this(id != null ? new NumberId(new BigDecimal(id.toString())) : NullId.instance(), method, new ArrayParameter(params));
    }

    public Request(Number id, String method, JsonObject params) {
        this(id != null ? new NumberId(new BigDecimal(id.toString())) : NullId.instance(), method, new ObjectParameter(params));
    }

    public String jsonrpc() {
        return jsonrpc;
    }

    public Optional<Id<?>> id() {
        return id;
    }

    public String method() {
        return method;
    }

    public Optional<Parameter<?>> params() {
        return params;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jsonrpc, id, method, params);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Request other)
            return this == other || jsonrpc.equals(other.jsonrpc) && id.equals(other.id) && method.equals(other.method) && params.equals(other.params);

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        sb.append("\"jsonrpc\": \"" + jsonrpc + "\"");
        id.ifPresent(i -> sb.append(", \"id\": " + i.toString()));
        sb.append(", \"method\": \"" + method + "\"");
        params.ifPresent(p -> sb.append(", \"params\": " + p.toString()));
        sb.append("}");

        return sb.toString();
    }

}