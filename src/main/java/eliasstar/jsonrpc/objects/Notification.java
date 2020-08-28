package eliasstar.jsonrpc.objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import eliasstar.jsonrpc.objects.id.Id;
import eliasstar.jsonrpc.objects.parameter.ArrayParameter;
import eliasstar.jsonrpc.objects.parameter.ObjectParameter;
import eliasstar.jsonrpc.objects.parameter.Parameter;

public final class Notification extends Request {

    public Notification(String method) {
        super((Id<?>) null, method, (Parameter<?>) null);
    }

    public Notification(String method, JsonArray params) {
        super(null, method, new ArrayParameter(params));
    }

    public Notification(String method, JsonObject params) {
        super(null, method, new ObjectParameter(params));
    }

}