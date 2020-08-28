package eliasstar.jsonrpc.exceptions;

import java.util.Optional;

import com.google.gson.JsonElement;

import eliasstar.jsonrpc.objects.Error;

public final class RpcErrorException extends RpcException {

    private static final long serialVersionUID = 6883031150212639037L;

    private final long code;
    private final Optional<JsonElement> data;

    public RpcErrorException(Error error) {
        this(error.code(), error.message(), error.data().orElse(null));
    }

    public RpcErrorException(long code, String message) {
        this(code, message, null);
    }

    public RpcErrorException(long code, String message, JsonElement data) {
        super(message);
        this.code = code;
        this.data = Optional.ofNullable(data);
    }

    public long getErrorCode() {
        return code;
    }

    public Optional<JsonElement> getErrorData() {
        return data;
    }

}