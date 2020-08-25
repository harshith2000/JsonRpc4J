package eliasstar.jsonrpc.objects.parameter;

import java.io.IOException;
import java.util.function.Supplier;

import com.google.gson.stream.JsonWriter;

public interface Parameter<T> extends Supplier<T> {

    public void write(JsonWriter out) throws IOException;

}