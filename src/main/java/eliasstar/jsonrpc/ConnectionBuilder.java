package eliasstar.jsonrpc;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import eliasstar.gson.OptionalTypeAdapterFactory;
import eliasstar.jsonrpc.gson.RpcTypeAdapterFactory;

public class ConnectionBuilder {

    private String id;
    private HttpClient client;
    private HttpRequest.Builder requestBuilder;
    private GsonBuilder gsonBuilder;

    public ConnectionBuilder(HttpClient client) {
        this.client = Objects.requireNonNull(client);
    }

    public ConnectionBuilder(HttpClient client, String url) {
        this.client = Objects.requireNonNull(client);
        withUrl(url);
    }

    public ConnectionBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public ConnectionBuilder changeClient(HttpClient newClient) {
        client = Objects.requireNonNull(newClient);
        return this;
    }

    public ConnectionBuilder setRequestBuilder(HttpRequest.Builder reqBuilder) {
        requestBuilder = Objects.requireNonNull(reqBuilder).copy();
        return this;
    }

    public ConnectionBuilder withUrl(URI url) {
        Objects.requireNonNull(url);

        if (requestBuilder != null)
            requestBuilder.uri(url);
        else
            requestBuilder = HttpRequest.newBuilder(url);

        return this;
    }

    public ConnectionBuilder withUrl(String url) {
        return withUrl(URI.create(Objects.requireNonNull(url)));
    }

    public ConnectionBuilder setGson(Gson gson) {
        gsonBuilder = Objects.requireNonNull(gson).newBuilder();
        return this;
    }

    public ConnectionBuilder setGson(GsonBuilder gson) {
        return setGson(Objects.requireNonNull(gson).create());
    }

    public Connection build() {
        if (requestBuilder == null)
            throw new IllegalStateException("uri is null");
        else
            requestBuilder.build();

        requestBuilder.setHeader("Content-Type", "application/json");

        if (gsonBuilder == null)
            gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapterFactory(OptionalTypeAdapterFactory.instance());
        gsonBuilder.registerTypeAdapterFactory(RpcTypeAdapterFactory.instance());
        gsonBuilder.serializeNulls();

        return new Connection(id, client, requestBuilder, gsonBuilder.create());
    }

}