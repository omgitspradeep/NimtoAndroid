package com.pk.nimto.models.ReceiveData;

import com.pk.nimto.models.ThemeDetail;

import java.util.List;

public class AllThemesResponseModel {
    private int count;
    private String next;
    private String previous;
    private List<ThemeDetail> results =null;

    public AllThemesResponseModel(int count, String next, String previous, List<ThemeDetail> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
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

    public List<ThemeDetail> getResults() {
        return results;
    }

    public void setResults(List<ThemeDetail> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "AllThemesResponseModel{" +
                "count=" + count +
                ", next='" + next + '\'' +
                ", previous='" + previous + '\'' +
                ", results=" + results +
                '}';
    }
}
