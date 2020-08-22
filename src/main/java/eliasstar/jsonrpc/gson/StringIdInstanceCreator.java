package eliasstar.jsonrpc.gson;

import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;

import eliasstar.jsonrpc.objects.id.StringId;

public class StringIdInstanceCreator implements InstanceCreator<StringId> {

    private static StringIdInstanceCreator instance;

    private StringIdInstanceCreator() {
    }

    public static StringIdInstanceCreator instance() {
        if (instance == null) {
            instance = new StringIdInstanceCreator();
        }

        return instance;
    }

    public static Type type() {
        return StringId.class;
    }

    @Override
    public StringId createInstance(Type type) {
        return new StringId(null);
    }

}