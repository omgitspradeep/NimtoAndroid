package com.example.nimto0.models.marriagedata;

public class Meetingpoint {
    private int id ,marriage_data;
    private String longitude;
    private String latitude;
    private String direction_info;
    private String palace_name;
    private String address;
    private String contact_num;
    private String email_or_fb_link;

    public Meetingpoint(int id, int marriage_data, String longitude, String latitude, String direction_info, String palace_name, String address, String contact_num, String email_or_fb_link) {
        this.id = id;
        this.marriage_data = marriage_data;
        this.longitude = longitude;
        this.latitude = latitude;
        this.direction_info = direction_info;
        this.palace_name = palace_name;
        this.address = address;
        this.contact_num = contact_num;
        this.email_or_fb_link = email_or_fb_link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMarriage_data() {
        return marriage_data;
    }

    public void setMarriage_data(int marriage_data) {
        this.marriage_data = marriage_data;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDirection_info() {
        return direction_info;
    }

    public void setDirection_info(String direction_info) {
        this.direction_info = direction_info;
    }

    public String getPalace_name() {
        return palace_name;
    }

    public void setPalace_name(String palace_name) {
        this.palace_name = palace_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_num() {
        return contact_num;
    }

    public void setContact_num(String contact_num) {
        this.contact_num = contact_num;
    }

    public String getEmail_or_fb_link() {
        return email_or_fb_link;
    }

    public void setEmail_or_fb_link(String email_or_fb_link) {
        this.email_or_fb_link = email_or_fb_link;
    }
}

/*

url:http://muktinath.herokuapp.com/api/data/<order_id>/marriage_mp_data/
{
    "id": 2,
    "longitude": "27.640382435658456",
    "latitude": "83.30955395735835",
    "direction_info": "Once you get Bethani, take Lumbini circuit road leading to West direction. Once you reach a Siyari river then take road towards North that leads directly to marriage location.",
    "palace_name": "Buddhabhumi Party palace",
    "address": "Rupandehi, Nepal",
    "contact_num": "+977-1234567897",
    "email_or_fb_link": "binary.science98@gmail.com",
    "marriage_data": 2
}
 */