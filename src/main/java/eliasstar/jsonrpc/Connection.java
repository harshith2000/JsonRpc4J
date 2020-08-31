package eliasstar.jsonrpc;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Objects;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import eliasstar.jsonrpc.exceptions.ConnectionException;
import eliasstar.jsonrpc.exceptions.ErrorResponseException;
import eliasstar.jsonrpc.exceptions.IdMismatchException;
import eliasstar.jsonrpc.objects.Notification;
import eliasstar.jsonrpc.objects.Request;
import eliasstar.jsonrpc.objects.Response;

public class Connection {

    private final HttpClient client;
    private final HttpRequest.Builder reqBuilder;
    private final Gson gson;
    private final Optional<String> id;
    private int requestId;

    Connection(String id, HttpClient client, HttpRequest.Builder reqBuilder, Gson gson) {
        this.id = Optional.ofNullable(id);
        this.client = Objects.requireNonNull(client);
        this.reqBuilder = Objects.requireNonNull(reqBuilder);
        this.gson = Objects.requireNonNull(gson);
    }

    public Response sendRequest(Request req) throws ConnectionException {
        Objects.requireNonNull(req);

        var res = send(gson.toJson(req));

        if (req instanceof Notification)
            return null;

        return gson.fromJson(res, Response.class);
    }

    public JsonElement callRemoteProcedure(String method) throws ConnectionException, ErrorResponseException, IdMismatchException {
        var req = id.map(i -> new Request(i + requestId++, method)).orElse(new Request(requestId++, method));
        return checkResponse(req, sendRequest(req));
    }

    public JsonElement callRemoteProcedure(String method, JsonArray params) throws ConnectionException, ErrorResponseException, IdMismatchException {
        var req = id.map(i -> new Request(i + requestId++, method, params)).orElse(new Request(requestId++, method, params));
        return checkResponse(req, sendRequest(req));
    }

    public JsonElement callRemoteProcedure(String method, JsonObject params) throws ConnectionException, ErrorResponseException, IdMismatchException {
        var req = id.map(i -> new Request(i + requestId++, method, params)).orElse(new Request(requestId++, method, params));
        return checkResponse(req, sendRequest(req));
    }

    public void sendNotification(String method) throws ConnectionException {
        sendRequest(new Notification(method));
    }

    public void sendNotification(String method, JsonArray params) throws ConnectionException {
        sendRequest(new Notification(method, params));
    }

    public void sendNotification(String method, JsonObject params) throws ConnectionException {
        sendRequest(new Notification(method, params));
    }

    public Response[] sendBatchRequest(Request... requests) throws ConnectionException {
        Objects.requireNonNull(requests);

        if (requests.length == 0)
            return null;

        var res = send(gson.toJson(requests));

        // ? is this working
        if (requests instanceof Notification[])
            return null;

        return gson.fromJson(res, Response[].class);
    }

    private String send(String content) throws ConnectionException {
        try {
            var httpReq = reqBuilder.POST(BodyPublishers.ofString(content)).build();
            return client.send(httpReq, BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            throw new ConnectionException(e);
        }
    }

    private JsonElement checkResponse(Request req, Response res) throws ErrorResponseException, IdMismatchException {
        if (res.isUnsuccessful())
            throw new ErrorResponseException(res.error().get());

        if (!req.id().get().equals(res.id()))
            throw new IdMismatchException(req.id().get(), res.id());

        return res.result().get();
    }

    int requestsMade() {
        return requestId;
    }

}