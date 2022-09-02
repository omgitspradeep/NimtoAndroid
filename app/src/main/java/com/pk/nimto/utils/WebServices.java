package com.pk.nimto.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.pk.nimto.interfaces.ApiInterface;
import com.pk.nimto.interfaces.MainInterface;
import com.pk.nimto.interfaces.commonListener;
import com.pk.nimto.interfaces.onGettingInvitees;
import com.pk.nimto.models.CustOrderModel;
import com.pk.nimto.models.CustomerProfileModel;
import com.pk.nimto.models.CustomizedInviteeModel;
import com.pk.nimto.models.LoginModel;
import com.pk.nimto.models.PaginatedThemes;
import com.pk.nimto.models.ReceiveData.AllThemesResponseModel;
import com.pk.nimto.models.ReceiveData.LoginJWTModel;
import com.pk.nimto.models.ReceiveData.PlanLangResponse;
import com.pk.nimto.models.SendData.ChangePasswordModel;
import com.pk.nimto.models.SendData.InviteeModel;
import com.pk.nimto.models.SendData.RefreshTokenModel;
import com.pk.nimto.models.SendData.TempGallery;
import com.pk.nimto.models.SendData.TempMeetingPoint;
import com.pk.nimto.models.SendData.TempParents;
import com.pk.nimto.models.SendData.VerifyTokenModel;
import com.pk.nimto.models.SendData.WisherObjectU;
import com.pk.nimto.models.SendData.TempContact;
import com.pk.nimto.models.SendData.TempMainData;
import com.pk.nimto.models.SendData.TempTestimonial;
import com.pk.nimto.models.Token;
import com.pk.nimto.models.SendData.SendLoginData;
import com.pk.nimto.models.marriagedata.AllMarriageData;
import com.pk.nimto.models.marriagedata.Contact;
import com.pk.nimto.models.marriagedata.Gallery;
import com.pk.nimto.models.marriagedata.MainData;
import com.pk.nimto.models.marriagedata.Meetingpoint;
import com.pk.nimto.models.marriagedata.Parents;
import com.pk.nimto.models.marriagedata.Testimonials;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebServices {


    static ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public static void AllInviteesCaller(onGettingInvitees inviteesInterface, int order_id) {

        // We have different base url so, different Api interface.

        Call<List<CustomizedInviteeModel>> call = apiInterface.getInvitees(order_id);
        call.enqueue(new Callback<List<CustomizedInviteeModel>>() {
            @Override
            public void onResponse(Call<List<CustomizedInviteeModel>> call, Response<List<CustomizedInviteeModel>> response) {

                if (response.code() == 200) {
                    ArrayList<CustomizedInviteeModel> myInvitees = (ArrayList<CustomizedInviteeModel>) response.body();
                    ResourceManager.allInviteesAreReceived(myInvitees,order_id);

                    inviteesInterface.isInviteesReceived(true, myInvitees);

                }else{
                    inviteesInterface.isInviteesReceived(false, null);
                }
                Log.e("Maine", "On Response:" + response.body());
            }

            @Override
            public void onFailure(Call<List<CustomizedInviteeModel>> call, Throwable t) {
                inviteesInterface.isInviteesReceived(false, null);
                Log.e("Maine", "On Failure:" + t.getLocalizedMessage());
            }
        });
    }


    public static void NextPaginatedCaller(String nextUrl, MainInterface.NextPaginatedThemesListener nextPaginatedThemesListener) {
        Call<PaginatedThemes> call = apiInterface.getNextThemes(nextUrl);
        call.enqueue(new Callback<PaginatedThemes>() {
            @Override
            public void onResponse(Call<PaginatedThemes> call, Response<PaginatedThemes> response) {
                if (response.code() == 200) {
                    PaginatedThemes p = response.body();
                    nextPaginatedThemesListener.pagThemesReceived(p);
                }
            }

            @Override
            public void onFailure(Call<PaginatedThemes> call, Throwable t) {
                nextPaginatedThemesListener.pagThemesFail("Next paginated book call failed.");
            }
        });

    }


    private static final String TAG = "Maine";


    public static void updatingWisher( onGettingInvitees listener , String accessToken, WisherObjectU wisherObject){
        Log.e(TAG, wisherObject.toString());
        String tokenWithBearer = "Bearer "+ accessToken;
        Call<List<CustomizedInviteeModel>> call = apiInterface.updatingWisher(wisherObject.getInvitee(), tokenWithBearer, wisherObject);
        call.enqueue(new Callback<List<CustomizedInviteeModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<CustomizedInviteeModel>> call, @NotNull Response<List<CustomizedInviteeModel>> response) {
                if (response.code() == 200) {
                    ArrayList<CustomizedInviteeModel> myInvitees = (ArrayList<CustomizedInviteeModel>) response.body();
                    ResourceManager.allInviteesAreReceived(myInvitees,wisherObject.getOrder());
                    listener.isInviteesReceived(true, myInvitees);
                    Log.e(TAG, "Wisher updated successfully.");

                }else{
                    Log.e(TAG, " Wisher update unsuccess."+ response.code());
                    listener.isInviteesReceived(false, null);

                }
            }

            @Override
            public void onFailure(Call<List<CustomizedInviteeModel>> call, Throwable t) {
                listener.isInviteesReceived(false, null);
                Log.e(TAG, " Wish update unsuccess.");

            }
        });

    }

    public static void deletingWisher(onGettingInvitees listener , String accessToken, int wisher_id, int order_id ){
        String tokenWithBearer = "Bearer "+ accessToken;
        Call<List<CustomizedInviteeModel>> call = apiInterface.deletingWisher(wisher_id, tokenWithBearer);
        call.enqueue(new Callback<List<CustomizedInviteeModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<CustomizedInviteeModel>> call, @NotNull Response<List<CustomizedInviteeModel>> response) {
                if (response.code() == 200) {
                    ArrayList<CustomizedInviteeModel> myInvitees = (ArrayList<CustomizedInviteeModel>) response.body();
                    ResourceManager.allInviteesAreReceived(myInvitees, order_id);
                    listener.isInviteesReceived(true, myInvitees);

                    Log.e(TAG, "Wisher delete successfully.");

                }else{
                    Log.e(TAG, " Wisher delete unsuccess."+ response.code());
                    listener.isInviteesReceived(false, null);

                }
            }

            @Override
            public void onFailure(Call<List<CustomizedInviteeModel>> call, Throwable t) {
                listener.isInviteesReceived(false, null);
                Log.e(TAG, " Wishser delete unsuccess.");

            }
        });

    }

    public static void createNewInvitee(commonListener listener , String accessToken, InviteeModel inviteeModel, int order_id){
        String tokenWithBearer = "Bearer "+ accessToken;

        Call<List<CustomizedInviteeModel>> call = apiInterface.creatingInvitee(tokenWithBearer, inviteeModel);
        call.enqueue(new Callback<List<CustomizedInviteeModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<CustomizedInviteeModel>> call, @NotNull Response<List<CustomizedInviteeModel>> response) {
                if (response.code() == 200) {
                    Log.e(TAG, "New Invitee created successfully.");
                    ArrayList<CustomizedInviteeModel> myInvitees = (ArrayList<CustomizedInviteeModel>) response.body();
                    ResourceManager.allInviteesAreReceived(myInvitees,order_id);

                    listener.onCommonResponse(true);

                }else{
                    Log.e(TAG, "New Invitee created unsuccess."+response.code());
                    listener.onCommonResponse(false);

                }
            }

            @Override
            public void onFailure(Call<List<CustomizedInviteeModel>> call, Throwable t) {
                listener.onCommonResponse(false);
                Log.e(TAG, "New Invitee created unsuccess.");
            }
        });

    }

    public static void updatingInvitees(commonListener listener , String accessToken, InviteeModel inviteeModel){
        Log.e(TAG, inviteeModel.toString());
        String tokenWithBearer = "Bearer "+ accessToken;
        Call<List<CustomizedInviteeModel>> call = apiInterface.updatingInvitee(inviteeModel.getId(), tokenWithBearer, inviteeModel);
        call.enqueue(new Callback<List<CustomizedInviteeModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<CustomizedInviteeModel>> call, @NotNull Response<List<CustomizedInviteeModel>> response) {
                if (response.code() == 200) {
                    listener.onCommonResponse(true);
                    ArrayList<CustomizedInviteeModel> myInvitees = (ArrayList<CustomizedInviteeModel>) response.body();
                    ResourceManager.allInviteesAreReceived(myInvitees,inviteeModel.getOrder());
                    Log.e(TAG, "Invitee updated successfully.");

                }else{
                    Log.e(TAG, " Invitee update unsuccess."+ response.code());
                    listener.onCommonResponse(false);

                }
            }

            @Override
            public void onFailure(Call<List<CustomizedInviteeModel>> call, Throwable t) {
                listener.onCommonResponse(false);
                Log.e(TAG, " Invitee update unsuccess.");

            }
        });

    }

    public static void deletingInvitee(onGettingInvitees listener , String accessToken, int invitee_id, int order_id ){
        String tokenWithBearer = "Bearer "+ accessToken;
        Call<List<CustomizedInviteeModel>> call = apiInterface.deletingInvitee(invitee_id, tokenWithBearer);
        call.enqueue(new Callback<List<CustomizedInviteeModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<CustomizedInviteeModel>> call, @NotNull Response<List<CustomizedInviteeModel>> response) {
                if (response.code() == 200) {
                    ArrayList<CustomizedInviteeModel> myInvitees = (ArrayList<CustomizedInviteeModel>) response.body();
                    ResourceManager.allInviteesAreReceived(myInvitees, order_id);
                    listener.isInviteesReceived(true, myInvitees);

                    Log.e(TAG, "Invitee delete successfully.");

                }else{
                    Log.e(TAG, " Invitee delete unsuccess."+ response.code());
                    listener.isInviteesReceived(false, null);

                }
            }

            @Override
            public void onFailure(Call<List<CustomizedInviteeModel>> call, Throwable t) {
                listener.isInviteesReceived(false, null);
                Log.e(TAG, " Invitee delete unsuccess.");

            }
        });

    }

    public static void ChangePasswordCaller(commonListener listener, ChangePasswordModel changePasswordModel){
        Call<ResponseBody> call = apiInterface.changePassword(ResourceManager.getUserToken().getAccess(), changePasswordModel );
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                if (response.code() == 200) {
                    Log.e(TAG, "Password change Successfully");
                    listener.onCommonResponse(true);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Password change Unsuccessfull");
                listener.onCommonResponse(false);
            }
        });

    }

    public static void AllThemeCaller(MainInterface.AllThemesListener allThemesListener) {
        Call<PaginatedThemes> call = apiInterface.getThemes();
        call.enqueue(new Callback<PaginatedThemes>() {
            @Override
            public void onResponse(@NotNull Call<PaginatedThemes> call, @NotNull Response<PaginatedThemes> response) {
                Log.e("ANON", "ALL THEMES success");
                if (response.code() == 200) {
                    ResourceManager.setPaginatedThemes(response.body());
                    allThemesListener.areAllThemesReceived(true, response.body().getListOfThemes());
                }
            }

            @Override
            public void onFailure(Call<PaginatedThemes> call, Throwable t) {
                Log.e("ANON", " AllBOOKs failed");
                allThemesListener.areAllThemesReceived(false, null);
            }
        });
    }


    public static void loginWithJWT(MainInterface.LoginListener loginListener, String accessToken) {
        // CALLER :         WebServices.loginWithJWT();

        String tokenWithBearer = "Bearer "+ accessToken;
        Call<LoginJWTModel> call = apiInterface.loginWithToken(tokenWithBearer);
        call.enqueue(new Callback<LoginJWTModel>() {
            @Override
            public void onResponse(@NotNull Call<LoginJWTModel> call, @NotNull Response<LoginJWTModel> response) {
                Log.e(TAG, "onResponse: " +  response.body());
                if (response.code() == 200) {

                    LoginJWTModel myModel = (LoginJWTModel) response.body();
                    assert myModel != null;
                    ResourceManager.setUserProfile(myModel.getMy_profile());
                    ResourceManager.setCustomerOrders(myModel.getMy_orders());
                    ResourceManager.setPaginatedThemes(myModel.getAll_themes());


                    loginListener.onLoginResponse(true,"Login Successful");
                }else{
                    loginListener.onLoginResponse(false,"Login failed");

                }

            }

            @Override
            public void onFailure(@NonNull Call<LoginJWTModel> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage() );
                loginListener.onLoginResponse(false,"Login failed");
            }
        });
    }


    public static void getMyProfile(MainInterface.ProfileCallListener profileCallListener) {
        // CALLER :         WebServices.getMyProfile();

        String tokenWithBearer = "Bearer "+ResourceManager.getUserToken().getAccess();
        Call<CustomerProfileModel> call = apiInterface.getProfile(tokenWithBearer);
        call.enqueue(new Callback<CustomerProfileModel>() {
            @Override
            public void onResponse(@NotNull Call<CustomerProfileModel> call, @NotNull Response<CustomerProfileModel> response) {
                if(response.code()==200){
                    CustomerProfileModel currentUser= (CustomerProfileModel)response.body();
                    ResourceManager.setUserProfile(currentUser);
                    profileCallListener.profileCallSuccess(currentUser);
                }else {
                    profileCallListener.profileCallFail("Fail");
                }

            }

            @Override
            public void onFailure(@NonNull Call<CustomerProfileModel> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage() );
                profileCallListener.profileCallFail("Fail");

            }
        });
    }


    public static void allThemesWithPagination() {
        Call<AllThemesResponseModel> call = apiInterface.allThemes();
        call.enqueue(new Callback<AllThemesResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<AllThemesResponseModel> call, @NotNull Response<AllThemesResponseModel> response) {
                Log.e(TAG, "onResponse: " +  response.body());
            }

            @Override
            public void onFailure(@NonNull Call<AllThemesResponseModel> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage() );
            }
        });
    }


    public static void getTokens(SendLoginData loginData) {
        //    CALLER:    WebServices.getTokens(new SendLoginData("c@gmail.com","1abc@def"));
        Call<Token> call = apiInterface.getToken(loginData);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(@NotNull Call<Token> call, @NotNull Response<Token> response) {
                Log.e(TAG, "onResponse: " +  response.body());

            }

            @Override
            public void onFailure(@NonNull Call<Token> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage() );
            }
        });
    }

    public static void refreshToken(MainInterface.tokenListener refreshListener, String refreshToken){

        // Caller:         WebServices.refreshToken();

        Call<Token> call = apiInterface.refreshToken(new RefreshTokenModel(refreshToken));
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(@NotNull Call<Token> call, @NotNull Response<Token> response) {
                if (response.code() == 200) {

                    Token token = (Token)response.body();
                    ResourceManager.setUserToken(token);

                    // When refresh_token is still ligit, return with that token
                    assert token != null;
                    refreshListener.onTokenResponse(true, token.getAccess(), token.getRefresh(), true);
                    Log.e(TAG,"REFRESH TOKEN IS VALID.");


                }else{
                    Log.e(TAG, "onResponse: Refesh token expired"+ response.code());
                    //When refresh_token is not ligit.Send false flag for-- Direct to Login page. after making user as logout.
                    refreshListener.onTokenResponse(false,"","", false);
                    Log.e(TAG,"REFRESH TOKEN IS INVALID. GOING FOR LOGIN PAGE");

                }
            }

            @Override
            public void onFailure(@NonNull Call<Token> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage() );
                //When refresh_token is not ligit.Send false flag for-- Direct to Login page. after making user as logout.
                refreshListener.onTokenResponse(false,"","", false);


            }
        });
    }

    // Here, refresh token is sent just in case, access_token expires, refresh_token is used to hit another api to get new access_token
    public static void verifyToken(MainInterface.tokenListener tokenListener, String accessToken, String refreshToken){

        // Caller:         WebServices.verifyToken();

        Call<ResponseBody> call = apiInterface.verifyToken(new VerifyTokenModel(accessToken));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                if (response.code() == 200) {
                    // When access_token is still ligit, return with that token
                    ResourceManager.setUserToken(new Token(accessToken, refreshToken));
                    tokenListener.onTokenResponse(true, accessToken,refreshToken,false);
                    Log.e(TAG,"ACCESS TOKEN IS VALID");
                }else{
                    // When access_token is still not legit, hit another api with refresh token
                    refreshToken(tokenListener,refreshToken);
                    Log.e(TAG,"ACCESS TOKEN IS INVALID. GOING FOR REFRESH");
                }
            }


            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage() );
                // When access_token verification fails, hit another api with refresh token
                refreshToken(tokenListener,refreshToken);
                Log.e(TAG,"ACCESS TOKEN IS INVALID. GOING FOR REFRESH");

            }
        });
    }

    public static void LoginCaller(MainInterface.LoginListener loginListener, SendLoginData loginData) {
        Call<LoginModel> call = apiInterface.custLogin(loginData);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NotNull Call<LoginModel> call, @NotNull Response<LoginModel> response) {
                Log.e(TAG, "onResponse: " +  response.body());

                if (response.code() == 200) {

                    LoginModel myModel = (LoginModel) response.body();
                    assert myModel != null;
                    ResourceManager.setUserProfile(myModel.getMy_profile());
                    ResourceManager.setUserToken(myModel.getToken());
                    ResourceManager.setCustomerOrders(myModel.getMy_orders());
                    ResourceManager.setPaginatedThemes(myModel.getAll_themes());

                    loginListener.onLoginResponse(true,"Login Successful");

                }else{
                    loginListener.onLoginResponse(false,"Login failed");

                }

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.e("Maine", String.valueOf(t.getLocalizedMessage()));
                loginListener.onLoginResponse(false,"Login failed");

            }
        });
    }

    public static void ProfileUpdateCaller(CustomerProfileModel dataToBeUpdated, String accessToken,MainInterface.ProfileUpdateListener profileUpdateListener) {

        Call<CustomerProfileModel> call = apiInterface.updateProfile(dataToBeUpdated.getId(), accessToken, dataToBeUpdated);
        call.enqueue(new Callback<CustomerProfileModel>() {
            @Override
            public void onResponse(@NotNull Call<CustomerProfileModel> call, @NotNull Response<CustomerProfileModel> response) {
                Log.e("ANONE",response.toString());
                if(response.code()==200){
                    ResourceManager.setUserProfile(response.body());
                    profileUpdateListener.onProfileUpdateResponse(true, "Successful");
                }else{
                    // Anything else liKE error: 404
                    // 1. This error mostly occurs when user mistakes in profession, address and gender or if IMAGE is not base64 string
                    profileUpdateListener.onProfileUpdateResponse(false,"Something went wrong while updating your profile. Please try again..");
                }
            }
            @Override
            public void onFailure(Call<CustomerProfileModel> call, Throwable t) {
                profileUpdateListener.onProfileUpdateResponse(false,"Something went wrong while updating your profile. Please try again.");
            }
        });

    }

    public static void createNewOrder(commonListener listener, String accessTokenWithBearer, CustOrderModel custOrderModel){

        Call<CustOrderModel> call = apiInterface.createOrder(accessTokenWithBearer, custOrderModel);
        call.enqueue(new Callback<CustOrderModel>() {
            @Override
            public void onResponse(@NotNull Call<CustOrderModel> call, @NotNull Response<CustOrderModel> response) {
                Log.d(TAG, response.code()+"");
                if (response.code() == 200) {

                    CustOrderModel newOrder = (CustOrderModel) response.body();
                    ResourceManager.getCustomerOrders().add(newOrder);
                    Log.d(TAG, "New Order is created Successfully.");
                    listener.onCommonResponse(true);
                }else{
                    Log.e(TAG, "New Order creation is Unsuccessfull.");
                    listener.onCommonResponse(false);
                }
            }

            @Override
            public void onFailure(Call<CustOrderModel> call, Throwable t) {
                Log.e(TAG, "New Order creation is Unsuccessfull.");
                listener.onCommonResponse(false);
            }
        });

    }

    public static void deleteOrder(commonListener listener, String accessTokenWithBearer, int order_id){

        Call<ResponseBody> call = apiInterface.deleteOrder(order_id, accessTokenWithBearer);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                Log.d(TAG, response.code()+"");
                if (response.code() == 200) {
                    Log.d(TAG, "New Order is delete Successfully.");
                    listener.onCommonResponse(true);
                }else{
                    Log.e(TAG, "New Order delete is Unsuccessfull.");
                    listener.onCommonResponse(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "New Order creation is Unsuccessfull (Failure).");
                listener.onCommonResponse(false);
            }
        });

    }

    public static void getPlanLangAndPayments(commonListener listener){

        Call<PlanLangResponse> call = apiInterface.getPlansInfo();
        call.enqueue(new Callback<PlanLangResponse>() {
            @Override
            public void onResponse(@NotNull Call<PlanLangResponse> call, @NotNull Response<PlanLangResponse> response) {
                Log.d(TAG, response.code()+"");
                if (response.code() == 200) {
                    Log.d(TAG, "All plan data available.");
                    PlanLangResponse allResponse = (PlanLangResponse) response.body();

                    assert allResponse != null;
                    ResourceManager.setListOfPlans(allResponse.getPlans());
                    ResourceManager.setListOfPayments(allResponse.getPayment());
                    ResourceManager.setListOfLanguages(allResponse.getLang());


                    listener.onCommonResponse(true);
                }else{
                    Log.e(TAG, "All plan data Unsuccessfull.");
                    listener.onCommonResponse(false);
                }
            }

            @Override
            public void onFailure(Call<PlanLangResponse> call, Throwable t) {
                Log.e(TAG, "All plan data Unsuccessfull (Failure).");
                listener.onCommonResponse(false);
            }
        });

    }

    public static void getMarriageAllData(commonListener listener,String accessTokenWithBearer, int order_id){

        Call<AllMarriageData> call = apiInterface.getAllDataMarriage(order_id, accessTokenWithBearer);
        call.enqueue(new Callback<AllMarriageData>() {
            @Override
            public void onResponse(@NotNull Call<AllMarriageData> call, @NotNull Response<AllMarriageData> response) {
                Log.d(TAG, response.code()+"");
                if (response.code() == 200) {
                    Log.d(TAG, "GET ALL Marraiage data  Successfully");
                    AllMarriageData allMarriageData = (AllMarriageData) response.body();

                    ResourceManager.setAllMarriageDataOfselectedOrder(allMarriageData);

                    listener.onCommonResponse(true);
                }else{
                    Log.e(TAG, "GET ALL Marraiage data  Unsuccessfull");
                    listener.onCommonResponse(false);
                }
            }

            @Override
            public void onFailure(Call<AllMarriageData> call, Throwable t) {
                Log.e(TAG, "GET ALL Marraiage data  Unsuccessfull");
                listener.onCommonResponse(false);
            }
        });

    }

    public static void updateMarriageMainData(commonListener listener,String accessTokenWithBearer, int order_id, TempMainData mainData){
        Log.e("Maine", accessTokenWithBearer);
        Log.e("Maine", mainData.toString());

        Call<MainData> call = apiInterface.updateMainData(order_id, accessTokenWithBearer, mainData);
        call.enqueue(new Callback<MainData>() {
            @Override
            public void onResponse(@NotNull Call<MainData> call, @NotNull Response<MainData> response) {
                if (response.code() == 200) {
                    Log.d(TAG, "PUT MainData  Successfully");
                    MainData mainData = (MainData) response.body();
                    ResourceManager.getAllMarriageDataOfselectedOrder().setMain_data(mainData);
                    listener.onCommonResponse(true);
                }else{
                    Log.d(TAG, "PUT MainData  UnSuccessfull");
                    Log.e("Maine",response.code()+"");
                    listener.onCommonResponse(false);

                }
            }

            @Override
            public void onFailure(Call<MainData> call, Throwable t) {
                Log.e(TAG, "PUT MainData  Unsuccessfull Failure");
                listener.onCommonResponse(false);
            }
        });

    }

    public static void updateMarriageContact(commonListener listener,String accessTokenWithBearer, int order_id, TempContact contactData){

        Call<Contact> call = apiInterface.updateContactData(order_id, accessTokenWithBearer, contactData);
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(@NotNull Call<Contact> call, @NotNull Response<Contact> response) {
                if (response.code() == 200) {
                    Log.d(TAG, "PUT Contact  Successfully");
                    Contact responseContactData = (Contact) response.body();
                    ResourceManager.getAllMarriageDataOfselectedOrder().setContact(responseContactData);
                    listener.onCommonResponse(true);
                }else{
                    Log.d(TAG, "PUT Contact  UnSuccessfull");
                    Log.e("Maine",response.code()+"");
                    listener.onCommonResponse(false);

                }
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {
                Log.e(TAG, "PUT Contact  Unsuccessfull Failure");
                listener.onCommonResponse(false);
            }
        });

    }

    public static void updateMarriageTestimonialsData(commonListener listener,String accessTokenWithBearer, int order_id, TempTestimonial testimonials){
        Call<Testimonials> call = apiInterface.updateTestimonialsData(order_id, accessTokenWithBearer, testimonials);
        call.enqueue(new Callback<Testimonials>() {
            @Override
            public void onResponse(@NotNull Call<Testimonials> call, @NotNull Response<Testimonials> response) {
                if (response.code() == 200) {
                    Log.d(TAG, "GET Testimonials  Successfully");
                    ResourceManager.getAllMarriageDataOfselectedOrder().setTestimonials(response.body());
                    listener.onCommonResponse(true);
                }else{
                    listener.onCommonResponse(false);
                    Log.d(TAG, "GET Testimonials  Unsuccessfull "+response.code());

                }
            }

            @Override
            public void onFailure(Call<Testimonials> call, Throwable t) {
                Log.e(TAG, "GET Testimonials  Unsuccessfull fail");
                listener.onCommonResponse(false);
            }
        });

    }

    public static void updateMarriageMeetingpoint(commonListener listener,String accessTokenWithBearer, int order_id, TempMeetingPoint meetingPoint){
        Call<Meetingpoint> call = apiInterface.updateMeetingPointData(order_id, accessTokenWithBearer, meetingPoint);
        call.enqueue(new Callback<Meetingpoint>() {
            @Override
            public void onResponse(@NotNull Call<Meetingpoint> call, @NotNull Response<Meetingpoint> response) {
                if (response.code() == 200) {
                    Log.d(TAG, "PUT Meetingpoint  Successfully");
                    ResourceManager.getAllMarriageDataOfselectedOrder().setMeetingpoint(response.body());
                    listener.onCommonResponse(true);
                }else{
                    listener.onCommonResponse(false);
                    Log.d(TAG, "PUT Meetingpoint  Unsuccessfull "+response.code());

                }
            }

            @Override
            public void onFailure(Call<Meetingpoint> call, Throwable t) {
                Log.e(TAG, "GET Meetingpoint  Unsuccessfull");
                listener.onCommonResponse(false);
            }
        });

    }

    public static void updateMarriageParentsData(commonListener listener, String accessToken, int order_id, TempParents parents){
        Call<Parents> call = apiInterface.updateParentData(order_id, accessToken, parents);
        call.enqueue(new Callback<Parents>() {
            @Override
            public void onResponse(@NotNull Call<Parents> call, @NotNull Response<Parents> response) {
                if (response.code() == 200) {
                    Log.d(TAG, "PUT Parents  Successfully");
                    ResourceManager.getAllMarriageDataOfselectedOrder().setParents(response.body());
                    listener.onCommonResponse(true);
                }else{
                    listener.onCommonResponse(false);
                    Log.d(TAG, "PUT Parents  Unsuccessfull "+response.code());

                }
            }

            @Override
            public void onFailure(Call<Parents> call, Throwable t) {
                Log.e(TAG, "GET Parents  Unsuccessfull");
                listener.onCommonResponse(false);
            }
        });

    }

    public static void updateMarriageParentsDataTemp(commonListener listener, String accessToken, int order_id, MultipartBody.Part image){
        Call<Parents> call = apiInterface.updateParentDataTemp(order_id, accessToken, image);
        call.enqueue(new Callback<Parents>() {
            @Override
            public void onResponse(@NotNull Call<Parents> call, @NotNull Response<Parents> response) {
                if (response.code() == 200) {
                    Log.d(TAG, "PUT Parents  Successfully");
                    Parents parents = (Parents) response.body();
                    Log.d(TAG, parents.toString());

                    ResourceManager.getAllMarriageDataOfselectedOrder().setParents(parents);
                    listener.onCommonResponse(true);
                }else{
                    listener.onCommonResponse(false);
                    Log.d(TAG, "PUT Parents  Unsuccessfull "+response.code());

                }
            }

            @Override
            public void onFailure(Call<Parents> call, Throwable t) {
                Log.e(TAG, "GET Parents  Unsuccessfull");
                listener.onCommonResponse(false);
            }
        });

    }

    public static void getMarriageGalleryData(commonListener listener,String accessToken, int order_id){
        Call<List<Gallery>> call = apiInterface.getGalleryData(order_id, accessToken);
        call.enqueue(new Callback<List<Gallery>>() {
            @Override
            public void onResponse(@NotNull Call<List<Gallery>> call, @NotNull Response<List<Gallery>> response) {
                if (response.code() == 200) {
                    Log.d(TAG, "GET Gallery  Successfully");
                    listener.onCommonResponse(true);
                }
            }

            @Override
            public void onFailure(Call<List<Gallery>> call, Throwable t) {
                Log.e(TAG, "GET Gallery  Unsuccessfull");
                listener.onCommonResponse(false);
            }
        });

    }

    public static void createMarriageGalleryData(MainInterface.onGalleryImageCreate listener,String accessToken, String title, String detail, int marriage_data, MultipartBody.Part image){
        Call<Gallery> call = apiInterface.createGalleryData(accessToken,title,detail,marriage_data, image);
        call.enqueue(new Callback<Gallery>() {
            @Override
            public void onResponse(@NotNull Call<Gallery> call, @NotNull Response<Gallery> response) {
                if (response.code() == 200) {
                    Gallery g= response.body();
                    ResourceManager.getAllMarriageDataOfselectedOrder().getGalleries().add(g);
                    listener.isImageCreated(true, g);
                }else{
                    listener.isImageCreated(false, null);

                }
            }

            @Override
            public void onFailure(Call<Gallery> call, Throwable t) {
                Log.e(TAG, "GET Gallery  Unsuccessfull");
                listener.isImageCreated(false, null);
            }
        });

    }

    public static void updateMarriageGalleryData(MainInterface.onGalleryImageUpdate listener, int imageID, boolean deleteOrUpdate,String accessToken, TempGallery gallery){
        Call<Gallery> call = apiInterface.updateGalleryData(imageID,accessToken, gallery);
        call.enqueue(new Callback<Gallery>() {
            @Override
            public void onResponse(@NotNull Call<Gallery> call, @NotNull Response<Gallery> response) {
                if (response.code() == 200) {
                    Gallery g= response.body();
                    listener.isImageUpdated(true, g, imageID,deleteOrUpdate);

                }else{
                    listener.isImageUpdated(false,null,imageID, deleteOrUpdate);
                }
            }

            @Override
            public void onFailure(Call<Gallery> call, Throwable t) {
                Log.e(TAG, "GET Gallery  Unsuccessfull");
                listener.isImageUpdated(false,null,imageID, deleteOrUpdate);
            }
        });

    }

    public static void deleteMarriageGalleryData(MainInterface.onGalleryImageUpdate listener, int imageID, boolean deleteOrUpdate, String accessToken){
        Call<Gallery> call = apiInterface.deleteGalleryData(imageID,accessToken);
        call.enqueue(new Callback<Gallery>() {
            @Override
            public void onResponse(@NotNull Call<Gallery> call, @NotNull Response<Gallery> response) {
                if (response.code() == 200) {
                    Log.e(TAG, "Delete Gallery  Successfull");
                    listener.isImageUpdated(true, null, imageID, deleteOrUpdate);
                }else{
                    listener.isImageUpdated(false,null,imageID, deleteOrUpdate);
                }
            }

            @Override
            public void onFailure(Call<Gallery> call, Throwable t) {
                Log.e(TAG, "Delete Gallery  Unsuccessfull");
                listener.isImageUpdated(false,null,imageID, deleteOrUpdate);
            }
        });

    }





}
