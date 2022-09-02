package com.pk.nimto.models.marriagedata;

public class Testimonials {

    private int id, marriage_data;
    private String grand_parents;
    private String parents;
    private String brothers;
    private String sisters;
    private String nephews;
    private String cousins;
    private String maternal;



    public Testimonials(int id, int marriage_data, String grand_parents, String parents, String brothers, String sisters, String nephews, String cousins, String maternal) {
        this.id = id;
        this.marriage_data = marriage_data;
        this.grand_parents = grand_parents;
        this.parents = parents;
        this.brothers = brothers;
        this.sisters = sisters;
        this.nephews = nephews;
        this.cousins = cousins;
        this.maternal = maternal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMarriageData() {
        return marriage_data;
    }

    public void setMarriageData(int marriageData) {
        this.marriage_data = marriageData;
    }

    public String getGrandParents() {
        return grand_parents;
    }

    public void setGrandParents(String grandParents) {
        this.grand_parents = grandParents;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public String getBrothers() {
        return brothers;
    }

    public void setBrothers(String brothers) {
        this.brothers = brothers;
    }

    public String getSisters() {
        return sisters;
    }

    public void setSisters(String sisters) {
        this.sisters = sisters;
    }

    public String getNephews() {
        return nephews;
    }

    public void setNephews(String nephews) {
        this.nephews = nephews;
    }

    public String getCousins() {
        return cousins;
    }

    public void setCousins(String cousins) {
        this.cousins = cousins;
    }

    public String getMaternal() {
        return maternal;
    }

    public void setMaternal(String maternal) {
        this.maternal = maternal;
    }


    @Override
    public String toString() {
        return "Testimonials{" +
                "id=" + id +
                ", marriage_data=" + marriage_data +
                ", grand_parents='" + grand_parents + '\'' +
                ", parents='" + parents + '\'' +
                ", brothers='" + brothers + '\'' +
                ", sisters='" + sisters + '\'' +
                ", nephews='" + nephews + '\'' +
                ", cousins='" + cousins + '\'' +
                ", maternal='" + maternal + '\'' +
                '}';
    }
}

/*
url : http://muktinath.herokuapp.com/api/data/2/marriage_testimonials_data/
{
    "id": 2,
    "grand_parents": "Hajurbuwa, Hajurama",
    "parents": "Thulobuwa/THuliama, uncle/aunt",
    "brothers": "Thoulo dai/ thulibhauju, sanodai/ kanchi bhauju ",
    "sisters": "Thulididi / Thulovinaju",
    "nephews": "male/ female, female, male, ....",
    "cousins": "male/ female, female, male, ....",
    "maternal": "mama/ maiju, mama/maiju, ....",
    "marriage_data": 2
}

 */
