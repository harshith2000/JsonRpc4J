package eliasstar.webuntis;

import java.net.http.HttpClient;

public class Session {

    private static HttpClient client;

    private final String url;
    private final String clientName;

    private String sessionID;

    private boolean authenticated;

    public Session(String url, String clientName) {
        this.url = url;
        this.clientName = clientName;

        if (client == null) {
            client = HttpClient.newHttpClient();
        }
    }

    public Session(String url, String clientName, String username, char[] password) {
        this(url, clientName);
        authenticate(username, password);
    }

    public boolean authenticate(String username, char[] password) {
        return false;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public boolean logout() {
        return false;
    }
}