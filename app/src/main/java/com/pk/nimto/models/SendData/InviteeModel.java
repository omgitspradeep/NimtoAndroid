package com.pk.nimto.models.SendData;

public class InviteeModel {
    int id, order;
    String name, gender, address, inviteStatus, invitee_message;
    boolean is_invitation_viewed;

    // For Updating/Retrieving Invitess
    public InviteeModel(int id, int order, String name, String gender, String address, String inviteStatus, String invitee_message, boolean is_invitation_viewed) {
        this.id = id;
        this.order = order;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.inviteStatus = inviteStatus;
        this.invitee_message = invitee_message;
        this.is_invitation_viewed = is_invitation_viewed;
    }

    // For Creating Invitess
    public InviteeModel(String name, int order, String gender, String address, String inviteStatus, String invitee_message) {
        this.order = order;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.inviteStatus = inviteStatus;
        this.invitee_message = invitee_message;
        this.is_invitation_viewed = false;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInviteStatus() {
        return inviteStatus;
    }

    public void setInviteStatus(String inviteStatus) {
        this.inviteStatus = inviteStatus;
    }

    public String getInvitee_message() {
        return invitee_message;
    }

    public void setInvitee_message(String invitee_message) {
        this.invitee_message = invitee_message;
    }

    public boolean isIs_invitation_viewed() {
        return is_invitation_viewed;
    }

    public void setIs_invitation_viewed(boolean is_invitation_viewed) {
        this.is_invitation_viewed = is_invitation_viewed;
    }

    @Override
    public String toString() {
        return "InviteeModelRU{" +
                ", order=" + order +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", inviteStatus='" + inviteStatus + '\'' +
                ", invitee_message='" + invitee_message + '\'' +
                ", is_invitation_viewed=" + is_invitation_viewed +
                '}';
    }


}

