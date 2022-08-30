package com.example.nimto0.models.SendData;

public class TempContact {
    private String phone;
    private String email;
    private String twitter_link;
    private String fb_link;
    private String yt_link;
    private String lnkd_link;

    public TempContact(String phone, String email, String twitter_link, String fb_link, String yt_link, String lnkd_link) {
        this.phone = phone;
        this.email = email;
        this.twitter_link = twitter_link;
        this.fb_link = fb_link;
        this.yt_link = yt_link;
        this.lnkd_link = lnkd_link;
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
        return "TempContact{" +
                "phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", twitter_link='" + twitter_link + '\'' +
                ", fb_link='" + fb_link + '\'' +
                ", yt_link='" + yt_link + '\'' +
                ", lnkd_link='" + lnkd_link + '\'' +
                '}';
    }
}
