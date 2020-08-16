package eliasstar.jsonrpc;

import com.google.gson.JsonObject;

public class Response {
    String jsonrpc;
    String id;
    JsonObject result;
    Error error;
}