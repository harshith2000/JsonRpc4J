package eliasstar.jsonrpc.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import eliasstar.jsonrpc.objects.id.Id;
import eliasstar.jsonrpc.objects.parameter.Parameters;

/**
 * Gson {@link TypeAdapterFactory} for {@link IdTypeAdapter} and
 * {@link ParameterTypeAdapter}.
 *
 * @author Elias*
 * @since 0.1.0
 */
public final class RpcTypeAdapterFactory implements TypeAdapterFactory {

    /** The singleton instance. */
    private static RpcTypeAdapterFactory instance;

    /** Used once for singleton. */
    private RpcTypeAdapterFactory() {
    }

    /**
     * Getter for singleton instance.
     * <p>
     * The instance is lazyly instanciated, which means the the singleton instance
     * is created when this method is invoked for the first time. Any consecutive
     * calls return the instance.
     *
     * @return The only instance of {@link RpcTypeAdapterFactory}
     */
    public static RpcTypeAdapterFactory instance() {
        if (instance == null)
            instance = new RpcTypeAdapterFactory();

        return instance;
    }

    /**
     * Returns a {@link TypeAdapter} based on the following rules:
     * <p>
     * The {@link IdTypeAdapter} singleton instance if the type implements
     * {@link Id}.
     * <p>
     * The {@link ParameterTypeAdapter} singleton instance if the type implements
     * {@link Parameters}.
     * <p>
     *
     * @param <T>  The type for which a {@link TypeAdapter} is needed
     * @param gson The {@link Gson} instance to which this adapter is registered
     * @param type A {@link TypeToken} which specifies the type the
     *             {@link TypeAdapter} should handle
     * @return A {@link TypeAdapter} which is either a {@link IdTypeAdapter},
     *         {@link ParameterTypeAdapter} or null
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (Id.class.isAssignableFrom(type.getRawType()))
            return (TypeAdapter<T>) IdTypeAdapter.instance();

        if (Parameters.class.isAssignableFrom(type.getRawType()))
            return (TypeAdapter<T>) ParameterTypeAdapter.instance();

        return null;
    }

}