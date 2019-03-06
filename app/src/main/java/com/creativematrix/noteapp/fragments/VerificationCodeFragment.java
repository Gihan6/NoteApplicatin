package com.creativematrix.noteapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.callback.VerificationCallbacks;
import com.creativematrix.noteapp.util.Utils;

public class VerificationCodeFragment extends Fragment {
    private Context mContext;
    private VerificationCallbacks mCallbacks;
    private TextInputEditText editTextVerificationCode;
    private Button buttonVerifyCode;
    private String code;

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mCallbacks = (VerificationCallbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verification_code, container, false);
        configureViews(view);
        buttonVerifyCode.setOnClickListener(v -> {

            collectData();
        });
        return view;
    }

    private void configureViews(View view) {
        editTextVerificationCode = view.findViewById(R.id.input_verification_code);
        buttonVerifyCode = view.findViewById(R.id.btn_verify_code);
    }

    private void collectData() {
        String verificationCode = editTextVerificationCode.getText().toString();
        if (Utils.isFieldEmpty(verificationCode)) {
            Utils.showResToast(mContext, R.string.verification_code_empty_msg);
            return;
        }
        if (!verificationCode.equals(code)){
            Utils.showResToast(mContext,R.string.code_does_not_match);
            return;
        }else {
            mCallbacks.onVerifyCodeClicked();
        }

    }
}
