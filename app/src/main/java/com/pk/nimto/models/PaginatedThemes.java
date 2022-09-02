package com.pk.nimto.models;

import java.util.List;

public class PaginatedThemes {
    int count;
    String next, previous;
    List<ThemeDetail> results;

    public PaginatedThemes(int count, String next, String previous, List<ThemeDetail> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results =results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<ThemeDetail> getListOfThemes() {
        return results;
    }

    public void setListOfThemes(List<ThemeDetail> listOfThemes) {
        this.results = listOfThemes;
    }


}
/*

API: https://mylibraryclub.herokuapp.com/bbc/api/allbooks/
{
    "count": 12,
    "next": "https://mylibraryclub.herokuapp.com/bbc/api/bookapi/?page=2",
    "previous": null,
    "results": [
        {
            "id": 12,
            "image": "https://mylibraryclub.herokuapp.com/media/images/bethani/Nemuni-NP-HA.jpeg",
            "author": "Triratna Manadhar",
            "name": "Nemuni",
            "description": "THis is the description of book Nemuni",
            "available_status": true,
            "borrow_count": 0,
            "upload_date": "2021-07-07T16:39:20.337662+05:45",
            "language": "N",
            "reader": 3
        },
        {
            "id": 11,
            "image": "https://mylibraryclub.herokuapp.com/media/images/bethani/autobiographyOfYogi-EN-HA.jpeg",
            "author": "Pramhansa Yogananda",
            "name": "Autobiography of a Yogi",
            "description": "THis is  the description of book Autobiography of a Yogi.",
            "available_status": true,
            "borrow_count": 0,
            "upload_date": "2021-07-07T16:38:21.769953+05:45",
            "language": "N",
            "reader": 1
        }
    ]
}

*/