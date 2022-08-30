package com.example.nimto0.models;

import com.example.nimto0.models.SendData.WisherObjectU;
import com.example.nimto0.models.SendData.InviteeModel;

public class CustomizedInviteeModel {
    int id, order, wishId;
    String name,gender, address,inviteStatus,invitee_message,url, wish, wishPosted;
    boolean is_invitation_viewed;

    public String getWishPosted() {
        return wishPosted;
    }

    public void setWishPosted(String wishPosted) {
        this.wishPosted = wishPosted;
    }

    public CustomizedInviteeModel(int id, int order, int wishId, String name, String gender, String address, String inviteStatus, String invitee_message, String url, String wish, String wishPosted, boolean is_invitation_viewed) {
        this.id = id;
        this.order = order;
        this.wishId = wishId;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.inviteStatus = inviteStatus;
        this.invitee_message = invitee_message;
        this.url = url;
        this.wish = wish;
        this.wishPosted = wishPosted;
        this.is_invitation_viewed = is_invitation_viewed;
    }



    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public String getStatus() {
        if(inviteStatus.equals("Wedding")){
            return "W";
        }else{
            return "R";
        }
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

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public String getInvitee_message() {
        return invitee_message;
    }

    public void setInvitee_message(String invitee_message) {
        this.invitee_message = invitee_message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isIs_invitation_viewed() {
        return is_invitation_viewed;
    }


    public InviteeModel getInviteeObjectForUpdate(){
        return new InviteeModel(this.id,this.order, this.name, this.gender, this.address, this.inviteStatus, this.invitee_message, this.is_invitation_viewed);
    }

    public WisherObjectU getWisherObjectForUpdate(){
        return new WisherObjectU(this.wishId, this.order, this.id, this.wish, this.wishPosted);
    }


    /*



  {
        "id": 1,
        "order": 2,
        "name": "Dr. Hari Bashyal",
        "gender": "Mr. ",
        "address": "Dhankuta-14, Nepal",
        "inviteStatus": "Wedding",
        "invitee_message": "Behe ma aunus hai mitra",
        "is_invitation_viewed": false,
        "url": "muktinath.herokuapp.com/you-are-invited/2f3690bb2b8130d633ca806cc6e0bc8f",
        "wish": "Congratulation, Ramji. I would surely be there.",
        "wishId": 1,
        "wishPosted": "2022-08-14T12:10:08.687Z"
    }

     */

    public void setIs_invitation_viewed(boolean is_invitation_viewed) {
        this.is_invitation_viewed = is_invitation_viewed;
    }
}
