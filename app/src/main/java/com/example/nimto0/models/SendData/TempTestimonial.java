package com.example.nimto0.models.SendData;

public class TempTestimonial {
    private String grand_parents;
    private String parents;
    private String brothers;
    private String sisters;
    private String nephews;
    private String cousins;
    private String maternal;

    public TempTestimonial(String grand_parents, String parents, String brothers, String sisters, String nephews, String cousins, String maternal) {
        this.grand_parents = grand_parents;
        this.parents = parents;
        this.brothers = brothers;
        this.sisters = sisters;
        this.nephews = nephews;
        this.cousins = cousins;
        this.maternal = maternal;
    }

    @Override
    public String toString() {
        return "TempTestimonial{" +
                "grand_parents='" + grand_parents + '\'' +
                ", parents='" + parents + '\'' +
                ", brothers='" + brothers + '\'' +
                ", sisters='" + sisters + '\'' +
                ", nephews='" + nephews + '\'' +
                ", cousins='" + cousins + '\'' +
                ", maternal='" + maternal + '\'' +
                '}';
    }
}
