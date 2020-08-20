package eliasstar.jsonrpc.mocks;

import java.net.URI;
import java.net.http.HttpClient.Version;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.net.ssl.SSLSession;

public final class MockHttpResponse implements HttpResponse<String> {

    private final HttpRequest req;
    private final boolean shouldSucceed;
    private final String id;

    public MockHttpResponse(HttpRequest req, boolean shouldSucceed, String id) {
        this.req = req;
        this.shouldSucceed = shouldSucceed;
        this.id = id;
    }

    @Override
    public int statusCode() {
        return 200;
    }

    @Override
    public HttpRequest request() {
        return req;
    }

    @Override
    public Optional<HttpResponse<String>> previousResponse() {
        return Optional.empty();
    }

    @Override
    public HttpHeaders headers() {
        var map = new HashMap<String, List<String>>();

        map.put("Content-Type", Arrays.asList("application/json"));

        return HttpHeaders.of(map, null);
    }

    @Override
    public String body() {
        return shouldSucceed ? "{\"jsonrpc\": \"2.0\",\"id\": \"" + id + "\",\"result\": \"test\"}" : "{\"jsonrpc\": \"2.0\",\"id\": \"" + id + "\",\"error\": {\"code\": 42069,\"message\": \"test\"}}";
    }

    @Override
    public Optional<SSLSession> sslSession() {
        return Optional.empty();
    }

    @Override
    public URI uri() {
        return req.uri();
    }

    @Override
    public Version version() {
        return req.version().isPresent() ? req.version().get() : null;
    }

}