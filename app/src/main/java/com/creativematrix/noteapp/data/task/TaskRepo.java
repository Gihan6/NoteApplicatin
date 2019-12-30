package com.creativematrix.noteapp.data.task;

import android.app.ProgressDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.data.ApiClient;
import com.creativematrix.noteapp.firebase.UpdateTockenRequest;
import com.creativematrix.noteapp.firebase.UpdateTockenResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskRepo {
    private TaskApiInterface mTaskApiInterface;
    public static final String TAG = TaskRepo.class.getSimpleName();
    private Context mContext;

    public TaskRepo(Context context) {
        mTaskApiInterface = ApiClient.getINSTANCE().create(TaskApiInterface.class);
        mContext = context;
        if (progressDoalog == null) {
            progressDoalog = new ProgressDialog(mContext);
            //         progressDoalog.setMax(100);
            progressDoalog.setMessage(mContext.getResources().getString(R.string.please_wait));
            progressDoalog.setTitle(mContext.getResources().getString(R.string.loading));
            // progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        }
    }

    ProgressDialog progressDoalog;

    public void showDialog() {

        if (progressDoalog != null && !progressDoalog.isShowing())
            progressDoalog.show();
    }

    public void hideDialog() {

        if (progressDoalog != null && progressDoalog.isShowing())
            progressDoalog.dismiss();
    }

    public LiveData<Task> addTask(Task task) {
        final MutableLiveData<Task> mutableLiveData = new MutableLiveData<>();
        showDialog();
        Call<Task> tasksResponseCall = mTaskApiInterface.postAddTask(task);
        tasksResponseCall.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                Log.d(TAG, "onResponse: " + response.message());
                mutableLiveData.postValue(response.body());
                hideDialog();
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                hideDialog();
            }
        });
        return mutableLiveData;
    }

    public LiveData<UpdateTockenResponse> updateTocken(UpdateTockenRequest request) {
        final MutableLiveData<UpdateTockenResponse> mutableLiveData = new MutableLiveData<>();
        Call<UpdateTockenResponse> tasksResponseCall = mTaskApiInterface.updateTocken(request);
        tasksResponseCall.enqueue(new Callback<UpdateTockenResponse>() {
            @Override
            public void onResponse(Call<UpdateTockenResponse> call, Response<UpdateTockenResponse> response) {
                Log.d(TAG, "onResponse: " + response.message());
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<UpdateTockenResponse> call, Throwable t) {
            }
        });
        return mutableLiveData;
    }

    public LiveData<Task> EditTask(Task task) {
        final MutableLiveData<Task> mutableLiveData = new MutableLiveData<>();
        showDialog();
        Call<Task> tasksResponseCall = mTaskApiInterface.postEditTask(task);
        tasksResponseCall.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                Log.d(TAG, "onResponse: " + response.message());
                mutableLiveData.postValue(response.body());
                hideDialog();
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                hideDialog();
            }
        });
        return mutableLiveData;
    }
    public LiveData<DeleteTaskResponse> deleteTask(Task task) {
        final MutableLiveData<DeleteTaskResponse> mutableLiveData = new MutableLiveData<>();
        showDialog();
        Call<DeleteTaskResponse> tasksResponseCall = mTaskApiInterface.postDeleteTask(task);
        tasksResponseCall.enqueue(new Callback<DeleteTaskResponse>() {
            @Override
            public void onResponse(Call<DeleteTaskResponse> call, Response<DeleteTaskResponse> response) {
                Log.d(TAG, "onResponse: " + response.message());
                mutableLiveData.postValue(response.body());
                hideDialog();
            }

            @Override
            public void onFailure(Call<DeleteTaskResponse> call, Throwable t) {
                hideDialog();
            }
        });
        return mutableLiveData;
    }
    public LiveData<DisplayTaskDetailsResponse> displayTaskDetails(Task task) {
        final MutableLiveData<DisplayTaskDetailsResponse> mutableLiveData = new MutableLiveData<>();
        showDialog();
        Call<DisplayTaskDetailsResponse> tasksResponseCall = mTaskApiInterface.postGetTaskDetails(task);
        tasksResponseCall.enqueue(new Callback<DisplayTaskDetailsResponse>() {
            @Override
            public void onResponse(Call<DisplayTaskDetailsResponse> call, Response<DisplayTaskDetailsResponse> response) {
                Log.d(TAG, "onResponse: " + response.message());
                mutableLiveData.postValue(response.body());
                hideDialog();
            }

            @Override
            public void onFailure(Call<DisplayTaskDetailsResponse> call, Throwable t) {
                hideDialog();
            }
        });
        return mutableLiveData;
    }

    public LiveData<DisplayTasksResponse> displayTasks(DisplayTaskRequest displayTaskRequest) {
        final MutableLiveData<DisplayTasksResponse> mutableLiveData = new MutableLiveData<>();
        showDialog();
        Call<DisplayTasksResponse> tasksResponseCall = mTaskApiInterface.postDisplayTasks(displayTaskRequest);
        tasksResponseCall.enqueue(new Callback<DisplayTasksResponse>() {
            @Override
            public void onResponse(Call<DisplayTasksResponse> call, Response<DisplayTasksResponse> response) {
                Log.d(TAG, "onResponse: " + response.message());
                mutableLiveData.postValue(response.body());
                hideDialog();
            }

            @Override
            public void onFailure(Call<DisplayTasksResponse> call, Throwable t) {
                hideDialog();
            }
        });
        return mutableLiveData;
    }

    public LiveData<GetUsersInCompanyResponse> getUsersInCompanyResponseLiveData(DisplayTaskRequest displayTaskRequest) {
        final MutableLiveData<GetUsersInCompanyResponse> mutableLiveData = new MutableLiveData<>();
        showDialog();
        Call<GetUsersInCompanyResponse> tasksResponseCall = mTaskApiInterface.postGetUsersInCompany(displayTaskRequest);
        tasksResponseCall.enqueue(new Callback<GetUsersInCompanyResponse>() {
            @Override
            public void onResponse(Call<GetUsersInCompanyResponse> call, Response<GetUsersInCompanyResponse> response) {
                Log.d(TAG, "onResponse: " + response.body());
                mutableLiveData.postValue(response.body());
                hideDialog();
            }

            @Override
            public void onFailure(Call<GetUsersInCompanyResponse> call, Throwable t) {
                hideDialog();
            }
        });
        return mutableLiveData;
    }

}
