package com.creativematrix.noteapp.data.task;

import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.data.ApiClient;
import com.creativematrix.noteapp.data.company.CompanyApiInterface;
import com.creativematrix.noteapp.data.groups.DisplayGroupRequest;
import com.creativematrix.noteapp.data.groups.DisplayGroupResponse;

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


    public void addTask(Task task) {
        Call<Task> taskCall = mTaskApiInterface.postAddTask(task);
        showDialog();
        taskCall.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                Log.d(TAG, "onResponse: " + response.message());
                hideDialog();
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                hideDialog();
            }
        });
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
}
