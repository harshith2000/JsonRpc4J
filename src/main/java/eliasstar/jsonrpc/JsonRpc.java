/*
 * Copyright (C) 2020-2021 Elias*
 *
 * This file is part of JsonRpc4J.
 *
 * JsonRpc4J is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or any later version.
 *
 * JsonRpc4J is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JsonRpc4J. If not, see <https://www.gnu.org/licenses/>.
 */

package eliasstar.jsonrpc;

import java.net.CookieManager;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.time.Duration;

import com.google.gson.Gson;

/**
 * This facade can be used to get started quickly and easily with sending RPC
 * requests.
 *
 * @author Elias*
 * @since 0.1.0
 */
public final class JsonRpc {

    /** The id prefix a new {@link Connection} will have. */
    static final String CONNECTION_PREFIX = "con";

    /** The builder used for building the client. */
    private static final HttpClient.Builder BUILDER = HttpClient.newBuilder().cookieHandler(new CookieManager()).connectTimeout(Duration.ofMinutes(1));

    /** The client used for connection to a service. */
    private static HttpClient client;

    /** The id a new {@link Connection} will have. */
    private static int connectionId = 0;

    /**
     * Connects to a RPC service.
     * <p>
     * Creates a {@link Connection} with default settings.
     * <p>
     * Requests will time out after a minute.
     *
     * @param url The url of the RPC service
     * @return A {@link Connection} object for sending requests
     */
    public static Connection connect(String url) {
        return connect(url, Duration.ofMinutes(1));
    }

    /**
     * Connects to a RPC service.
     * <p>
     * Creates a {@link Connection} with default settings.
     *
     * @param url The url of the RPC service
     * @param requestTimeout The {@link Duration} until a request will timeout
     * @return A {@link Connection} object for sending requests
     */
    public static Connection connect(String url, Duration requestTimeout) {
        if (client == null)
            client = BUILDER.build();

        return new ConnectionBuilder(client, url).withId(CONNECTION_PREFIX + connectionId++).setGson(new Gson()).build();
    }

    /**
     * Sets the timeout for a new connection attempt.
     *
     * @param connectTimeout The {@link Duration} until a connection will timeout
     */
    public static void setConnectionTimeout(Duration connectTimeout) {
        client = BUILDER.connectTimeout(connectTimeout).build();
    }

    /**
     * Sets the redirect policy used for requests.
     *
     * @param policy The Policy
     */
    public static void setRedirectPolicy(Redirect policy) {
        client = BUILDER.followRedirects(policy).build();
    }

    /**
     * Sets the HTTP version used by the client.
     *
     * @param http The version
     */
    public static void setHttpVersion(Version http) {
        client = BUILDER.version(http).build();
    }

    /**
     * Gets how many connections have been made.
     *
     * @return The next connection id
     */
    static int connectionsMade() {
        return connectionId;
    }

}
