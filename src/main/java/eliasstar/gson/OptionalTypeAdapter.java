package eliasstar.gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

final class OptionalTypeAdapter extends TypeAdapter<Optional<?>> {

    private final Gson gson;
    private final Type typeOfOptional;

    OptionalTypeAdapter(Gson gson, Type typeOfOptional) {
        this.gson = gson;
        this.typeOfOptional = typeOfOptional;
    }

    @Override
    public void write(JsonWriter out, Optional<?> value) throws IOException {
        var serialize = out.getSerializeNulls();
        out.setSerializeNulls(false);

        var json = value.map(x -> gson.toJsonTree(x));
        if (json.isPresent())
            Streams.write(json.get(), out);
        else
            out.nullValue();

        out.setSerializeNulls(serialize);
    }

    @Override
    public Optional<?> read(JsonReader in) throws IOException {
        return Optional.ofNullable(gson.fromJson(in, typeOfOptional));
    }

}