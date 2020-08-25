package eliasstar.jsonrpc.objects;

import java.math.BigDecimal;
import java.util.Objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import eliasstar.jsonrpc.objects.id.Id;
import eliasstar.jsonrpc.objects.id.NumberId;
import eliasstar.jsonrpc.objects.id.StringId;
import eliasstar.jsonrpc.objects.parameter.ArrayParameter;
import eliasstar.jsonrpc.objects.parameter.ObjectParameter;
import eliasstar.jsonrpc.objects.parameter.Parameter;

public final class Request {

    private final String jsonrpc = "2.0";
    private final Id<?> id;
    private final String method;
    private final Parameter<?> params;

    public Request(String id, String method, JsonObject params) {
        this(new StringId(id), method, new ObjectParameter(params));
    }

    public Request(String id, String method, JsonArray params) {
        this(new StringId(id), method, new ArrayParameter(params));
    }

    public Request(long id, String method, JsonObject params) {
        this(new NumberId(new BigDecimal(id)), method, new ObjectParameter(params));
    }

    public Request(long id, String method, JsonArray params) {
        this(new NumberId(new BigDecimal(id)), method, new ArrayParameter(params));
    }

    public Request(double id, String method, JsonObject params) {
        this(new NumberId(new BigDecimal(id)), method, new ObjectParameter(params));
    }

    public Request(double id, String method, JsonArray params) {
        this(new NumberId(new BigDecimal(id)), method, new ArrayParameter(params));
    }

    private Request(Id<?> id, String method, Parameter<?> params) {
        this.id = id;
        this.method = Objects.requireNonNull(method);
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

    public Parameter<?> params() {
        return params;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jsonrpc, id, method, params);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Request other) {
            if (this == other)
                return true;

            if (id != null && params != null)
                return jsonrpc.equals(other.jsonrpc) && id.equals(other.id) && method.equals(other.method) && params.equals(other.params);

            if (params != null)
                return jsonrpc.equals(other.jsonrpc) && method.equals(other.method) && params.equals(other.params);

            if (id != null)
                return jsonrpc.equals(other.jsonrpc) && id.equals(other.id) && method.equals(other.method);

            return jsonrpc.equals(other.jsonrpc) && method.equals(other.method);
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        sb.append("\"jsonrpc\": \"" + jsonrpc + "\"");

        if (id != null)
            sb.append(", \"id\": " + id.toString());

        sb.append(", \"method\": \"" + method + "\"");

        if (params != null)
            sb.append(", \"params\": " + params.toString());

        sb.append("}");

        return sb.toString();
    }

}