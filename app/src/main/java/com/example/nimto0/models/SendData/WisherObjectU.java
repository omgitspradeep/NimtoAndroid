package com.example.nimto0.models.SendData;

public class WisherObjectU {
    int id, order, invitee;
    String wishes, posted;

    public WisherObjectU(int id, int order, int invitee, String wishes, String posted) {
        this.id = id;
        this.order = order;
        this.invitee = invitee;
        this.wishes = wishes;
        this.posted = posted;
    }

    public WisherObjectU(String wishes) {
        this.wishes = wishes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPosted() {
        return posted;
    }

    public void setPosted(String posted) {
        this.posted = posted;
    }

}
