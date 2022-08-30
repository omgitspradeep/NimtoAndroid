package com.example.nimto0.models;


public class CustOrderModel {
    private int id;
    private String order_name;
    private String time_of_purchase;
    private String invitation_language;
    private String order_status;
    private String payment_method;
    private boolean payment_completed;
    private int selected_theme;
    private int plans;
    private int user;



    // When creating the order data (C)
    public CustOrderModel(String order_name, String invitation_language, String order_status, String payment_method, int selected_theme, int plans, int user) {
        this.order_name = order_name;
        this.invitation_language = invitation_language;
        this.order_status = order_status;
        this.payment_method = payment_method;
        this.selected_theme = selected_theme;
        this.plans = plans;
        this.user = user;
    }

    // When getting the Order data (R)
    public CustOrderModel(int id, String order_name, String time_of_purchase, String invitation_language, String order_status, String payment_method, boolean payment_completed, int selected_theme, int plans, int user) {
        this.id = id;
        this.order_name = order_name;
        this.time_of_purchase = time_of_purchase;
        this.invitation_language = invitation_language;
        this.order_status = order_status;
        this.payment_method = payment_method;
        this.payment_completed = payment_completed;
        this.selected_theme = selected_theme;
        this.plans = plans;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getTime_of_purchase() {
        return time_of_purchase;
    }

    public void setTime_of_purchase(String time_of_purchase) {
        this.time_of_purchase = time_of_purchase;
    }

    public String getInvitation_language() {
        return invitation_language;
    }

    public void setInvitation_language(String invitation_language) {
        this.invitation_language = invitation_language;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public boolean isPayment_completed() {
        return payment_completed;
    }

    public void setPayment_completed(boolean payment_completed) {
        this.payment_completed = payment_completed;
    }

    public int getSelected_theme() {
        return selected_theme;
    }

    public void setSelected_theme(int selected_theme) {
        this.selected_theme = selected_theme;
    }

    public int getPlans() {
        return plans;
    }

    public void setPlans(int plans) {
        this.plans = plans;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CustOrderModel{" +
                "order_name='" + order_name + '\'' +
                ", invitation_language='" + invitation_language + '\'' +
                ", order_status='" + order_status + '\'' +
                ", payment_method='" + payment_method + '\'' +
                ", selected_theme=" + selected_theme +
                ", plans=" + plans +
                ", user=" + user +
                '}';
    }
}


    /*


    {
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
	}

     */