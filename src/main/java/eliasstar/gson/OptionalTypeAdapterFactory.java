package eliasstar.gson;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

public final class OptionalTypeAdapterFactory implements TypeAdapterFactory {

    private static OptionalTypeAdapterFactory instance;

    private OptionalTypeAdapterFactory() {
    }

    public static OptionalTypeAdapterFactory instance() {
        if (instance == null)
            instance = new OptionalTypeAdapterFactory();

        return instance;
    }

    @Override
    @SuppressWarnings("unchecked") // Checks if T is not Optional and returns early
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (type.getRawType() == Optional.class && type.getType() instanceof ParameterizedType optionalType)
            return (TypeAdapter<T>) new OptionalTypeAdapter(gson, optionalType.getActualTypeArguments()[0]);

        return null;
    }

}