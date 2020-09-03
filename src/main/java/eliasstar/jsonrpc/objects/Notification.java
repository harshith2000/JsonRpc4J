package eliasstar.jsonrpc.objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import eliasstar.jsonrpc.objects.id.Id;
import eliasstar.jsonrpc.objects.parameter.ArrayParameters;
import eliasstar.jsonrpc.objects.parameter.ObjectParameters;
import eliasstar.jsonrpc.objects.parameter.Parameters;

/**
 * Represents a JSON-RPC request without an id, otherwise known as a notification.
 *
 * @author Elias*
 * @version 0.1.0
 * @since 0.1.0
 * @see <a href="https://www.jsonrpc.org/specification#notification">JSON-RPC Specification</a>
 */
public final class Notification extends Request {

    /** Used indirectly by GSON */
    @SuppressWarnings("unused")
    private Notification() {
        this("");
    }

    /**
    * Creates a {@link Notification} without parameters.
    *
    * @param method The non-null method you want to invoke
    */
    public Notification(String method) {
        super((Id<?>) null, method, (Parameters<?>) null);
    }

    /**
    * Creates a {@link Notification} with parameters in array format.
    * <p>
    * If params is null a NullPointerException will be thrown.
    * If you want to create a notification without parameters, you should use {@link #Notification(String)}
    *
    * @param method The non-null method you want to invoke
    * @param params The parameters provided as {@link JsonArray}
    */
    public Notification(String method, JsonArray params) {
        super(null, method, new ArrayParameters(params));
    }

    /**
    * Creates a {@link Notification} with parameters in object format.
    * <p>
    * If params is null a NullPointerException will be thrown.
    * If you want to create a notification without parameters, you should use {@link #Notification(String)}
    *
    * @param method The non-null method you want to invoke
    * @param params The parameters provided as {@link JsonObject}
    */
    public Notification(String method, JsonObject params) {
        super(null, method, new ObjectParameters(params));
    }

    /**
    * This method returns {@code true} if the argument is a {@link Notification} or {@link Request} without an id
    * and all properties are equal, otherwise returns {@code false}.
    * <p>
    * {@inheritDoc}
    *
    * @return Whether {@code this} is the same as the {@link Object} argument
    */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Notification other)
            return this == other || jsonrpc().equals(other.jsonrpc())
                    && id().equals(other.id())
                    && method().equals(other.method())
                    && params().equals(other.params());

        return super.equals(obj);
    }

    /**
    * The returned {@link String} is equal to the value of:
    * {@code "Notification@" + Integer.toHexString(hashCode()) + " " + contentAsJsonString()}
    * where {@code contentAsJsonString()} returns a JSON-like {@link String} of this
    * {@link Notification}.
    *
    * @return A {@link String} representation of this {@link Notification}
    */
    @Override
    public String toString() {
        return "Notification@" + Integer.toHexString(hashCode()) + " " + contentAsJsonString();
    }

}