package com.pk.nimto.models.marriagedata;

public class Parents {
    private int id,marriage_data;
    private String bride_father_fullname;
    private String bride_mother_fullname;
    private String groom_father_fullname;
    private String groom_mother_fullname;
    private String bride_father_image;
    private String bride_mother_image;
    private String groom_father_image;
    private String groom_mother_image;

    public Parents(int id, int marriage_data, String bride_father_fullname, String bride_mother_fullname, String groom_father_fullname, String groom_mother_fullname, String bride_father_image, String bride_mother_image, String groom_father_image, String groom_mother_image) {
        this.id = id;
        this.marriage_data = marriage_data;
        this.bride_father_fullname = bride_father_fullname;
        this.bride_mother_fullname = bride_mother_fullname;
        this.groom_father_fullname = groom_father_fullname;
        this.groom_mother_fullname = groom_mother_fullname;
        this.bride_father_image = bride_father_image;
        this.bride_mother_image = bride_mother_image;
        this.groom_father_image = groom_father_image;
        this.groom_mother_image = groom_mother_image;
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

    public String getBride_father_fullname() {
        return bride_father_fullname;
    }

    public void setBride_father_fullname(String bride_father_fullname) {
        this.bride_father_fullname = bride_father_fullname;
    }

    public String getBride_mother_fullname() {
        return bride_mother_fullname;
    }

    public void setBride_mother_fullname(String bride_mother_fullname) {
        this.bride_mother_fullname = bride_mother_fullname;
    }

    public String getGroom_father_fullname() {
        return groom_father_fullname;
    }

    public void setGroom_father_fullname(String groom_father_fullname) {
        this.groom_father_fullname = groom_father_fullname;
    }

    public String getGroom_mother_fullname() {
        return groom_mother_fullname;
    }

    public void setGroom_mother_fullname(String groom_mother_fullname) {
        this.groom_mother_fullname = groom_mother_fullname;
    }

    public String getBride_father_image() {
        return bride_father_image;
    }

    public void setBride_father_image(String bride_father_image) {
        this.bride_father_image = bride_father_image;
    }

    public String getBride_mother_image() {
        return bride_mother_image;
    }

    public void setBride_mother_image(String bride_mother_image) {
        this.bride_mother_image = bride_mother_image;
    }

    public String getGroom_father_image() {
        return groom_father_image;
    }

    public void setGroom_father_image(String groom_father_image) {
        this.groom_father_image = groom_father_image;
    }

    public String getGroom_mother_image() {
        return groom_mother_image;
    }

    public void setGroom_mother_image(String groom_mother_image) {
        this.groom_mother_image = groom_mother_image;
    }

    @Override
    public String toString() {
        return "Parents{" +
                "id=" + id +
                ", marriage_data=" + marriage_data +
                ", bride_father_fullname='" + bride_father_fullname + '\'' +
                ", bride_mother_fullname='" + bride_mother_fullname + '\'' +
                ", groom_father_fullname='" + groom_father_fullname + '\'' +
                ", groom_mother_fullname='" + groom_mother_fullname + '\'' +
                ", bride_father_image='" + bride_father_image + '\'' +
                ", bride_mother_image='" + bride_mother_image + '\'' +
                ", groom_father_image='" + groom_father_image + '\'' +
                ", groom_mother_image='" + groom_mother_image + '\'' +
                '}';
    }
}

/*
url: http://muktinath.herokuapp.com/api/data/2/marriage_parents_data/
{
    "id": 2,
    "bride_father_fullname": "Dr. Ghanshyam Sharma",
    "bride_mother_fullname": "Dr. Radha Sharma",
    "groom_father_fullname": "Prof. Vidya Bhandari",
    "groom_mother_fullname": "Er. Shova Bhandari",
    "bride_father_image": "/static/Marriage/images/parents/bd.png",
    "bride_mother_image": "/static/Marriage/images/parents/bm.png",
    "groom_father_image": "/static/Marriage/images/parents/gd.png",
    "groom_mother_image": "/static/Marriage/images/parents/gm.png",
    "marriage_data": 2
}
 */