package eliasstar.jsonrpc.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import eliasstar.jsonrpc.objects.id.Id;
import eliasstar.jsonrpc.objects.parameter.Parameter;

public final class RpcTypeAdapterFactory implements TypeAdapterFactory {

    private static RpcTypeAdapterFactory instance;

    private RpcTypeAdapterFactory() {
    }

    public static RpcTypeAdapterFactory instance() {
        if (instance == null)
            instance = new RpcTypeAdapterFactory();

        return instance;
    }

    @Override
    @SuppressWarnings("unchecked") // Checks if T is not Optional and returns early
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (Id.class.isAssignableFrom(type.getRawType()))
            return (TypeAdapter<T>) IdTypeAdapter.instance();

        if (Parameter.class.isAssignableFrom(type.getRawType()))
            return (TypeAdapter<T>) ParameterTypeAdapter.instance();

        return null;
    }

}