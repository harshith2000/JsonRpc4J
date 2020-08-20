package eliasstar.jsonrpc;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import eliasstar.jsonrpc.exceptions.RpcConnectionException;
import eliasstar.jsonrpc.exceptions.RpcErrorException;
import eliasstar.jsonrpc.exceptions.RpcIdMismatchException;
import eliasstar.jsonrpc.objects.Request;
import eliasstar.jsonrpc.objects.Response;

public class Connection {

    private final HttpClient client;
    private final HttpRequest.Builder requestBuilder;
    private final Gson jsonConverter;
    final String id;
    private int requestId = 0;

    public Connection(String id, HttpClient client, HttpRequest.Builder reqBuilder, GsonBuilder gsonBuilder) {
        this.client = client;
        this.requestBuilder = reqBuilder.setHeader("Content-Type", "application/json");
        this.jsonConverter = gsonBuilder.serializeNulls().create();
        this.id = id;
    }

    public JsonElement callRemoteProcedure(String method, JsonObject params) throws RpcConnectionException, RpcErrorException, RpcIdMismatchException {
        var req = new Request(id + "-" + requestId++, method, params);
        var res = sendRPCRequest(req);

        if (!res.isSuccessful())
            throw new RpcErrorException(res.error());

        if (res.id() != req.id())
            throw new RpcIdMismatchException(req.id(), res.id());

        return res.result();
    }

    public JsonElement callRemoteProcedure(String method, JsonArray params) throws RpcConnectionException, RpcErrorException, RpcIdMismatchException {
        var req = new Request(id + "-" + requestId++, method, params);
        var res = sendRPCRequest(req);

        if (!res.isSuccessful())
            throw new RpcErrorException(res.error());

        if (res.id() != req.id())
            throw new RpcIdMismatchException(req.id(), res.id());

        return res.result();
    }

    public Response sendRPCRequest(Request req) throws RpcConnectionException {
        var body = jsonConverter.toJson(req, Request.class);
        var httpReq = requestBuilder.POST(BodyPublishers.ofString(body)).build();

        HttpResponse<String> httpRes;
        try {
            httpRes = client.send(httpReq, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RpcConnectionException(e.getMessage(), e);
        }

        return jsonConverter.fromJson(httpRes.body(), Response.class);
    }

    int requestsMade() {
        return requestId;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Connection))
            return false;

        Connection other = (Connection) obj;

        if (this == other)
            return true;

        return client.equals(other.client) && requestBuilder.equals(other.requestBuilder) && jsonConverter.equals(other.jsonConverter) && id == other.id && requestId == other.requestId;
    }

}