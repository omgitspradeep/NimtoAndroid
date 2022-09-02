package com.pk.nimto.models.SendData;

public class TempMeetingPoint {
    private String longitude;
    private String latitude;
    private String direction_info;
    private String palace_name;
    private String address;
    private String contact_num;
    private String email_or_fb_link;

    public TempMeetingPoint(String longitude, String latitude, String direction_info, String palace_name, String address, String contact_num, String email_or_fb_link) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.direction_info = direction_info;
        this.palace_name = palace_name;
        this.address = address;
        this.contact_num = contact_num;
        this.email_or_fb_link = email_or_fb_link;
    }
}
