package com.creativematrix.noteapp.data.project;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.data.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectRepo {
    private ProjectApiInterface mProjectApiInterface;
    public static final String TAG = ProjectRepo.class.getSimpleName();
    ProgressDialog progressDoalog;
    private Context mContext;

    public ProjectRepo(Context context) {
        mProjectApiInterface = ApiClient.getINSTANCE().create(ProjectApiInterface.class);
        mContext = context;
        if (progressDoalog == null) {
            progressDoalog = new ProgressDialog(mContext);
            progressDoalog.setMessage(mContext.getResources().getString(R.string.please_wait));
            progressDoalog.setTitle(mContext.getResources().getString(R.string.loading));
        }
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

    public LiveData<Project> updateProject(Project project) {
        final MutableLiveData<Project> mutableLiveData = new MutableLiveData<>();
        Call<Project> projectCall = mProjectApiInterface.postUpdateProject(project);
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
    public LiveData<Project> deleteProject(Project project) {
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
    public void showDialog() {

        if (progressDoalog != null && !progressDoalog.isShowing())
            progressDoalog.show();
    }

    public void hideDialog() {

        if (progressDoalog != null && progressDoalog.isShowing())
            progressDoalog.dismiss();
    }
    public LiveData<ProjectDetailsResponse> displayProjectDeitals(Project project) {
        final MutableLiveData<ProjectDetailsResponse> mutableLiveData = new MutableLiveData<>();
        Call<ProjectDetailsResponse> projectCall = mProjectApiInterface.postDisplayProjectDetails(project);
        projectCall.enqueue(new Callback<ProjectDetailsResponse>() {
            @Override
            public void onResponse(Call<ProjectDetailsResponse> call, Response<ProjectDetailsResponse> response) {
                Log.d(TAG, "onResponse: " + response.message());
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ProjectDetailsResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
    public LiveData<DisplayProjectsResponse> displayProjectsResponseLiveData(DisplayProjectRequest displayProjectRequest) {
        final MutableLiveData<DisplayProjectsResponse> mutableLiveData = new MutableLiveData<>();
        showDialog();
        Call<DisplayProjectsResponse> tasksResponseCall = mProjectApiInterface.postDisplayProjects(displayProjectRequest);
        tasksResponseCall.enqueue(new Callback<DisplayProjectsResponse>() {
            @Override
            public void onResponse(Call<DisplayProjectsResponse> call, Response<DisplayProjectsResponse> response) {
                Log.d(TAG, "onResponse: " + response.body());
                mutableLiveData.postValue(response.body());
              hideDialog();
            }

            @Override
            public void onFailure(Call<DisplayProjectsResponse> call, Throwable t) {
                hideDialog();
            }
        });
        return mutableLiveData;
    }
}
