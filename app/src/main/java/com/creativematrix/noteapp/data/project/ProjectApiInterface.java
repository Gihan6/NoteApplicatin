package com.creativematrix.noteapp.data.project;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProjectApiInterface {

    @POST("AddProject/")
    Call<Project> postAddProject(@Body Project project);
}
