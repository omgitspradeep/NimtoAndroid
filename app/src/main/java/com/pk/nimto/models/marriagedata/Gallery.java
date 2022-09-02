package com.pk.nimto.models.marriagedata;

public class Gallery {
    int id, marriage_data;
    String title, image, detail;

    public Gallery(int id, int marriage_data, String title, String image, String detail) {
        this.id = id;
        this.marriage_data = marriage_data;
        this.title = title;
        this.image = image;
        this.detail = detail;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Gallery{" +
                "id=" + id +
                ", marriage_data=" + marriage_data +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}

/*
url: http://muktinath.herokuapp.com/api/data/<order_id>/marriage_gallery_data/

[
    {
        "id": 1,
        "title": "Tika ceremony",
        "image": "/static/Marriage/images/gallery/gallery01_q2EUsqh.jpg",
        "detail": "While going towards the temple where puja is held.",
        "marriage_data": 2
    }
]


 */
