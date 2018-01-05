package test.com.teddictionary.data;

/**
 * Created by teddylin on 15/12/2017.
 */

public class QuizletToken {

    /**
     * access_token : 46a54395f3d1108feca56c7f6ca8dd3d
     * token_type : bearer
     * expires_in : 3600
     * scope : read
     * user_id : yuhsiung_lin
     */

    private String access_token;
    private String token_type;
    private int expires_in;
    private String scope;
    private String user_id;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAccessToken() {
        return "Bearer " + getAccess_token();
    }
}
