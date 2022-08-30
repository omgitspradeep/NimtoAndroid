package com.example.nimto0.models.SendData;

public class RefreshTokenModel {
    private String refresh;

    public RefreshTokenModel(String refresh) {
        this.refresh = refresh;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    @Override
    public String toString() {
        return "RefreshTokenModel{" +
                "refresh='" + refresh + '\'' +
                '}';
    }
}
