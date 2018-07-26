package com.shiro.entity;

public class Users {
    private String username;
    private String password;
    private String passwrdSalt;

    public Users() {
    }

    public Users(String username, String password, String passwrdSalt) {
        this.username = username;
        this.password = password;
        this.passwrdSalt = passwrdSalt;

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

    public String getPasswrdSalt() {
        return passwrdSalt;
    }

    public void setPasswrdSalt(String passwrdSalt) {
        this.passwrdSalt = passwrdSalt;
    }
}

