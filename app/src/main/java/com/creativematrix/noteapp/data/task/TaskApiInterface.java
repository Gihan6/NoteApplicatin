package com.creativematrix.noteapp.data.task;

import com.creativematrix.noteapp.data.groups.DisplayGroupRequest;
import com.creativematrix.noteapp.data.groups.DisplayGroupResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TaskApiInterface {

    @POST("AddTask/")
    Call<Task> postAddTask(@Body Task task);

    @POST("DisplayTasks/")
    Call<DisplayTasksResponse> postDisplayTasks(@Body DisplayTaskRequest displayTaskRequest);

    @POST("GetUsersInCompany/")
    Call<GetUsersInCompanyResponse> postGetUsersInCompany(@Body DisplayTaskRequest displayTaskRequest);
}
