package hiring.model;

import java.util.UUID;

public class TokenPassword {
    private UUID token;
    private String password;

    public TokenPassword(UUID token, String password){
        this.password = password;
        this.token = token;
    }

    public String getPassword() {
        return this.password;
    }

    public UUID getToken() {
        return this.token;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
