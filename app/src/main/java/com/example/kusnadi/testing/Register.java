package com.example.kusnadi.testing;

/**
 * Created by Kusnadi on 5/26/2016.
 */
public class Register {
    private long user_id;
    private String username;
    private String password;

    public Register() {

    }
    public long getUser_id() {
        return user_id;
    }
    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Username : " + username + " Password : " + password;
    }
}
