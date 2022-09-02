package com.pk.nimto.models.marriagedata;

import java.util.List;

public class AllMarriageData {
    MainData main_data;
    Contact contact;
    List<Gallery> galleries;
    Meetingpoint meetingpoint;
    Parents parents;
    Testimonials testimonials;

    public AllMarriageData(MainData main_data, Contact contact, List<Gallery> galleries, Meetingpoint meetingpoint, Parents parents, Testimonials testimonials) {
        this.main_data = main_data;
        this.contact = contact;
        this.galleries = galleries;
        this.meetingpoint = meetingpoint;
        this.parents = parents;
        this.testimonials = testimonials;
    }

    public MainData getMain_data() {
        return main_data;
    }

    public void setMain_data(MainData main_data) {
        this.main_data = main_data;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Gallery> getGalleries() {
        return galleries;
    }

    public void setGalleries(List<Gallery> galleries) {
        this.galleries = galleries;
    }

    public Meetingpoint getMeetingpoint() {
        return meetingpoint;
    }

    public void setMeetingpoint(Meetingpoint meetingpoint) {
        this.meetingpoint = meetingpoint;
    }

    public Parents getParents() {
        return parents;
    }

    public void setParents(Parents parents) {
        this.parents = parents;
    }

    public Testimonials getTestimonials() {
        return testimonials;
    }

    public void setTestimonials(Testimonials testimonials) {
        this.testimonials = testimonials;
    }

    @Override
    public String toString() {
        return "AllMarriageData{" +
                "main_data=" + main_data +
                ", contact=" + contact +
                ", galleries=" + galleries +
                ", meetingpoint=" + meetingpoint +
                ", parents=" + parents +
                ", testimonials=" + testimonials +
                '}';
    }
}



/*
URL: http://muktinath.herokuapp.com/api/data/2/marriage_all_data/

{
    "main_data": {
        "id": 2,
        "name": "Ram Ko Behe",
        "data_name": "Default Data",
        "title_image": "/static/Marriage/images/title_images/title_icon_default.gif",
        "bride_groom_name": "Ram & Sita",
        "marry_date_text": "20.12.2020 ",
        "engagement_date": "Friday, 2077 - 08 - 26 (11th December 2020) ",
        "wedding_day": "Sunday, 2077 - 09 - 05 (20th December 2020) ",
        "janti_prasthan_time": "10 a.m ",
        "janti_prsthan_place": "Suddhodhan-3, Bethani, Rupandehi ",
        "reception_date": "Tuesday, 2077 - 09 - 07 (22nd December 2020)",
        "reception_time": " From 3 P.M. to 8 P.M. ",
        "reception_place": " Riddi Siddhi Party Palace, Suddhodhan-1, Pharsatikar",
        "default_invitation_msz": "Mr. Bhaktaraj Bashyal and Mrs. Haridevi Bashyal request the honour of your presence on the auspicious occassion of the marriage ceremony of their son.",
        "about_us_image": "/static/Marriage/images/aboutus/about_default.jpg",
        "groom_info": "Hi! I am Ram Sharma currently pursing my job at Yeti International. I have completed my masters in MBA and bachelors in Computer Science. \nSuddhodhan-3, Bethani, Rupandehi",
        "bride_info": "Hi! I am Neha Dahal currently working as leading architect in Lumbini Provinance. I have completed my masters in Civil Engineering from Oxford university.\nButwal-18, Bethani, Rupandehi",
        "groom_address": "Butwal-18, Bethani, Rupandehi",
        "bride_address": "Suddhodhan-3, Bethani, Rupandehi",
        "footer_message": " Love, laughter and happily ever after. ",
        "header_image": "/static/Marriage/images/background/default_header_bg.jpg",
        "marriage_order": 2
    },
    "contact": {
        "id": 2,
        "phone": "1234567898",
        "email": "sample@email.com",
        "twitter_link": "ttr.com",
        "fb_link": "f.com",
        "yt_link": "yt.com",
        "lnkd_link": "link.com",
        "marriage_data": 2
    },
    "galleries": [
        {
            "id": 1,
            "title": "Tika Ceremony",
            "image": "/static/Marriage/images/gallery/gallery01_17lpD8W.jpg",
            "detail": "It was snapped on the the way to temple where puja was to be held.",
            "marriage_data": 2
        }
    ],
    "meetingpoint": {
        "id": 2,
        "longitude": "27.640382435658456",
        "latitude": "83.30955395735835",
        "direction_info": "Once you get Bethani, take Lumbini circuit road leading to West direction. Once you reach a Siyari river then take road towards North that leads directly to marriage location.",
        "palace_name": "Buddhabhumi Party palace",
        "address": "Rupandehi, Nepal",
        "contact_num": "+977-1234567897",
        "email_or_fb_link": "binary.science98@gmail.com",
        "marriage_data": 2
    },
    "parents": {
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
    },
    "testimonials": {
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
}


 */