package com.pk.nimto.interfaces;

import com.pk.nimto.models.CustomizedInviteeModel;

import java.util.ArrayList;

public interface onGettingInvitees {
    public void isInviteesReceived(boolean isSuccessful, ArrayList<CustomizedInviteeModel> inviteesList);

}
