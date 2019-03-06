package com.creativematrix.noteapp.data.project;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.creativematrix.noteapp.data.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectRepo {
    private ProjectApiInterface mProjectApiInterface;
    public static final String TAG = ProjectRepo.class.getSimpleName();

    public ProjectRepo() {
        mProjectApiInterface = ApiClient.getINSTANCE().create(ProjectApiInterface.class);
    }

    public LiveData<Project> addProject(Project project) {
        final MutableLiveData<Project> mutableLiveData = new MutableLiveData<>();
        Call<Project> projectCall = mProjectApiInterface.postAddProject(project);
        projectCall.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                Log.d(TAG, "onResponse: " + response.message());
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
