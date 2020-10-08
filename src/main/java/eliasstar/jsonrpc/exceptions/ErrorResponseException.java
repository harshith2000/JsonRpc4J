package eliasstar.jsonrpc.exceptions;

import java.util.Optional;

import com.google.gson.JsonElement;

import eliasstar.jsonrpc.objects.Error;

/**
 * Indicates that a {@link eliasstar.jsonrpc.objects.Response Response} with an {@link Error} was received.
 *
 * @author Elias*
 * @version 1.2.0
 * @since 0.1.0
 */
public final class ErrorResponseException extends RpcException {

    private static final long serialVersionUID = 6883031150212639037L;

    /** Indicates the error type that occurred. */
    private final long code;

    /** Additional information about the error. */
    private final Optional<JsonElement> data;

    /**
     * Creates a {@link ErrorResponseException} from an {@link Error}.
     *
     * @param error The error received
     */
    public ErrorResponseException(Error error) {
        this(error.code(), error.message(), error.data().orElse(null));
    }

    /**
     * Creates a {@link ErrorResponseException} without additional data.
     *
     * @param code The error type that occurred
     * @param message A short description of the error
     */
    public ErrorResponseException(long code, String message) {
        this(code, message, null);
    }

    /**
     * Creates a {@link ErrorResponseException} with additional data.
     *
     * @param code The error type that occurred
     * @param message A short description of the error
     * @param data Additional information about the error
     */
    public ErrorResponseException(long code, String message, JsonElement data) {
        super(message);
        this.code = code;
        this.data = Optional.ofNullable(data);
    }

    /**
     * Getter for error code.
     *
     * @return A number that indicates the error type that occurred
     */
    public long getErrorCode() {
        return code;
    }

    /**
     * Getter for error data.
     *
     * @return A value that contains additional information about the error
     */
    public Optional<JsonElement> getErrorData() {
        return data;
    }

}