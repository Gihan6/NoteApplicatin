package com.creativematrix.noteapp.data.task;

import com.creativematrix.noteapp.firebase.UpdateTockenRequest;
import com.creativematrix.noteapp.firebase.UpdateTockenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TaskApiInterface {

    @POST("AddTask/")
    Call<Task> postAddTask(@Body Task task);

    @POST("DeleteTask/")
    Call<DeleteTaskResponse> postDeleteTask(@Body Task task);

    @POST("DisplayTasks/")
    Call<DisplayTasksResponse> postDisplayTasks(@Body DisplayTaskRequest displayTaskRequest);

    @POST("GetUsersInCompany/")
    Call<GetUsersInCompanyResponse> postGetUsersInCompany(@Body DisplayTaskRequest displayTaskRequest);

    @POST("DisplayTaskesInDetalies/")
    Call<DisplayTaskDetailsResponse> postGetTaskDetails(@Body Task task);

    @POST("EditTask/")
    Call<Task> postEditTask(@Body Task task);

    @POST("UpdateAccessToken")
    Call<UpdateTockenResponse> updateTocken(@Body UpdateTockenRequest request);

}
