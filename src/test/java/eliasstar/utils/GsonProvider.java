package eliasstar.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import eliasstar.gson.OptionalTypeAdapterFactory;
import eliasstar.jsonrpc.gson.RpcTypeAdapterFactory;

public final class GsonProvider {

    private static Gson gson;
    private static Gson gsonWithNulls;

    public static Gson gson() {
        if (gson == null)
            gson = new GsonBuilder().registerTypeAdapterFactory(OptionalTypeAdapterFactory.instance()).registerTypeAdapterFactory(RpcTypeAdapterFactory.instance()).create();

        return gson;
    }

    public static Gson gsonWithNulls() {
        if (gsonWithNulls == null)
            gsonWithNulls = new GsonBuilder().registerTypeAdapterFactory(OptionalTypeAdapterFactory.instance()).registerTypeAdapterFactory(RpcTypeAdapterFactory.instance()).serializeNulls().create();

        return gsonWithNulls;
    }

}