package com.creativematrix.noteapp.data.user;

import com.creativematrix.noteapp.data.company.Login;
import com.creativematrix.noteapp.data.company.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApiInterface {

    @POST("AddUser/")
    Call<User> postAddUser(@Body User user);
    @POST("Login/")
    Call<LoginResponse> login(@Body Login login);

}
