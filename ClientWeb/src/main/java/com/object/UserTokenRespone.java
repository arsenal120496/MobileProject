package com.object;

/**
 *
 */
public class UserTokenRespone {
    String username;
    String name;
    String tokenHeader;
    String tokenValue;

    public UserTokenRespone(String username, String name, String tokenHeader, String tokenValue) {
        this.username = username;
        this.name = name;
        this.tokenHeader = tokenHeader;
        this.tokenValue = tokenValue;
    }

    public UserTokenRespone() {
    }

    public String getEmail() {
        return username;
    }

    public void setEmail(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }
}
