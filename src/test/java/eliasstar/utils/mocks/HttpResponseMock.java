package eliasstar.utils.mocks;

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

public final class HttpResponseMock<T> implements HttpResponse<T>, HttpResponse.ResponseInfo {

    private final HttpRequest req;
    private T body;

    HttpResponseMock(HttpRequest req) {
        this.req = req;
    }

    @Override
    public int statusCode() {
        return 200;
    }

    @Override
    public HttpHeaders headers() {
        var map = new HashMap<String, List<String>>();

        map.put("Content-Type", Arrays.asList("application/json"));

        return HttpHeaders.of(map, (a, b) -> true);
    }

    @Override
    public Version version() {
        return req.version().isPresent() ? req.version().get() : null;
    }

    void body(T body) {
        this.body = body;
    }

    @Override
    public T body() {
        return body;
    }

    @Override
    public HttpRequest request() {
        return req;
    }

    @Override
    public URI uri() {
        return req.uri();
    }

    @Override
    public Optional<SSLSession> sslSession() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpResponse<T>> previousResponse() {
        return Optional.empty();
    }

}