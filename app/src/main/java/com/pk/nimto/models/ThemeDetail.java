package com.pk.nimto.models;

public class ThemeDetail {
    private int id;
    private String theme_name;
    private String theme_type;
    private String theme_link;
    private String theme_color;
    private String sample_page_location;
    private String description;
    private String theme_image;
    private int theme_price;

    public ThemeDetail(int id, String theme_name, String theme_type, String theme_link, String theme_color, String sample_page_location, String description, String theme_image, int theme_price) {
        this.id = id;
        this.theme_name = theme_name;
        this.theme_type = theme_type;
        this.theme_link = theme_link;
        this.theme_color = theme_color;
        this.sample_page_location = sample_page_location;
        this.description = description;
        this.theme_image = theme_image;
        this.theme_price = theme_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme_name() {
        return theme_name;
    }

    public void setTheme_name(String theme_name) {
        this.theme_name = theme_name;
    }

    public String getTheme_type() {
        return theme_type;
    }

    public void setTheme_type(String theme_type) {
        this.theme_type = theme_type;
    }

    public String getTheme_link() {
        return theme_link;
    }

    public void setTheme_link(String theme_link) {
        this.theme_link = theme_link;
    }

    public String getTheme_color() {
        return theme_color;
    }

    public void setTheme_color(String theme_color) {
        this.theme_color = theme_color;
    }

    public String getSample_page_location() {
        return sample_page_location;
    }

    public void setSample_page_location(String sample_page_location) {
        this.sample_page_location = sample_page_location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTheme_image() {
        return theme_image;
    }

    public void setTheme_image(String theme_image) {
        this.theme_image = theme_image;
    }

    public int getTheme_price() {
        return theme_price;
    }

    public void setTheme_price(int theme_price) {
        this.theme_price = theme_price;
    }

    @Override
    public String toString() {
        return "ThemeDetail{" +
                "id=" + id +
                ", theme_name='" + theme_name + '\'' +
                ", theme_type='" + theme_type + '\'' +
                ", theme_link='" + theme_link + '\'' +
                ", theme_color='" + theme_color + '\'' +
                ", sample_page_location='" + sample_page_location + '\'' +
                ", description='" + description + '\'' +
                ", theme_image='" + theme_image + '\'' +
                ", theme_price=" + theme_price +
                '}';
    }
}

  /*


{
	"id": 1,
	"theme_name": "Nepali Marriage",
	"theme_type": "Marriage",
	"theme_link": "themes_invitation/marriage/theme_01.html",
	"theme_color": "pink",
	"sample_page_location": "themes_sample/marriage/sample_theme_01.html",
	"description": "This theme talks about Nepalese way of auspicious marriage ceremony.",
	"theme_image": "http://muktinath.herokuapp.com/static/core/theme_images/8.png",
	"theme_price": 150
}


    */