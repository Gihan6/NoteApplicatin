package com.creativematrix.noteapp.data.user;

import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.data.ApiClient;
import com.creativematrix.noteapp.data.company.Login;
import com.creativematrix.noteapp.data.company.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepo {
    private UserApiInterface mUserApiInterface;
    public static final String TAG = UserRepo.class.getSimpleName();
      private Context mContext;
      ProgressDialog progressDoalog;
      public UserRepo(Context context) {
        mUserApiInterface = ApiClient.getINSTANCE().create(UserApiInterface.class);
        mContext = context;

        if (progressDoalog == null) {
            progressDoalog = new ProgressDialog(mContext);
            //         progressDoalog.setMax(100);
            progressDoalog.setMessage(mContext.getResources().getString(R.string.please_wait));
            progressDoalog.setTitle(mContext.getResources().getString(R.string.loading));
            // progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        }
    }
    public LiveData<LoginResponse> login(Login login) {
        final MutableLiveData<LoginResponse> liveData = new MutableLiveData<>();
        showDialog();
        mUserApiInterface.login(login).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d(TAG, "onResponse: " + response.message());
                Log.d(TAG, "onResponse: " + response.body().toString());
                hideDialog();
                liveData.postValue(response.body());

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                hideDialog();
            }
        });
        return liveData;
    }
    public void showDialog() {

        if (progressDoalog != null && !progressDoalog.isShowing())
            progressDoalog.show();
    }

    public void hideDialog() {

        if (progressDoalog != null && progressDoalog.isShowing())
            progressDoalog.dismiss();
    }

    public void addUser(User user) {
        Call<User> userCall = mUserApiInterface.postAddUser(user);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, "onResponse: " + response.message());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
