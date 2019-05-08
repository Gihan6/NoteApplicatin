package com.creativematrix.noteapp.callback;

import com.creativematrix.noteapp.data.company.Company;
import com.creativematrix.noteapp.data.company.LoginResponse;

public interface RegisterCallbacks {
    public void onAlreadyHaveAccountClicked();

    public void onDoNotHaveAccountClicked();

    public void onSignUpClicked(/*Company company*/);

    public void onLoginClicked(LoginResponse loginResponse, String email,String password,String logoPath,String Type);
}
