package eliasstar.jsonrpc.objects.id;

import java.io.IOException;

import com.google.gson.stream.JsonWriter;

public interface Id<T> {

    public T get();

    public void write(JsonWriter out) throws IOException;

}