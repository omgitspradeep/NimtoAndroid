package com.pk.nimto.models.marriagedata;

public class temp { }

/*

Same person sitting on different boats.
Some boats small, some boats big, some boat old,
boats on the river, mountain around,  sun, rain,

    1. Create interface
    @Multipart
    @PUT("/api/mgalleryapi/{pk}/")
    Call<Gallery> updateGalleryData(@Path("pk")int pk, @Header("Authorization") String authorization);

    2. Send only image for update.
    3. if it succeeds, then make change in interface
    @Multipart
    @PUT("/api/mgalleryapi/{pk}/")
    Call<Gallery> updateGalleryData(@Path("pk")int pk, @Header("Authorization") String authorization, @Body Gallery allVariablesWithoutIDandImage);


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
