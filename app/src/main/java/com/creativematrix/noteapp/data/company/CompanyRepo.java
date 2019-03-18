package com.creativematrix.noteapp.data.company;

import android.app.ProgressDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.data.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyRepo {
    private CompanyApiInterface mCompanyApiInterface;
    private static final String TAG = CompanyRepo.class.getSimpleName();
    private Context mContext;
    ProgressDialog progressDoalog;

    public void showDialog() {
        if (progressDoalog != null && !progressDoalog.isShowing())
            progressDoalog.show();
    }

    public void hideDialog() {

        if (progressDoalog != null && progressDoalog.isShowing())
            progressDoalog.dismiss();
    }

    public CompanyRepo(Context context) {
        mCompanyApiInterface = ApiClient.getINSTANCE().create(CompanyApiInterface.class);
        mContext = context;

        if (progressDoalog == null) {
            progressDoalog = new ProgressDialog(mContext);
   //         progressDoalog.setMax(100);
            progressDoalog.setMessage(mContext.getResources().getString(R.string.please_wait));
            progressDoalog.setTitle(mContext.getResources().getString(R.string.loading));
           // progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        }
    }

    public LiveData<CompanyResponse> addCompany(final Company company) {
        Call<CompanyResponse> companyCall = mCompanyApiInterface.postAddCompany(company);
        final MutableLiveData<CompanyResponse> liveData = new MutableLiveData<>();
        showDialog();
        // show it
        progressDoalog.show();
        companyCall.enqueue(new Callback<CompanyResponse>() {
            @Override
            public void onResponse(Call<CompanyResponse> call, Response<CompanyResponse> response) {
                Log.d(TAG, "onResponse: " + response.message());
                Log.d(TAG, "onResponse: " + response.body().toString());
                hideDialog();
                liveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<CompanyResponse> call, Throwable t) {
                hideDialog();
            }
        });
        return liveData;
    }

    public LiveData<LoginResponse> login(Login login) {
        final MutableLiveData<LoginResponse> liveData = new MutableLiveData<>();
        showDialog();
        mCompanyApiInterface.login(login).enqueue(new Callback<LoginResponse>() {
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

    public LiveData<VerificationCodeResponse> verifyCode(VerificationCode code) {
        final MutableLiveData<VerificationCodeResponse> liveData = new MutableLiveData<>();
        showDialog();
        mCompanyApiInterface.verify(code).enqueue(new Callback<VerificationCodeResponse>() {
            @Override
            public void onResponse(Call<VerificationCodeResponse> call, Response<VerificationCodeResponse> response) {
                Log.d(TAG, "onResponse: " + response.message());
                Log.d(TAG, "onResponse: " + response.body().toString());
                hideDialog();
                liveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<VerificationCodeResponse> call, Throwable t) {
                hideDialog();
            }
        });
        return liveData;
    }

    public LiveData<CheckEmailAndPhone> checkEmail(CheckEmailAndPhone email) {
        final MutableLiveData<CheckEmailAndPhone> liveData = new MutableLiveData<>();
        showDialog();
        mCompanyApiInterface.checkMail(email)
                .enqueue(new Callback<CheckEmailAndPhone>() {
                    @Override
                    public void onResponse(Call<CheckEmailAndPhone> call, Response<CheckEmailAndPhone> response) {
                        liveData.postValue(response.body());
                        Log.d(TAG, "onResponse: " + response.message());
                        hideDialog();
                    }

                    @Override
                    public void onFailure(Call<CheckEmailAndPhone> call, Throwable t) {
                        hideDialog();
                    }
                });
        return liveData;
    }

    public LiveData<CheckEmailAndPhone> checkCompanyName(CheckEmailAndPhone name) {
        final MutableLiveData<CheckEmailAndPhone> liveData = new MutableLiveData<>();
        showDialog();
        mCompanyApiInterface.checkName(name)
                .enqueue(new Callback<CheckEmailAndPhone>() {
                    @Override
                    public void onResponse(Call<CheckEmailAndPhone> call, Response<CheckEmailAndPhone> response) {
                        liveData.postValue(response.body());
                        hideDialog();
                        Log.d(TAG, "onResponse: " + response.message());
                    }

                    @Override
                    public void onFailure(Call<CheckEmailAndPhone> call, Throwable t) {
                        hideDialog();

                    }
                });
        return liveData;
    }

}
