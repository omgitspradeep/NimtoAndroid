package com.example.nimto0.models;

import java.util.ArrayList;
import java.util.List;

public class LoginModel {

    private int flag;
    private Token token;
    private CustomerProfileModel my_profile;
    private List<CustOrderModel> my_orders;
    private PaginatedThemes all_themes;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public CustomerProfileModel getMy_profile() {
        return my_profile;
    }

    public void setMy_profile(CustomerProfileModel my_profile) {
        this.my_profile = my_profile;
    }

    public List<CustOrderModel> getMy_orders() {
        return my_orders;
    }

    public void setMy_orders(List<CustOrderModel> my_orders) {
        this.my_orders = my_orders;
    }

    public PaginatedThemes getAll_themes() {
        return all_themes;
    }

    public void setAll_themes(PaginatedThemes all_themes) {
        this.all_themes = all_themes;
    }

    public LoginModel(int flag, Token token, CustomerProfileModel my_profile, List<CustOrderModel> my_orders, PaginatedThemes all_themes) {
        this.flag = flag;
        this.token = token;
        this.my_profile = my_profile;
        this.my_orders = my_orders;
        this.all_themes = all_themes;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "flag=" + flag +
                ", token=" + token +
                ", my_profile=" + my_profile +
                ", my_orders=" + my_orders +
                ", all_themes=" + all_themes +
                '}';
    }
}

/*

INPUT:
{
    "email":"c@gmail.com",
    "password":"1abc@def"
}

OUTPUT:

{
	"flag": 1,
	"token": {
		"refresh": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6MTY1OTY4MTExMSwiaWF0IjoxNjU5NTk0NzExLCJqdGkiOiI1ODRmYzBiZTA5NTI0ZWYzYjNhMmE3NDM3OTQ4ZTRlOCIsInVzZXJfaWQiOjF9.r6j2LcR2hkz8UJ8A-A6Fg6-68o0GpeMpGJE6lLN0ASc",
		"access": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjU5NTk1OTExLCJpYXQiOjE2NTk1OTQ3MTEsImp0aSI6ImRmMWFjY2Q4ODc5ZjRhMDY5YTE2M2MxODAyMmQ0MzdjIiwidXNlcl9pZCI6MX0.XSPl1w-gMaYP71_BYCSKXr_JPjVwncJJ_Ymjw2Kn1Tk"
	},
	"my_profile": {
		"id": 1,
		"email": "c@gmail.com",
		"first_name": "Allu",
		"last_name": "Aru",
		"phone_number": "+9888373837383",
		"country": "Nepal",
		"address": "Butwal"
	},
	"my_orders": [{
		"id": 26,
		"order_name": "My Order",
		"time_of_purchase": "2022-07-30T14:32:58.749574+05:45",
		"invitation_language": "en",
		"order_status": "active",
		"payment_method": "Khalti",
		"payment_completed": false,
		"selected_theme": 1,
		"plans": 1,
		"user": 1
	}, {
		"id": 27,
		"order_name": "Second Order",
		"time_of_purchase": "2022-07-30T14:37:46.858773+05:45",
		"invitation_language": "en",
		"order_status": "active",
		"payment_method": "Khalti",
		"payment_completed": false,
		"selected_theme": 2,
		"plans": 1,
		"user": 1
	}]
}
*/