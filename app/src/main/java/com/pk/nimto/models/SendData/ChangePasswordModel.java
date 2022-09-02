package com.pk.nimto.models.SendData;

public class ChangePasswordModel {
    String password, password2;

    public ChangePasswordModel(String password, String password2) {
        this.password = password;
        this.password2 = password2;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @Override
    public String toString() {
        return "ChangePasswordModel{" +
                "password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                '}';
    }
}
