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
    private final String connectionId;
    private int requestId = 0;

    public Connection(String id, HttpClient client, HttpRequest.Builder reqBuilder, GsonBuilder gsonBuilder) {
        this.client = client;
        this.requestBuilder = reqBuilder.setHeader("Content-Type", "application/json");
        this.jsonConverter = gsonBuilder.serializeNulls().create();
        this.connectionId = id;
    }

    public JsonElement callRemoteProcedure(String method, JsonObject params) throws RpcConnectionException, RpcErrorException, RpcIdMismatchException {
        return sendRPCRequest(new Request(connectionId + "-" + requestId++, method, params));
    }

    public JsonElement callRemoteProcedure(String method, JsonArray params) throws RpcConnectionException, RpcErrorException, RpcIdMismatchException {
        return sendRPCRequest(new Request(connectionId + "-" + requestId++, method, params));
    }

    public JsonElement sendRPCRequest(Request req) throws RpcConnectionException, RpcErrorException, RpcIdMismatchException {
        var body = jsonConverter.toJson(req, Request.class);
        var httpReq = requestBuilder.POST(BodyPublishers.ofString(body)).build();

        HttpResponse<String> httpRes;
        try {
            httpRes = client.send(httpReq, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RpcConnectionException(e.getMessage(), e);
        }

        var res = jsonConverter.fromJson(httpRes.body(), Response.class);

        if (!res.isSuccessful())
            throw new RpcErrorException(res.error());

        if (res.id() != req.id())
            throw new RpcIdMismatchException(req.id(), res.id());

        return res.result();
    }

    public int requestsMade() {
        return requestId;
    }

}