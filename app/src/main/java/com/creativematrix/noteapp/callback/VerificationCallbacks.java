package com.creativematrix.noteapp.callback;

import com.creativematrix.noteapp.data.company.VerificationCodeResponse;

public interface VerificationCallbacks {
    public void onSendSmsClicked(VerificationCodeResponse verificationCodeResponse, String phoneNumber);

    public void onVerifyCodeClicked();
}
