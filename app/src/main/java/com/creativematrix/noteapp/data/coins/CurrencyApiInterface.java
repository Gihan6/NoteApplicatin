package com.creativematrix.noteapp.data.coins;

import com.creativematrix.noteapp.data.groups.DisplayGroupRequest;
import com.creativematrix.noteapp.data.groups.DisplayGroupResponse;
import com.creativematrix.noteapp.data.groups.Group;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CurrencyApiInterface {

    @POST("AddGroup/")
    Call<Group> postAddGroup(@Body Group group);

    @POST("DisplayCurrency/")
    Call<DisplayCurrencyResponse> postDisplayCurrnecy(@Body DisplayCurrencyRequest displayCurrencyRequest);
}
