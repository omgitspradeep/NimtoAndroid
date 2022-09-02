package com.pk.nimto.models.marriagedata;

public class Plans {
    int id;
    String plans;
    int no_of_days, no_of_invitees,plan_price;

    public Plans(int id, String plans, int no_of_days, int no_of_invitees, int plan_price) {
        this.id = id;
        this.plans = plans;
        this.no_of_days = no_of_days;
        this.no_of_invitees = no_of_invitees;
        this.plan_price = plan_price;
    }

    public int getId() {
        return id;
    }

    public String getPlans() {
        return plans;
    }

    public void setPlans(String plans) {
        this.plans = plans;
    }

    public int getNo_of_days() {
        return no_of_days;
    }

    public void setNo_of_days(int no_of_days) {
        this.no_of_days = no_of_days;
    }

    public int getNo_of_invitees() {
        return no_of_invitees;
    }

    public void setNo_of_invitees(int no_of_invitees) {
        this.no_of_invitees = no_of_invitees;
    }

    public int getPlan_price() {
        return plan_price;
    }

    public void setPlan_price(int plan_price) {
        this.plan_price = plan_price;
    }

    @Override
    public String toString() {
        return " Plan='" + plans + '\'' + "\n" +
                " Total Days=" + no_of_days + "\n" +
                " Invitees No.=" + no_of_invitees + "\n" +
                " Price=" + plan_price + "\n" +
                "<------------------->"+ "\n\n"
                ;
    }
}
