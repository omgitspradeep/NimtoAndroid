package com.example.nimto0.models.ReceiveData;

import com.example.nimto0.models.marriagedata.PaymentAndLanguageModel;
import com.example.nimto0.models.marriagedata.Plans;

import java.util.List;

public class PlanLangResponse {
    List<Plans> plans;
    List<PaymentAndLanguageModel> lang;
    List<PaymentAndLanguageModel> payment;

    public PlanLangResponse(List<Plans> plans, List<PaymentAndLanguageModel> lang, List<PaymentAndLanguageModel> payment) {
        this.plans = plans;
        this.lang = lang;
        this.payment = payment;
    }

    public List<Plans> getPlans() {
        return plans;
    }

    public void setPlans(List<Plans> plans) {
        this.plans = plans;
    }

    public List<PaymentAndLanguageModel> getLang() {
        return lang;
    }

    public void setLang(List<PaymentAndLanguageModel> lang) {
        this.lang = lang;
    }

    public List<PaymentAndLanguageModel> getPayment() {
        return payment;
    }

    public void setPayment(List<PaymentAndLanguageModel> payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "PlanLangResponse{" +
                "plans=" + plans +
                ", lang=" + lang +
                ", payment=" + payment +
                '}';
    }


}

/*
{
    "plans": [
        {
            "id": 1,
            "plans": "F",
            "no_of_days": 2,
            "no_of_invitees": 10,
            "plan_price": 0
        },
        {
            "id": 4,
            "plans": "O",
            "no_of_days": 3,
            "no_of_invitees": 50,
            "plan_price": 100
        },
        {
            "id": 2,
            "plans": "Y",
            "no_of_days": 7,
            "no_of_invitees": 70,
            "plan_price": 200
        },
        {
            "id": 5,
            "plans": "G",
            "no_of_days": 10,
            "no_of_invitees": 100,
            "plan_price": 400
        },
        {
            "id": 3,
            "plans": "B",
            "no_of_days": 12,
            "no_of_invitees": 100,
            "plan_price": 500
        },
        {
            "id": 6,
            "plans": "I",
            "no_of_days": 30,
            "no_of_invitees": 1000,
            "plan_price": 1000
        }
    ],
    "lang": [
        {
            "k": "en",
            "v": "English"
        },
        {
            "k": "ne",
            "v": "Nepali"
        },
        {
            "k": "wari",
            "v": "Newari"
        },
        {
            "k": "hi",
            "v": "Hindi"
        }
    ],
    "payment": [
        {
            "k": "Khalti",
            "v": "Khalti"
        },
        {
            "k": "IME Pay",
            "v": "IME Pay"
        },
        {
            "k": "Esewa",
            "v": "Esewa"
        },
        {
            "k": "Local Bank",
            "v": "Local Bank"
        }
    ]
}
 */