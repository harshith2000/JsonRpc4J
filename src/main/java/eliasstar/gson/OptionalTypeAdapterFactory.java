package eliasstar.gson;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

/**
 * Gson {@link TypeAdapterFactory} for {@link OptionalTypeAdapter}.
 * <p>
 * Creates a {@link OptionalTypeAdapter} instance for each Optional parameter
 * type.
 *
 * @author Elias*
 * @since 0.1.0
 */
public final class OptionalTypeAdapterFactory implements TypeAdapterFactory {

    /** The singleton instance. */
    private static OptionalTypeAdapterFactory instance;

    /** Used once for singleton. */
    private OptionalTypeAdapterFactory() {
    }

    /**
     * Getter for singleton instance.
     * <p>
     * The instance is lazyly instanciated, which means the the singleton instance
     * is created when this method is invoked for the first time. Any consecutive
     * calls return the instance.
     *
     * @return The only instance of {@link OptionalTypeAdapterFactory}
     */
    public static OptionalTypeAdapterFactory instance() {
        if (instance == null)
            instance = new OptionalTypeAdapterFactory();

        return instance;
    }

    /**
     * Creates a {@link OptionalTypeAdapter}, or null if {@code type} is not
     * representing a {@link Optional}.
     *
     * @param <T>  The type for which a {@link TypeAdapter} is needed
     * @param gson The {@link Gson} instance to which this adapter is registered
     * @param type A {@link TypeToken} which specifies the type the
     *             {@link TypeAdapter} should handle
     * @return A {@link TypeAdapter} which is either a {@link OptionalTypeAdapter}
     *         or null
     */
    @Override
    @SuppressWarnings("unchecked") // Checks if T is not Optional and returns early
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (type.getRawType() == Optional.class && type.getType() instanceof ParameterizedType) {
            var optionalType = (ParameterizedType) type.getType();

            return (TypeAdapter<T>) new OptionalTypeAdapter(gson, optionalType.getActualTypeArguments()[0]);
        }

        return null;
    }

}