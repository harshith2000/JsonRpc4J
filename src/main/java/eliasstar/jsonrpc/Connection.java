package eliasstar.jsonrpc;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

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

/**
 * This class is the primary way of interacting with a JSON-RPC service.
 * <p>
 * Use it to {@link #callRemoteProcedure(String) call a remote procedure} or {@link #sendNotification(String) send a} {@link Notification}.
 *
 * @author Elias*
 * @version 1.2.0
 * @since 0.1.0
 */
public class Connection {

    /** The client used by this {@link Connection}. */
    protected final HttpClient client;

    /** The request builder used for new requests. */
    protected final HttpRequest.Builder reqBuilder;

    /** The gson instance used for serialization and deserialization. */
    protected final Gson gson;

    /** The optional id of this {@link Connection}. */
    protected final Optional<String> id;

    /** The next request id. */
    protected int requestId;

    /**
     * Used by {@link ConnectionBuilder}.
     *
     * @param id The id prefix used for requests
     * @param client The client used for requests
     * @param reqBuilder The request builder used for new requests
     * @param gson The gson instance used for serialization and deserialization.
     */
    protected Connection(String id, HttpClient client, HttpRequest.Builder reqBuilder, Gson gson) {
        this.id = Optional.ofNullable(id);
        this.client = Objects.requireNonNull(client);
        this.reqBuilder = Objects.requireNonNull(reqBuilder);
        this.gson = Objects.requireNonNull(gson);
    }

    /**
     * Sends a {@link Request} object to the server.
     * <p>
     * If the request is a {@link Notification}
     * the returned {@link Optional} will be empty.
     *
     * @param req The {@link Request} or {@link Notification}
     * @return A optional {@link Response}
     * @throws ConnectionException If sending fails
     */
    public Optional<Response> sendRequest(Request req) throws ConnectionException {
        Objects.requireNonNull(req);

        var res = send(gson.toJson(req));

        if (req instanceof Notification)
            return Optional.empty();

        return Optional.of(gson.fromJson(res, Response.class));
    }

    /**
     * Calls a remote procedure.
     * <p>
     * Sends a {@link Request} with the specified method and checks the response.
     *
     * @param method The method to invoke
     * @return The result of the operation
     * @throws ConnectionException If sending fails
     * @throws ErrorResponseException If a {@link Response} with an {@link Error} is received
     * @throws IdMismatchException If the {@link Response} does not have the same id as the {@link Request}.
     */
    public JsonElement callRemoteProcedure(String method) throws ConnectionException, ErrorResponseException, IdMismatchException {
        var req = id.map(i -> new Request(i + "-" + requestId++, method)).orElse(new Request(requestId++, method));
        return checkResponse(req, sendRequest(req).get());
    }

    /**
     * Calls a remote procedure.
     * <p>
     * Sends a {@link Request} with the specified method and params
     * and checks the response.
     *
     * @param method The method to invoke
     * @param params The parameters of the method
     * @return The result of the operation
     * @throws ConnectionException If sending fails
     * @throws ErrorResponseException If a {@link Response} with an {@link Error} is received
     * @throws IdMismatchException If the {@link Response} does not have the same id as the {@link Request}.
     */
    public JsonElement callRemoteProcedure(String method, JsonArray params) throws ConnectionException, ErrorResponseException, IdMismatchException {
        var req = id.map(i -> new Request(i + "-" + requestId++, method, params)).orElse(new Request(requestId++, method, params));
        return checkResponse(req, sendRequest(req).get());
    }

    /**
    * Calls a remote procedure.
    * <p>
    * Sends a {@link Request} with the specified method and params
    * and checks the response.
    *
    * @param method The method to invoke
    * @param params The parameters of the method
    * @return The result of the operation
    * @throws ConnectionException If sending fails
    * @throws ErrorResponseException If a {@link Response} with an {@link Error} is received
    * @throws IdMismatchException If the {@link Response} does not have the same id as the {@link Request}.
    */
    public JsonElement callRemoteProcedure(String method, JsonObject params) throws ConnectionException, ErrorResponseException, IdMismatchException {
        var req = id.map(i -> new Request(i + "-" + requestId++, method, params)).orElse(new Request(requestId++, method, params));
        return checkResponse(req, sendRequest(req).get());
    }

    /**
    * Sends a {@link Notification} with the specified method.
    *
    * @param method The method to invoke
    * @throws ConnectionException If sending fails
    */
    public void sendNotification(String method) throws ConnectionException {
        sendRequest(new Notification(method));
    }

    /**
    * Sends a {@link Notification} with the specified method and parameters.
    *
    * @param method The method to invoke
    * @param params The parameters of the method
    * @throws ConnectionException If sending fails
    */
    public void sendNotification(String method, JsonArray params) throws ConnectionException {
        sendRequest(new Notification(method, params));
    }

    /**
    * Sends a {@link Notification} with the specified method and parameters.
    *
    * @param method The method to invoke
    * @param params The parameters of the method
    * @throws ConnectionException If sending fails
    */
    public void sendNotification(String method, JsonObject params) throws ConnectionException {
        sendRequest(new Notification(method, params));
    }

    /**
     * Sends an array of {@link Request} objects to the server.
     * <p>
     * If all requests are {@link Notification Notifications}
     * the returned {@link Optional} will be empty.
     *
     * @param requests The {@link Request Requests} or {@link Notification Notifications}
     * @return A optional {@link Response} array
     * @throws ConnectionException If sending fails
     */
    public Optional<Response[]> sendBatchRequest(Request... requests) throws ConnectionException {
        Objects.requireNonNull(requests);

        if (requests.length == 0)
            return null;

        var res = send(gson.toJson(requests));

        Supplier<Boolean> allNotification = () -> Arrays.asList(requests).stream().allMatch(req -> {
            return req instanceof Notification;
        });

        if (requests instanceof Notification[] || allNotification.get())
            return Optional.empty();

        return Optional.of(gson.fromJson(res, Response[].class));
    }

    /**
     * Sends the string to the server.
     *
     * @param content The content of the body
     * @return The response body
     * @throws ConnectionException If sending fails
     */
    private String send(String content) throws ConnectionException {
        try {
            var httpReq = reqBuilder.POST(BodyPublishers.ofString(content)).build();
            return client.send(httpReq, BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            throw new ConnectionException(e);
        }
    }

    /**
     * Checks the response if the ids match and no error is received.
     *
     * @param req The coresponding {@link Request}
     * @param res The response to be checked
     * @return The result of the {@link Response}
     * @throws ErrorResponseException If instead of a result a error was received
     * @throws IdMismatchException If the ids do not match
     */
    protected JsonElement checkResponse(Request req, Response res) throws ErrorResponseException, IdMismatchException {
        if (res.isUnsuccessful())
            throw new ErrorResponseException(res.error().get());

        if (!req.id().get().equals(res.id()))
            throw new IdMismatchException(req.id().get(), res.id());

        return res.result().get();
    }

    /**
     * Gets the optional id.
     *
     * @return The id of this {@link Connection}
     */
    Optional<String> id() {
        return id;
    }

    /**
     * Gets the next request id.
     *
     * @return How many requests have been made
     */
    int requestsMade() {
        return requestId;
    }

}