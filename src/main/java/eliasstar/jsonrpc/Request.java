package eliasstar.jsonrpc;

import com.google.gson.JsonObject;

public class Request {

    final String jsonrpc = "2.0";
    String id;
    String method;
    JsonObject params;
}