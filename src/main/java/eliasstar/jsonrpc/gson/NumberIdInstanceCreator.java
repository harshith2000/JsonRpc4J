package eliasstar.jsonrpc.gson;

import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;

import eliasstar.jsonrpc.objects.id.NumberId;

public class NumberIdInstanceCreator implements InstanceCreator<NumberId> {

    private static NumberIdInstanceCreator instance;

    private NumberIdInstanceCreator() {
    }

    public static NumberIdInstanceCreator instance() {
        if (instance == null) {
            instance = new NumberIdInstanceCreator();
        }

        return instance;
    }

    public static Type type() {
        return NumberId.class;
    }

    @Override
    public NumberId createInstance(Type type) {
        return new NumberId(null);
    }

}