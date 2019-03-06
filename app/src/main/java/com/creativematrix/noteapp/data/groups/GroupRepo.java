package com.creativematrix.noteapp.data.groups;

import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.data.ApiClient;
import com.creativematrix.noteapp.data.project.Project;
import com.creativematrix.noteapp.data.project.ProjectApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupRepo {
    private GroupsApiInterface mGroupsApiInterface;
    public static final String TAG = GroupRepo.class.getSimpleName();
    private Context mContext;
    ProgressDialog progressDoalog;

    public GroupRepo(Context context) {
        mGroupsApiInterface = ApiClient.getINSTANCE().create(GroupsApiInterface.class);
        mContext = context;
        if (progressDoalog == null) {
            progressDoalog = new ProgressDialog(mContext);
            //         progressDoalog.setMax(100);
            progressDoalog.setMessage(mContext.getResources().getString(R.string.please_wait));
            progressDoalog.setTitle(mContext.getResources().getString(R.string.loading));
            // progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        }
    }
    public void showDialog() {
        if (progressDoalog != null && !progressDoalog.isShowing())
            progressDoalog.show();
    }

    public void hideDialog() {
        if (progressDoalog != null && progressDoalog.isShowing())
            progressDoalog.dismiss();
    }
    public LiveData<Group> addGroup(Group group) {
        final MutableLiveData<Group> mutableLiveData = new MutableLiveData<>();
        Call<Group> groupCall = mGroupsApiInterface.postAddGroup(group);
        groupCall.enqueue(new Callback<Group>() {
            @Override
            public void onResponse(Call<Group> call, Response<Group> response) {
                Log.d(TAG, "onResponse: " + response.message());
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Group> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
    public LiveData<DisplayGroupResponse> displayGroups(DisplayGroupRequest displayGroupRequest) {
        final MutableLiveData<DisplayGroupResponse> mutableLiveData = new MutableLiveData<>();
        Call<DisplayGroupResponse> groupCall = mGroupsApiInterface.postDisplayGroups(displayGroupRequest);
        groupCall.enqueue(new Callback<DisplayGroupResponse>() {
            @Override
            public void onResponse(Call<DisplayGroupResponse> call, Response<DisplayGroupResponse> response) {
                Log.d(TAG, "onResponse: " + response.message());
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<DisplayGroupResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
