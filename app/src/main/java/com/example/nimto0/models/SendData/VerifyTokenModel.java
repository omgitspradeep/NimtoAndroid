package com.example.nimto0.models.SendData;

public class VerifyTokenModel {
    private String token;

    public VerifyTokenModel(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "VerifyToken{" +
                "token='" + token + '\'' +
                '}';
    }
}
