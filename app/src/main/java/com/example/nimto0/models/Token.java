package com.example.nimto0.models;

public class Token {
    private String access, refresh;

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public Token(String access, String refresh) {
        this.access = access;
        this.refresh = refresh;
    }
}