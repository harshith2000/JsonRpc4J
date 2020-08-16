package eliasstar.jsonrpc;

import com.google.gson.JsonElement;

public class Error {
    int code;
    String message;
    JsonElement data;
}