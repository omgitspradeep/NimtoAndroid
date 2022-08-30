package com.example.nimto0.models;

public class SimpleResponseModel {
    String msg;
    int flag;

    public SimpleResponseModel(String msg, int flag) {
        this.msg = msg;
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
