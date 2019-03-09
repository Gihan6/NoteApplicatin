package com.creativematrix.noteapp.data.project;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.creativematrix.noteapp.data.ApiClient;
import com.creativematrix.noteapp.data.task.DisplayTaskRequest;
import com.creativematrix.noteapp.data.task.GetUsersInCompanyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectRepo {
    private ProjectApiInterface mProjectApiInterface;
    public static final String TAG = ProjectRepo.class.getSimpleName();

    public ProjectRepo(Context context) {
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

    public LiveData<DisplayProjectsResponse> displayProjectsResponseLiveData(DisplayProjectRequest displayProjectRequest) {
        final MutableLiveData<DisplayProjectsResponse> mutableLiveData = new MutableLiveData<>();
        //showDialog();
        Call<DisplayProjectsResponse> tasksResponseCall = mProjectApiInterface.postDisplayProjects(displayProjectRequest);
        tasksResponseCall.enqueue(new Callback<DisplayProjectsResponse>() {
            @Override
            public void onResponse(Call<DisplayProjectsResponse> call, Response<DisplayProjectsResponse> response) {
                Log.d(TAG, "onResponse: " + response.body());
                mutableLiveData.postValue(response.body());
              //  hideDialog();
            }

            @Override
            public void onFailure(Call<DisplayProjectsResponse> call, Throwable t) {
               // hideDialog();
            }
        });
        return mutableLiveData;
    }
}
