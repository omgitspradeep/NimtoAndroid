package com.example.nimto0.models.ReceiveData;

public class OnlyMessageResponseModel {
    String detail;

    @Override
    public String toString() {
        return "OnlyMessageResponseModel{" +
                "detail='" + detail + '\'' +
                '}';
    }

    public OnlyMessageResponseModel(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

}
