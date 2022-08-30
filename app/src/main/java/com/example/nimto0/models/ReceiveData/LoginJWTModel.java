package com.example.nimto0.models.ReceiveData;

import com.example.nimto0.models.CustOrderModel;
import com.example.nimto0.models.CustomerProfileModel;
import com.example.nimto0.models.PaginatedThemes;
import com.example.nimto0.models.Token;

import java.util.List;

public class LoginJWTModel {

    private int flag;
    private CustomerProfileModel my_profile;
    private List<CustOrderModel> my_orders;
    private PaginatedThemes all_themes;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
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

    public LoginJWTModel(int flag, CustomerProfileModel my_profile, List<CustOrderModel> my_orders, PaginatedThemes all_themes) {
        this.flag = flag;
        this.my_profile = my_profile;
        this.my_orders = my_orders;
        this.all_themes = all_themes;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "flag=" + flag +
                ", my_profile=" + my_profile +
                ", my_orders=" + my_orders +
                ", all_themes=" + all_themes +
                '}';
    }
}

/*

INPUT:
{
    "token":"c@dsfsfsd,sdklfdksfl;dsf.dfklsfksdlfklsdf.dfsdkf-dsfkl"
}

OUTPUT:

{
	"flag": 1,
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