package com.creativematrix.noteapp.fragments;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.callback.VerificationCallbacks;
import com.creativematrix.noteapp.data.company.CompanyRepo;
import com.creativematrix.noteapp.data.company.VerificationCode;
import com.creativematrix.noteapp.data.company.VerificationCodeResponse;
import com.creativematrix.noteapp.util.Utils;
import com.rilixtech.CountryCodePicker;

public class VerificationMobileFragment extends Fragment {
    private Context mContext;
    private VerificationCallbacks mCallbacks;

    public static final String TAG = VerificationMobileFragment.class.getSimpleName();
    private TextInputEditText editTextPhoneNumber;
    private CountryCodePicker countryCodePicker;
    private Button buttonSendSms;
    private VerificationCodeResponse mVerificationCodeResponse;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mCallbacks = (VerificationCallbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verification_mobile, container, false);
        configureViews(view);
        buttonSendSms.setOnClickListener(v -> {
            collectData();
        });
        return view;
    }

    private void configureViews(View view) {
        editTextPhoneNumber = view.findViewById(R.id.input_mobile_number);
        countryCodePicker = view.findViewById(R.id.ccp);
        countryCodePicker.registerPhoneNumberTextView(editTextPhoneNumber);
        buttonSendSms = view.findViewById(R.id.btn_send_sms);
    }

    private void collectData() {
        String mobilePhone = editTextPhoneNumber.getText().toString();
        if (Utils.isFieldEmpty(mobilePhone)) {
            Utils.showResToast(getContext(), R.string.mobile_field_msg);
            return;
        }
        String phoneNumber = countryCodePicker.getFullNumber();

        Log.d(TAG, "collectData: " + phoneNumber);
        VerificationCode verificationCode = new VerificationCode(Utils.getLang(), phoneNumber);
        new CompanyRepo(getActivity()).verifyCode(verificationCode).observe(this, verificationCodeResponse -> {
            if (verificationCodeResponse != null){
                mCallbacks.onSendSmsClicked(verificationCodeResponse,phoneNumber);
            }
        //TODO    Utils.showStringToast(getContext(), verificationCodeResponse.getMessage());
        });
    }


}
