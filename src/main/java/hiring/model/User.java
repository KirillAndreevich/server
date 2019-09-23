package hiring.model;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String login;
    private String password;
    private String email;
    private boolean online;

    public User(String login, String password, String email, boolean online) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.online = online;
        //this.token. создается
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOnline() {
        return this.online;
    }

    public void setOnline(){
        this.online = true;
    }
    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void exit(){
        this.online = false;
    }

}
