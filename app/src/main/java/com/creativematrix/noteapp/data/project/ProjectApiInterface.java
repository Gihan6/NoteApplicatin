package com.creativematrix.noteapp.data.project;

import com.creativematrix.noteapp.data.task.DisplayTaskRequest;
import com.creativematrix.noteapp.data.task.DisplayTasksResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProjectApiInterface {

    @POST("AddProject/")
    Call<Project> postAddProject(@Body Project project);
    @POST("DeleteProject/")
    Call<Project> postDeleteProject(@Body Project project);
    @POST("DispalyProjects/")
    Call<DisplayProjectsResponse> postDisplayProjects(@Body DisplayProjectRequest displayTaskRequest);
    @POST("DisplayProjectDetials/")
    Call<ProjectDetailsResponse> postDisplayProjectDetails(@Body Project project);
}
