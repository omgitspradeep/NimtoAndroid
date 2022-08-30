package com.example.nimto0.utils;

import android.content.Context;

import com.example.nimto0.models.CustOrderModel;
import com.example.nimto0.models.CustomerProfileModel;
import com.example.nimto0.models.CustomizedInviteeModel;
import com.example.nimto0.models.ReceiveData.PlanLangResponse;
import com.example.nimto0.models.ThemeDetail;
import com.example.nimto0.models.PaginatedThemes;
import com.example.nimto0.models.Token;
import com.example.nimto0.models.marriagedata.AllMarriageData;
import com.example.nimto0.models.marriagedata.PaymentAndLanguageModel;
import com.example.nimto0.models.marriagedata.Plans;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class ResourceManager {
    public static PaginatedThemes paginatedThemes=null;
    public static PaginatedThemes myThemes=new PaginatedThemes(1,null,null,null);

    static ArrayList<CustomizedInviteeModel> inviteeDataArray=null;
    public static ThemeDetail selectedTheme=null;
    public static CustomizedInviteeModel selectedInvitee=null;
    public static CustomerProfileModel userProfile=null;
    public static Token userToken=null;
    public static List<CustOrderModel> customerOrders=null;
    public static AllMarriageData allMarriageDataOfselectedOrder = null;


    public static List<Plans> listOfPlans=null;
    public static List<PaymentAndLanguageModel> listOfLanguages=null;
    public static List<PaymentAndLanguageModel> listOfPayments=null;

    //It is needed to know the order_id of invitees we have in ResourceManager
    public static int ORDER_ID_OF_INVITEES= 0;


    public static AllMarriageData getAllMarriageDataOfselectedOrder() {
        return allMarriageDataOfselectedOrder;
    }

    public static void setAllMarriageDataOfselectedOrder(AllMarriageData allMarriageDataOfselectedOrder) {
        ResourceManager.allMarriageDataOfselectedOrder = allMarriageDataOfselectedOrder;
    }

    public static  List<CustOrderModel> getCustomerOrders() {
        return customerOrders;
    }

    public static void setCustomerOrders(List<CustOrderModel> customerOrders) {
        ResourceManager.customerOrders = customerOrders;
    }

    public static Token getUserToken() {
        return userToken;
    }

    public static void setUserToken(Token userToken) {
        ResourceManager.userToken = userToken;
    }

    public static CustomerProfileModel getUserProfile() {
        return userProfile;
    }

    public static void setUserProfile(CustomerProfileModel currerntUser) {
        ResourceManager.userProfile = currerntUser;
    }

    public static ThemeDetail getSelectedTheme() {
        return selectedTheme;
    }


    public static void setSelectedTheme(ThemeDetail selectedBook) {
        ResourceManager.selectedTheme = selectedBook;
    }



    public static PaginatedThemes getPaginatedThemes() {
        return paginatedThemes;
    }


    public static PaginatedThemes getMyThemes() {
        return myThemes;
    }

    public static void setMyThemes(PaginatedThemes myThemes) {
        ResourceManager.myThemes = myThemes;
    }

    public static void setPaginatedThemes(PaginatedThemes allPaginatedThemes) {
        ResourceManager.paginatedThemes = allPaginatedThemes;
    }


    public static ArrayList<CustomizedInviteeModel> getAllInviteesData() {
        return inviteeDataArray;
    }

    public static void setInviteeDataArray(ArrayList<CustomizedInviteeModel> inviteeDataArray) {
        ResourceManager.inviteeDataArray = inviteeDataArray;
    }


    public static void setSelectedInvitee(CustomizedInviteeModel dm) {
        ResourceManager.selectedInvitee =dm;
    }

    public static CustomizedInviteeModel getSelectedInvitee() {
        return selectedInvitee;
    }


    public static int getOrderIdOfInvitees() {
        return ORDER_ID_OF_INVITEES;
    }

    public static void setOrderIdOfInvitees(int orderIdOfInvitees) {
        ORDER_ID_OF_INVITEES = orderIdOfInvitees;
    }

    public static void allInviteesAreReceived(ArrayList<CustomizedInviteeModel> myInvitees, int orderIdOfInvitees) {
        ResourceManager.inviteeDataArray = myInvitees;
        ResourceManager.ORDER_ID_OF_INVITEES = orderIdOfInvitees;
    }

    public static ThemeDetail getThemeDetail(int selectedThemeID) {
        List<ThemeDetail> AllThemes = paginatedThemes.getListOfThemes();
        ThemeDetail themeDetail=null;
        for (int i=0; i <AllThemes.size();i++){
            themeDetail = AllThemes.get(i);
            if(selectedThemeID== themeDetail.getId()){
                return themeDetail;
            }
        }
        return null;
    }


    public static List<Plans> getListOfPlans() {
        return listOfPlans;
    }

    public static void setListOfPlans(List<Plans> listOfPlans) {
        ResourceManager.listOfPlans = listOfPlans;
    }

    public static List<PaymentAndLanguageModel> getListOfLanguages() {
        return listOfLanguages;
    }

    public static void setListOfLanguages(List<PaymentAndLanguageModel> listOfLanguages) {
        ResourceManager.listOfLanguages = listOfLanguages;
    }

    public static List<PaymentAndLanguageModel> getListOfPayments() {
        return listOfPayments;
    }

    public static void setListOfPayments(List<PaymentAndLanguageModel> listOfPayments) {
        ResourceManager.listOfPayments = listOfPayments;
    }



}
