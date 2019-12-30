package com.creativematrix.noteapp.data.user;

import android.app.ProgressDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

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
    public LiveData<AllUserInCompanyResponse> DisplayAllUsers(DisplayAllUsersRequest displayAllUsersRequest) {
        final MutableLiveData<AllUserInCompanyResponse> liveData = new MutableLiveData<>();
        showDialog();
        mUserApiInterface.login(displayAllUsersRequest).enqueue(new Callback<AllUserInCompanyResponse>() {
            @Override
            public void onResponse(Call<AllUserInCompanyResponse> call, Response<AllUserInCompanyResponse> response) {
                Log.d(TAG, "onResponse: " + response.message());
                Log.d(TAG, "onResponse: " + response.body().toString());
                hideDialog();
                liveData.postValue(response.body());

            }

            @Override
            public void onFailure(Call<AllUserInCompanyResponse> call, Throwable t) {
                hideDialog();
            }
        });
        return liveData;
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


    public LiveData<AddUserResponse> addUser(User user) {
        final MutableLiveData<AddUserResponse> liveData = new MutableLiveData<>();
        showDialog();
        mUserApiInterface.postAddUser(user).enqueue(new Callback<AddUserResponse>() {
            @Override
            public void onResponse(Call<AddUserResponse> call, Response<AddUserResponse> response) {
                Log.d(TAG, "onResponse: " + response.message());
                Log.d(TAG, "onResponse: " + response.body().toString());
                hideDialog();
                liveData.postValue(response.body());

            }

            @Override
            public void onFailure(Call<AddUserResponse> call, Throwable t) {
                hideDialog();
            }
        });
        return liveData;
    }
    public LiveData<UpdateUserResponse> updateUser(User user) {
        final MutableLiveData<UpdateUserResponse> liveData = new MutableLiveData<>();
        showDialog();
        mUserApiInterface.postEditUser(user).enqueue(new Callback<UpdateUserResponse>() {
            @Override
            public void onResponse(Call<UpdateUserResponse> call, Response<UpdateUserResponse> response) {
                Log.d(TAG, "onResponse: " + response.message());
                Log.d(TAG, "onResponse: " + response.body().toString());
                hideDialog();
                liveData.postValue(response.body());

            }

            @Override
            public void onFailure(Call<UpdateUserResponse> call, Throwable t) {
                hideDialog();
            }
        });
        return liveData;
    }
    public LiveData<DeleteUserResponse> deleteUser(LstUsers user) {
        final MutableLiveData<DeleteUserResponse> liveData = new MutableLiveData<>();
        showDialog();
        mUserApiInterface.postDeleteUser(user).enqueue(new Callback<DeleteUserResponse>() {
            @Override
            public void onResponse(Call<DeleteUserResponse> call, Response<DeleteUserResponse> response) {
                Log.d(TAG, "onResponse: " + response.message());
                Log.d(TAG, "onResponse: " + response.body().toString());
                hideDialog();
                liveData.postValue(response.body());

            }

            @Override
            public void onFailure(Call<DeleteUserResponse> call, Throwable t) {
                hideDialog();
            }
        });
        return liveData;
    }
    public LiveData<DisplayUserDetailsResponse> displayUserDetailsResponseLiveData(DisplayUserDetailsRequest displayUserDetailsRequest) {
        final MutableLiveData<DisplayUserDetailsResponse> liveData = new MutableLiveData<>();
        showDialog();
        mUserApiInterface.DISPLAY_USER_DETAILS_RESPONSE_CALL(displayUserDetailsRequest).enqueue(new Callback<DisplayUserDetailsResponse>() {
            @Override
            public void onResponse(Call<DisplayUserDetailsResponse> call, Response<DisplayUserDetailsResponse> response) {

                hideDialog();
                liveData.postValue(response.body());

            }

            @Override
            public void onFailure(Call<DisplayUserDetailsResponse> call, Throwable t) {
                hideDialog();
            }
        });
        return liveData;
    }
}
