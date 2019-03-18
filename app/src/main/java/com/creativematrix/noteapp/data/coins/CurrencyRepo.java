package com.creativematrix.noteapp.data.coins;

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

public class CurrencyRepo {
    private CurrencyApiInterface currencyApiInterface;
    public static final String TAG = CurrencyRepo.class.getSimpleName();
    private Context mContext;
    ProgressDialog progressDoalog;

    public CurrencyRepo(Context context) {
        currencyApiInterface = ApiClient.getINSTANCE().create(CurrencyApiInterface.class);
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

    public LiveData<DisplayCurrencyResponse> displayCurrencyResponseLiveData(DisplayCurrencyRequest displayCurrencyRequest) {
        final MutableLiveData<DisplayCurrencyResponse> mutableLiveData = new MutableLiveData<>();
        Call<DisplayCurrencyResponse> groupCall = currencyApiInterface.postDisplayCurrnecy(displayCurrencyRequest);
        groupCall.enqueue(new Callback<DisplayCurrencyResponse>() {
            @Override
            public void onResponse(Call<DisplayCurrencyResponse> call, Response<DisplayCurrencyResponse> response) {
                Log.d(TAG, "onResponse: " + response.message());
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<DisplayCurrencyResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
