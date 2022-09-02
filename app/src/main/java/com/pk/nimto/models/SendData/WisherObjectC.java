package com.pk.nimto.models.SendData;

public class WisherObjectC {
    int order, invitee;
    String wishes;

    public WisherObjectC(int order, int invitee, String wishes) {
        this.order = order;
        this.invitee = invitee;
        this.wishes = wishes;
    }



    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getInvitee() {
        return invitee;
    }

    public void setInvitee(int invitee) {
        this.invitee = invitee;
    }

    public String getWishes() {
        return wishes;
    }

    public void setWishes(String wishes) {
        this.wishes = wishes;
    }

}
