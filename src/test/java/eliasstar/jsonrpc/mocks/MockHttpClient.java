package eliasstar.jsonrpc.mocks;

import java.io.IOException;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.PushPromiseHandler;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;

public final class MockHttpClient extends HttpClient {

    private final boolean shouldSucceed;

    public MockHttpClient(boolean shouldSucceed) {
        this.shouldSucceed = shouldSucceed;
    }

    @Override
    public <T> HttpResponse<T> send(HttpRequest request, BodyHandler<T> responseBodyHandler) throws IOException, InterruptedException {

        return null;
    }

    // #region unimplemented methods

    @Override
    public <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, BodyHandler<T> responseBodyHandler) {
        return null;
    }

    @Override
    public <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, BodyHandler<T> responseBodyHandler, PushPromiseHandler<T> pushPromiseHandler) {
        return null;
    }

    @Override
    public Optional<CookieHandler> cookieHandler() {
        return null;
    }

    @Override
    public Optional<Duration> connectTimeout() {
        return null;
    }

    @Override
    public Redirect followRedirects() {
        return null;
    }

    @Override
    public Optional<ProxySelector> proxy() {
        return null;
    }

    @Override
    public SSLContext sslContext() {
        return null;
    }

    @Override
    public SSLParameters sslParameters() {
        return null;
    }

    @Override
    public Optional<Authenticator> authenticator() {
        return null;
    }

    @Override
    public Version version() {
        return null;
    }

    @Override
    public Optional<Executor> executor() {
        return null;
    }
    // #endregion

}