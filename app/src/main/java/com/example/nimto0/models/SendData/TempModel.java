package com.example.nimto0.models.SendData;

public class TempModel {
    private String detail;
    private String code;

    public TempModel(String detail, String code) {
        this.detail = detail;
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "TempModel{" +
                "detail='" + detail + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
