package com.example.nimto0.models.marriagedata;

public class Contact {

    private int id, marriage_data;
    private String phone;
    private String email;
    private String twitter_link;
    private String fb_link;
    private String yt_link;
    private String lnkd_link;


    public Contact(int id, int marriage_data, String phone, String email, String twitter_link, String fb_link, String yt_link, String lnkd_link) {
        this.id = id;
        this.marriage_data = marriage_data;
        this.phone = phone;
        this.email = email;
        this.twitter_link = twitter_link;
        this.fb_link = fb_link;
        this.yt_link = yt_link;
        this.lnkd_link = lnkd_link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMarriage_data() {
        return marriage_data;
    }

    public void setMarriage_data(int marriage_data) {
        this.marriage_data = marriage_data;
    }

    public String getTwitter_link() {
        return twitter_link;
    }

    public void setTwitter_link(String twitter_link) {
        this.twitter_link = twitter_link;
    }

    public String getFb_link() {
        return fb_link;
    }

    public void setFb_link(String fb_link) {
        this.fb_link = fb_link;
    }

    public String getYt_link() {
        return yt_link;
    }

    public void setYt_link(String yt_link) {
        this.yt_link = yt_link;
    }

    public String getLnkd_link() {
        return lnkd_link;
    }

    public void setLnkd_link(String lnkd_link) {
        this.lnkd_link = lnkd_link;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", marriage_data=" + marriage_data +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", twitter_link='" + twitter_link + '\'' +
                ", fb_link='" + fb_link + '\'' +
                ", yt_link='" + yt_link + '\'' +
                ", lnkd_link='" + lnkd_link + '\'' +
                '}';
    }
}

/*
url: http://muktinath.herokuapp.com/api/data/2/marriage_contact_data/

{
    "id": 2,
    "phone": "1234567898",
    "email": "sample@email.com",
    "twitter_link": "ttr.com",
    "fb_link": "f.com",
    "yt_link": "yt.com",
    "lnkd_link": "link.com",
    "marriage_data": 2
}

 */