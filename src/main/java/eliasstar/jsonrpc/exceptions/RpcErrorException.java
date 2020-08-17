package eliasstar.jsonrpc.exceptions;

import com.google.gson.JsonElement;

import eliasstar.jsonrpc.objects.Response;

public final class RpcErrorException extends RpcException {

    private static final long serialVersionUID = -6883031150212639037L;

    private final int code;
    private final JsonElement data;

    public RpcErrorException(int errorCode, String message) {
        this(errorCode, message, (JsonElement) null);
    }

    public RpcErrorException(Response.Error error) {
        this(error.code(), error.message(), error.data());
    }

    public RpcErrorException(int errorCode, String message, JsonElement data) {
        super(message);

        this.code = errorCode;
        this.data = data;
    }

    public RpcErrorException(int errorCode, String message, Throwable cause) {
        this(errorCode, message, null, cause);
    }

    public RpcErrorException(Response.Error error, Throwable cause) {
        this(error.code(), error.message(), error.data(), cause);
    }

    public RpcErrorException(int errorCode, String message, JsonElement data, Throwable cause) {
        super(message, cause);

        this.code = errorCode;
        this.data = data;
    }

    public int getErrorCode() {
        return code;
    }

    public JsonElement getErrorData() {
        return data;
    }

}