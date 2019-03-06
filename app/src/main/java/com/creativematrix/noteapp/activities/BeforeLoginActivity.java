package com.creativematrix.noteapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.creativematrix.noteapp.R;

public class BeforeLoginActivity extends AppCompatActivity {
Button btn_company_login,btn_user_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_login);
        configureViews();
        btn_company_login.setOnClickListener(view -> openCompanyLoginRegisterActivity());
        btn_user_login.setOnClickListener(view -> openUserLoginRegisterActivity());

    }

    private void openCompanyLoginRegisterActivity() {
        startActivity(new Intent(BeforeLoginActivity.this,CompanyLoginRegisterActivity.class));
    }
    private void openUserLoginRegisterActivity() {
        startActivity(new Intent(BeforeLoginActivity.this,UserLoginRegisterActivity.class));
    }
    private void configureViews() {
        btn_company_login = findViewById(R.id.btn_company_login);
        btn_user_login = findViewById(R.id.btn_user_login);
    }
}
