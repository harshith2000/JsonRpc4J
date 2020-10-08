package eliasstar.jsonrpc;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import eliasstar.gson.OptionalTypeAdapterFactory;
import eliasstar.jsonrpc.gson.RpcTypeAdapterFactory;

/**
 * Use this Builder to construct a customized {@link Connection} instance.
 * <p>
 * To get a {@link Connection} with default settings, you can use {@link JsonRpc#connect(String)}.
 *
 * @author Elias*
 * @version 1.2.0
 * @since 0.1.0
 */
public final class ConnectionBuilder {

    /** The optional id of a new {@link Connection}. */
    private String id;

    /** The client used by a new {@link Connection}. */
    private HttpClient client;

    /** The request builder containing the uri. */
    private HttpRequest.Builder requestBuilder;

    /** The gson builder used for creating the gson instance used by a new {@link Connection}. */
    private GsonBuilder gsonBuilder;

    /**
     * Creates a {@link ConnectionBuilder} with the specified {@link HttpClient}.
     *
     * @param client The client used a new {@link Connection}
     */
    public ConnectionBuilder(HttpClient client) {
        this.client = Objects.requireNonNull(client);
    }

    /**
     * Creates a {@link ConnectionBuilder} with the specified {@link HttpClient} and url.
     *
     * @param client The client used a new {@link Connection}
     * @param url The url used to create a {@link HttpRequest.Builder}
     */
    public ConnectionBuilder(HttpClient client, String url) {
        this.client = Objects.requireNonNull(client);
        withUrl(url);
    }

    /**
     * Sets the id used by the created {@link Connection}.
     * <p>
     * If null is provided, a {@link Connection} without an id will be created.
     *
     * @param id The id of the new {@link Connection}
     * @return {@code this} to satisfy the builder pattern
     */
    public ConnectionBuilder withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Changes the client used for a new {@link Connection}.
     *
     * @param newClient The client used by a new {@link Connection}.
     * @return {@code this} to satisfy the builder pattern
     */
    public ConnectionBuilder changeClient(HttpClient newClient) {
        client = Objects.requireNonNull(newClient);
        return this;
    }

    /**
     * Sets the request builder used by a new {@link Connection}.
     * <p>
     * The builder should already contain an url. If this is not the case,
     * a url may be set afterwards using {@link #withUrl(URI)} or
     * {@link #withUrl(String)}.
     *
     * @param reqBuilder The  {@link HttpRequest.Builder}
     * @return {@code this} to satisfy the builder pattern
     */
    public ConnectionBuilder setRequestBuilder(HttpRequest.Builder reqBuilder) {
        requestBuilder = Objects.requireNonNull(reqBuilder).copy();
        return this;
    }

    /**
     * Sets the url of a new {@link Connection}.
     * <p>
     * If no request builder was set beforehand, a new one
     * with default settings is created.
     *
     * @param url The service url
     * @return {@code this} to satisfy the builder pattern
     */
    public ConnectionBuilder withUrl(URI url) {
        Objects.requireNonNull(url);

        if (requestBuilder != null)
            requestBuilder.uri(url);
        else
            requestBuilder = HttpRequest.newBuilder(url);

        return this;
    }

    /**
     * Sets the url of a new {@link Connection}.
     * <p>
     * If no request builder was set beforehand, a new one
     * with default settings is created.
     *
     * @param url The service url
     * @return {@code this} to satisfy the builder pattern
     */
    public ConnectionBuilder withUrl(String url) {
        return withUrl(URI.create(Objects.requireNonNull(url)));
    }

    /**
     * Sets the {@link GsonBuilder} to be used by a created {@link Connection}.
     * <p>
     * Creates a new {@link GsonBuilder} with the same settings as the {@link Gson} instance.
     *
     * @param gson The {@link Gson} from which a {@link GsonBuilder} is created
     * @return {@code this} to satisfy the builder pattern
     */
    public ConnectionBuilder setGson(Gson gson) {
        gsonBuilder = Objects.requireNonNull(gson).newBuilder();
        return this;
    }

    /**
     * Sets the gson builder used for creating the gson instance used by a new {@link Connection}.
     *
     * @param gson The {@link GsonBuilder} to be used for a created {@link Connection}
     * @return {@code this} to satisfy the builder pattern
     */
    public ConnectionBuilder setGson(GsonBuilder gson) {
        return setGson(Objects.requireNonNull(gson).create());
    }

    /**
     * Creates a {@link Connection}.
     * <p>
     * If no {@link Gson} or {@link GsonBuilder} was set, a new one is created with default settings.
     * <p>
     * The {@code "Content-Type"} header is set to {@code "application/json"} on the {@link HttpRequest.Builder}.
     * <p>
     * {@link OptionalTypeAdapterFactory} and {@link RpcTypeAdapterFactory} are registered to the {@link GsonBuilder} instance.
     * Before building {@link GsonBuilder#serializeNulls()} is called.
     *
     * @return A new {@link Connection} with the settings of this {@link ConnectionBuilder}
     * @throws IllegalStateException If neither a {@link HttpRequest.Builder} nor a {@link URI} was set
     */
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