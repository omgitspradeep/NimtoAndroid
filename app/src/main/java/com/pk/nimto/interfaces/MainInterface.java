package com.pk.nimto.interfaces;

import com.pk.nimto.models.CustOrderModel;
import com.pk.nimto.models.CustomerProfileModel;
import com.pk.nimto.models.CustomizedInviteeModel;
import com.pk.nimto.models.PaginatedThemes;
import com.pk.nimto.models.ThemeDetail;
import com.pk.nimto.models.marriagedata.Gallery;

import java.util.ArrayList;
import java.util.List;

public class MainInterface {

    public interface AllThemesListener {
        public void areAllThemesReceived(boolean flag, List<ThemeDetail> allThemes);
    }


    public interface LoginListener {
        public void onLoginResponse(boolean flag, String msg);
    }

    public interface orderDeleteListner {
        public void onOrderDeleteResponse(boolean flag, List<CustOrderModel> customerOrderList);
    }

    public interface NextPaginatedThemesListener {
        public void pagThemesReceived(PaginatedThemes paginatedThemes);
        public void pagThemesFail(String msg);
    }

    public interface ProfileCallListener {
        public void profileCallSuccess(CustomerProfileModel reader);
        public void profileCallFail(String message);
    }

    public interface ProfileUpdateListener {
        public void onProfileUpdateResponse(boolean flag ,String msg);
    }

    public interface tokenListener {
        public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh);
    }

    public interface ViewAsListener {
        public void onViewAsResponse(boolean flag, String response);
    }

    public interface onGettingInvitees {
        public void isInviteesReceived(boolean isSuccessful, ArrayList<CustomizedInviteeModel> inviteesList);
    }

    public interface onGalleryImageUpdate {
        // deleteOrUpdate : true -> delete, false -> false
        public void isImageUpdated(boolean isSuccessful, Gallery updatedImage, int imageID, boolean deleteOrUpdate);
    }

    public interface onGalleryImageCreate {
        public void isImageCreated(boolean isSuccessful, Gallery createdImage);
    }
}
