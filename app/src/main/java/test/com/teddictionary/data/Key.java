package test.com.teddictionary.data;

import java.util.UUID;

/**
 * Created by teddylin on 14/12/2017.
 */

public class Key {
    String scope = "write_set";
    String client_id = "ZuyJXZaNnv";
    String response_type = "code";
    String state = UUID.randomUUID().toString();
    String redirect_uri = "teddictionary://callback";
    String secret = "BwExTYqXs4sZfCMx4A3Q84";

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getResponse_type() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
