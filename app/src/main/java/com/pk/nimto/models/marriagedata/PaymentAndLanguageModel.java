package com.pk.nimto.models.marriagedata;

public class PaymentAndLanguageModel {
    String k,v;

    public PaymentAndLanguageModel(String k, String v) {
        this.k = k;
        this.v = v;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "PlanLanguageModel{" +
                "k='" + k + '\'' +
                ", v='" + v + '\'' +
                '}';
    }
}

/*

// Lang
    {
        "k": "en",
        "v": "English"
    }

 // Payment
    {
        "k": "Khalti",
        "v": "Khalti"
     }



 */