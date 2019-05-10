package com.creativematrix.noteapp.data.groups;

import com.creativematrix.noteapp.data.project.Project;
import com.creativematrix.noteapp.data.user.DisplayUserDetailsRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GroupsApiInterface {

    @POST("DeleteGroup/")
    Call<DeleteGroupResponse> postDeleteGroup(@Body Group group);
    @POST("AddGroup/")
    Call<Group> postAddGroup(@Body Group group);
    @POST("DisplayGroup/")
    Call<DisplayGroupResponse> postDisplayGroups(@Body DisplayGroupRequest displayGroupRequest);
    @POST("EditGroups/")
    Call<Group> postUpdateGroup(@Body Group group);
    @POST("DisplayGroupAllData/")
    Call<DisplayGroupDetailsResponse> DISPLAY_GROUP_DETAILS_RESPONSE_CALL(@Body DisplayGroupDetailsRequest displayUserDetailsRequest);
}
