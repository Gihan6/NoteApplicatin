package com.creativematrix.noteapp.data.user;

import com.creativematrix.noteapp.data.company.Login;
import com.creativematrix.noteapp.data.company.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApiInterface {

    @POST("AddUser/")
    Call<AddUserResponse> postAddUser(@Body User user);
    @POST("Login/")
    Call<LoginResponse> login(@Body Login login);
    @POST("DisplayCompanyUSers/")
    Call<AllUserInCompanyResponse> login(@Body DisplayAllUsersRequest displayAllUsersRequest);
    @POST("DisplayUserDetails/")
    Call<DisplayUserDetailsResponse> DISPLAY_USER_DETAILS_RESPONSE_CALL(@Body DisplayUserDetailsRequest displayUserDetailsRequest);
}
