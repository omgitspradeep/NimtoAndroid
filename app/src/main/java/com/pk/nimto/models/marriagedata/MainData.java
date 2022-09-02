package com.pk.nimto.models.marriagedata;

public class MainData {
        private int id, marriage_order;
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



    public MainData(int id, String name, String data_name, String title_image, String bride_groom_name, String marry_date_text, String engagement_date, String wedding_day, String janti_prasthan_time, String janti_prsthan_place, String reception_date, String reception_time, String reception_place, String default_invitation_msz, String about_us_image, String groom_info, String bride_info, String groom_address, String bride_address, String footer_message, String header_image, int marriage_order) {
        this.id = id;
        this.name = name;
        this.data_name = data_name;
        this.title_image = title_image;
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
        this.about_us_image = about_us_image;
        this.groom_info = groom_info;
        this.bride_info = bride_info;
        this.groom_address = groom_address;
        this.bride_address = bride_address;
        this.footer_message = footer_message;
        this.header_image = header_image;
        this.marriage_order = marriage_order;
    }



// Getter Methods

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getData_name() {
            return data_name;
        }

        public String getTitle_image() {
            return title_image;
        }

        public String getBride_groom_name() {
            return bride_groom_name;
        }

        public String getMarry_date_text() {
            return marry_date_text;
        }

        public String getEngagement_date() {
            return engagement_date;
        }

        public String getWedding_day() {
            return wedding_day;
        }

        public String getJanti_prasthan_time() {
            return janti_prasthan_time;
        }

        public String getJanti_prsthan_place() {
            return janti_prsthan_place;
        }

        public String getReception_date() {
            return reception_date;
        }

        public String getReception_time() {
            return reception_time;
        }

        public String getReception_place() {
            return reception_place;
        }

        public String getDefault_invitation_msz() {
            return default_invitation_msz;
        }

        public String getAbout_us_image() {
            return about_us_image;
        }

        public String getGroom_info() {
            return groom_info;
        }

        public String getBride_info() {
            return bride_info;
        }

        public String getGroom_address() {
            return groom_address;
        }

        public String getBride_address() {
            return bride_address;
        }

        public String getFooter_message() {
            return footer_message;
        }

        public String getHeader_image() {
            return header_image;
        }

        public int getMarriage_order() {
            return marriage_order;
        }

        // Setter Methods

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setData_name(String data_name) {
            this.data_name = data_name;
        }

        public void setTitle_image(String title_image) {
            this.title_image = title_image;
        }

        public void setBride_groom_name(String bride_groom_name) {
            this.bride_groom_name = bride_groom_name;
        }

        public void setMarry_date_text(String marry_date_text) {
            this.marry_date_text = marry_date_text;
        }

        public void setEngagement_date(String engagement_date) {
            this.engagement_date = engagement_date;
        }

        public void setWedding_day(String wedding_day) {
            this.wedding_day = wedding_day;
        }

        public void setJanti_prasthan_time(String janti_prasthan_time) {
            this.janti_prasthan_time = janti_prasthan_time;
        }

        public void setJanti_prsthan_place(String janti_prsthan_place) {
            this.janti_prsthan_place = janti_prsthan_place;
        }

        public void setReception_date(String reception_date) {
            this.reception_date = reception_date;
        }

        public void setReception_time(String reception_time) {
            this.reception_time = reception_time;
        }

        public void setReception_place(String reception_place) {
            this.reception_place = reception_place;
        }

        public void setDefault_invitation_msz(String default_invitation_msz) {
            this.default_invitation_msz = default_invitation_msz;
        }

        public void setAbout_us_image(String about_us_image) {
            this.about_us_image = about_us_image;
        }

        public void setGroom_info(String groom_info) {
            this.groom_info = groom_info;
        }

        public void setBride_info(String bride_info) {
            this.bride_info = bride_info;
        }

        public void setGroom_address(String groom_address) {
            this.groom_address = groom_address;
        }

        public void setBride_address(String bride_address) {
            this.bride_address = bride_address;
        }

        public void setFooter_message(String footer_message) {
            this.footer_message = footer_message;
        }

        public void setHeader_image(String header_image) {
            this.header_image = header_image;
        }

        public void setMarriage_order(int marriage_order) {
            this.marriage_order = marriage_order;
        }


    @Override
    public String toString() {
        return "MainData{" +
                "name='" + name + '\'' +
                ", bride_groom_name='" + bride_groom_name + '\'' +
                ", marry_date_text='" + marry_date_text + '\'' +
                ", engagement_date='" + engagement_date + '\'' +
                ", wedding_day='" + wedding_day + '\'' +
                ", janti_prasthan_time='" + janti_prasthan_time + '\'' +
                ", janti_prsthan_place='" + janti_prsthan_place + '\'' +
                ", reception_date='" + reception_date + '\'' +
                ", reception_time='" + reception_time + '\'' +
                ", reception_place='" + reception_place + '\'' +
                ", default_invitation_msz='" + default_invitation_msz + '\'' +
                ", groom_info='" + groom_info + '\'' +
                ", bride_info='" + bride_info + '\'' +
                ", groom_address='" + groom_address + '\'' +
                ", bride_address='" + bride_address + '\'' +
                ", footer_message='" + footer_message + '\'' +
                '}';
    }
}

/*
url: http://muktinath.herokuapp.com/api/data/2/marriage_main_data/
{
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
}


 */