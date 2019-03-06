package com.creativematrix.noteapp.data.groups;

import com.creativematrix.noteapp.data.project.Project;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GroupsApiInterface {

    @POST("AddGroup/")
    Call<Group> postAddGroup(@Body Group group);

    @POST("DisplayGroup/")
    Call<DisplayGroupResponse> postDisplayGroups(@Body DisplayGroupRequest displayGroupRequest);
}
