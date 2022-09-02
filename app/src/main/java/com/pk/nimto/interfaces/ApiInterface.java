package com.pk.nimto.interfaces;


import com.pk.nimto.models.CustOrderModel;
import com.pk.nimto.models.CustomerProfileModel;
import com.pk.nimto.models.LoginModel;
import com.pk.nimto.models.ReceiveData.AllThemesResponseModel;
import com.pk.nimto.models.ReceiveData.LoginJWTModel;
import com.pk.nimto.models.ReceiveData.PlanLangResponse;
import com.pk.nimto.models.SendData.TempGallery;
import com.pk.nimto.models.SendData.TempMeetingPoint;
import com.pk.nimto.models.SendData.TempParents;
import com.pk.nimto.models.SendData.WisherObjectU;
import com.pk.nimto.models.RegistrationModel;
import com.pk.nimto.models.SendData.ChangePasswordModel;
import com.pk.nimto.models.SendData.InviteeModel;
import com.pk.nimto.models.SendData.RefreshTokenModel;
import com.pk.nimto.models.SendData.VerifyTokenModel;
import com.pk.nimto.models.SendData.TempContact;
import com.pk.nimto.models.SendData.TempMainData;
import com.pk.nimto.models.SendData.TempTestimonial;
import com.pk.nimto.models.ThemeDetail;
import com.pk.nimto.models.CustomizedInviteeModel;
import com.pk.nimto.models.PaginatedThemes;
import com.pk.nimto.models.Token;
import com.pk.nimto.models.SendData.SendLoginData;
import com.pk.nimto.models.marriagedata.AllMarriageData;
import com.pk.nimto.models.marriagedata.Contact;
import com.pk.nimto.models.marriagedata.Gallery;
import com.pk.nimto.models.marriagedata.MainData;
import com.pk.nimto.models.marriagedata.Meetingpoint;
import com.pk.nimto.models.marriagedata.Parents;
import com.pk.nimto.models.marriagedata.Testimonials;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ApiInterface {

    /*
    Example
    Call<ResponseBody> methodname(@Body Map<String, String> mobile);

    Info: If we have to send something in post. Make object and send it as "@Body".
     */



    // Working conditons

    @GET("/api/pag-themes/")
    Call<AllThemesResponseModel> allThemes();

    @POST("/api/token/")
    Call<Token> getToken(@Body SendLoginData sendLoginData);

    @GET("/api/user/profile/")
    Call<CustomerProfileModel> getProfile(@Header("Authorization") String authorization);

    // Updating profile
    @PUT("/api/user/profile/{user_id}/")
    Call<CustomerProfileModel> updateProfile(@Path("user_id") int user_id, @Header("Authorization") String authorization, @Body CustomerProfileModel profile);


    @POST("/api/token/verify/")
    Call<ResponseBody> verifyToken(@Body VerifyTokenModel verifyToken);

    @POST("/api/token/refresh/")
    Call<Token> refreshToken(@Body RefreshTokenModel refreshToken);

    @POST("/api/user/login/")
    Call<LoginModel> custLogin(@Body SendLoginData sendLoginData);

    @GET("/api/user/loginjwt/")
    Call<LoginJWTModel> loginWithToken(@Header("Authorization") String authorization);



    //Change Password
    @POST("api/user/change-password/")
    Call<ResponseBody> changePassword(@Header("Authorization") String authorization, @Body ChangePasswordModel changePasswordModel);


    // Calling allinvitees
    @GET("/api/all_guests/{order_id}")
    Call<List<CustomizedInviteeModel>> getInvitees(@Path("order_id")int order_id);


    @POST("/api/inviteesapi/")
    Call<List<CustomizedInviteeModel>> creatingInvitee(@Header("Authorization") String authorization, @Body InviteeModel inviteeModel);


    @PUT("/api/inviteesapi/{invitee_id}/")
    Call<List<CustomizedInviteeModel>> updatingInvitee(@Path("invitee_id")int invitee_id, @Header("Authorization")String authorization, @Body InviteeModel inviteeModel);


    @DELETE("/api/inviteesapi/{invitee_id}/")
    Call<List<CustomizedInviteeModel>> deletingInvitee(@Path("invitee_id")int invitee_id, @Header("Authorization") String authorization);


    @PUT("/api/wisherapi/{wisher_id}/")
    Call<List<CustomizedInviteeModel>> updatingWisher(@Path("wisher_id")int wisher_id, @Header("Authorization") String authorization, @Body WisherObjectU wisherObject);


    @DELETE("/api/wisherapi/{wisher_id}/")
    Call<List<CustomizedInviteeModel>> deletingWisher(@Path("wisher_id")int wisher_id, @Header("Authorization") String authorization);









    // For gettin different Order data OF Marriage TYPE:
    @GET("/api/data/{order_id}/marriage_all_data/")  // /api/data/2/marriage_all_data/
    Call<AllMarriageData> getAllDataMarriage(@Path("order_id")int order_id, @Header("Authorization") String authorization);

    // NOT WORKING (API: ok, Request from MObile: Bad)
    @PUT("/api/data/{order_id}/marriage_main_data/")
    Call<MainData> updateMainData(@Path("order_id")int order_id, @Header("Authorization") String authorization, @Body TempMainData mainData);

    @PUT("/api/data/{order_id}/marriage_contact_data/")
    Call<Contact> updateContactData(@Path("order_id")int order_id, @Header("Authorization") String authorization, @Body TempContact contact);

    @PUT("/api/data/{order_id}/marriage_testimonials_data/")
    Call<Testimonials> updateTestimonialsData(@Path("order_id")int order_id, @Header("Authorization") String authorization, @Body TempTestimonial testimonials);

    @PUT("/api/data/{order_id}/marriage_mp_data/")
    Call<Meetingpoint> updateMeetingPointData(@Path("order_id")int order_id, @Header("Authorization") String authorization, @Body TempMeetingPoint meetingPoint);

    @PUT("/api/data/{order_id}/marriage_parents_data/")
    Call<Parents> updateParentData(@Path("order_id")int order_id, @Header("Authorization") String authorization, @Body TempParents parents);


    @GET("/api/data/{order_id}/marriage_gallery_data/")
    Call<List<Gallery>> getGalleryData(@Path("order_id")int order_id, @Header("Authorization") String authorization);

    @PUT("/api/mgalleryapi/{pk}/")  // pk: image id
    Call<Gallery> updateGalleryData(@Path("pk")int pk, @Header("Authorization") String authorization, @Body TempGallery gallery);

    @Multipart
    @PUT("/api/mgalleryapi/{pk}/")  // pk: image id
     Call<Gallery> updateGalleryDataTemp(@Path("pk")int pk,
                                     @Header("Authorization") String authorization,
                                     @Part("title") String title,
                                     @Part("detail") String detail,
                                     @Part("marriage_data") int marriage_data,
                                     @Part MultipartBody.Part image
     );


    @Multipart
    @POST("/api/mgalleryapi/")
    Call<Gallery> createGalleryData(@Header("Authorization") String authorization,
                                    @Part("title") String title,
                                    @Part("detail") String detail,
                                    @Part("marriage_data") int marriage_data,
                                    @Part MultipartBody.Part image
                                    );

    @DELETE("/api/mgalleryapi/{pk}/")
    Call<Gallery> deleteGalleryData(@Path("pk")int pk, @Header("Authorization") String authorization);




    //    java.lang.IllegalArgumentException: @Body parameters cannot be used with form or multi-part encoding. (parameter #4)
    @Multipart
    @PUT("/api/data/{order_id}/marriage_parents_data/")
    Call<Parents> updateParentDataTemp(@Path("order_id")int order_id, @Header("Authorization") String authorization, @Part MultipartBody.Part part);












    // NOt tested yet
    @POST("api/user/register/")
    Call<RegistrationModel> custRegistration();

    @POST("/api/themes/")
    Call<ThemeDetail> getAllThemes();


    @POST("/api/user/send-reset-password-email/")
    Call<String> resetPasswordEmail();


    @GET("api/orderapi/{order_id}/")
    Call<CustOrderModel> getOrder();

    @POST("/api/orderapi/")
    Call<CustOrderModel> createOrder(@Header("Authorization") String authorization, @Body CustOrderModel custOrderModel);

    @PUT("/api/orderapi/{order_id}")
    Call<ResponseBody> updatingOrder(@Header("Authorization") String authorization, @Body CustOrderModel custOrderModel);

    @DELETE("/api/orderapi/{order_id}/")
    Call<ResponseBody> deleteOrder(@Path("order_id")int order_id, @Header("Authorization") String authorization);


    @GET("/api/plansAndLanguages/")
    Call<PlanLangResponse> getPlansInfo();




    // Calling all paginated books
    @GET("/bbc/api/allbooks/")
    Call<PaginatedThemes> getThemes();

    // Calling themes for next page during pagination used for both 1. Getting All books and 2. My books
    @GET
    Call<PaginatedThemes> getNextThemes(@Url String url);

    // Calling all paginated books
    @GET("/bbc/api/getMyBooks/{id}")
    Call<PaginatedThemes> getMyThemes(@Path("id") int id);


    @PUT("/bbc/api/getBooks/{bookID}")
    Call<ThemeDetail> updateTheme(@Path("bookID")int bookID, @Body ThemeDetail bookDetail);






    /*

{
    "id": 4,
    "borrow_accept": true,
    "book_received_by_borrower": false,
    "note": "I need this book",
    "returned": false,
    "borrower": 2,
    "book_borrowed": 10
}
     */









}
