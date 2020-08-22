package eliasstar.jsonrpc.gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import eliasstar.jsonrpc.objects.id.NumberId;
import eliasstar.jsonrpc.objects.id.Id;
import eliasstar.jsonrpc.objects.id.StringId;

public class IdTypeAdapter extends TypeAdapter<Id<?>> {

    private static IdTypeAdapter instance;

    private IdTypeAdapter() {
    }

    public static IdTypeAdapter instance() {
        if (instance == null) {
            instance = new IdTypeAdapter();
        }

        return instance;
    }

    public static Type type() {
        return Id.class;
    }

    @Override
    public void write(JsonWriter out, Id<?> value) throws IOException {
        value.write(out);
    }

    @Override
    public Id<?> read(JsonReader in) throws IOException {
        switch (in.peek()) {
        case STRING:
            return new StringId(in.nextString());

        case NUMBER:
            return new NumberId(new BigDecimal(in.nextString()));

        default:
            throw new RuntimeException("Expected string or number, not " + in.peek());
        }
    }

}