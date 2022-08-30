package com.example.nimto0.models.SendData;

public class TempMainData {

    private String name;
    private String data_name;
    private String title_image;
    private String bride_groom_name;
    private String marry_date_text;
    private String engagement_date;
    private String wedding_day;
    private String janti_prasthan_time;
    private String janti_prsthan_place;
    private String reception_date;
    private String reception_time;
    private String reception_place;
    private String default_invitation_msz;
    private String about_us_image;
    private String groom_info;
    private String bride_info;
    private String groom_address;
    private String bride_address;
    private String footer_message;
    private String header_image;


    //Creating object for Maindata update
    public TempMainData(String name, String bride_groom_name, String marry_date_text, String engagement_date, String wedding_day, String janti_prasthan_time, String janti_prsthan_place, String reception_date, String reception_time, String reception_place, String default_invitation_msz, String groom_info, String bride_info, String groom_address, String bride_address, String footer_message) {
        this.name = name;
        this.bride_groom_name = bride_groom_name;
        this.marry_date_text = marry_date_text;
        this.engagement_date = engagement_date;
        this.wedding_day = wedding_day;
        this.janti_prasthan_time = janti_prasthan_time;
        this.janti_prsthan_place = janti_prsthan_place;
        this.reception_date = reception_date;
        this.reception_time = reception_time;
        this.reception_place = reception_place;
        this.default_invitation_msz = default_invitation_msz;
        this.groom_info = groom_info;
        this.bride_info = bride_info;
        this.groom_address = groom_address;
        this.bride_address = bride_address;
        this.footer_message = footer_message;
    }

}
