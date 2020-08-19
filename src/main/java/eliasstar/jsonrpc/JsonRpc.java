package eliasstar.jsonrpc;

import java.net.CookieManager;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.time.Duration;

import com.google.gson.GsonBuilder;

public class JsonRpc {

    private static final HttpClient.Builder BUILDER = HttpClient.newBuilder().cookieHandler(new CookieManager()).connectTimeout(Duration.ofMinutes(1));
    private static HttpClient client;
    private static int connectionId = 0;

    public static Connection connect(String url) {
        return connect(url, Duration.ofMinutes(1));
    }

    public static Connection connect(String url, Duration requestTimeout) {
        if (client == null)
            client = BUILDER.build();

        return new Connection("con" + connectionId++, client, HttpRequest.newBuilder().uri(URI.create(url)).timeout(requestTimeout), new GsonBuilder());
    }

    public static void setConnectionTimeout(Duration connectTimeout) {
        client = BUILDER.connectTimeout(connectTimeout).build();
    }

    public static void setRedirectPolicy(Redirect policy) {
        client = BUILDER.followRedirects(policy).build();
    }

    public static void setHttpVersion(Version http) {
        client = BUILDER.version(http).build();
    }

    public static int connectionsMade() {
        return connectionId;
    }

}