package eliasstar.jsonrpc.objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import eliasstar.jsonrpc.objects.id.Id;
import eliasstar.jsonrpc.objects.parameter.ArrayParameter;
import eliasstar.jsonrpc.objects.parameter.ObjectParameter;
import eliasstar.jsonrpc.objects.parameter.Parameter;

public final class Notification extends Request {

    @SuppressWarnings("unused") // Used indirectly by GSON
    private Notification() {
        this("");
    }

    public Notification(String method) {
        super((Id<?>) null, method, (Parameter<?>) null);
    }

    public Notification(String method, JsonArray params) {
        super(null, method, new ArrayParameter(params));
    }

    public Notification(String method, JsonObject params) {
        super(null, method, new ObjectParameter(params));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Notification other)
            return this == other || jsonrpc().equals(other.jsonrpc())
                    && id().equals(other.id())
                    && method().equals(other.method())
                    && params().equals(other.params());

        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Notification@" + Integer.toHexString(hashCode()) + " " + contentAsJsonString();
    }

}