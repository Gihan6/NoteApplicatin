package com.creativematrix.noteapp.activities;

import android.content.Intent;

import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.callback.VerificationCallbacks;
import com.creativematrix.noteapp.data.company.Company;
import com.creativematrix.noteapp.data.company.CompanyRepo;
import com.creativematrix.noteapp.data.company.VerificationCodeResponse;
import com.creativematrix.noteapp.fragments.VerificationCodeFragment;
import com.creativematrix.noteapp.fragments.VerificationMobileFragment;
import com.creativematrix.noteapp.util.PreferenceHelper;
import com.creativematrix.noteapp.util.Utils;

public class VerificationActivity extends AppCompatActivity implements VerificationCallbacks {
    public static final String TAG = VerificationActivity.class.getSimpleName();
    private FragmentManager mFragmentManager;
    private VerificationMobileFragment verificationMobileFragment;
    private VerificationCodeFragment verificationCodeFragment;
    private Company company;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        mFragmentManager = getSupportFragmentManager();
        verificationCodeFragment = new VerificationCodeFragment();
        verificationMobileFragment = new VerificationMobileFragment();
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_holder_verification, verificationMobileFragment)
                .commit();
        Intent intent = getIntent();
        company = (Company) intent.getSerializableExtra("company");
        if (company != null) {
            Log.d(TAG, "onCreate: " + company.toString());
        }
    }

    @Override
    public void onSendSmsClicked(VerificationCodeResponse verificationCodeResponse, String phoneNumber) {
        verificationCodeFragment.setCode(verificationCodeResponse.getCode());
        Utils.showStringToast(VerificationActivity.this,verificationCodeResponse.getCode());
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_holder_verification, verificationCodeFragment)
                .commit();
        if (company != null) {
            company.CompanyPhone = phoneNumber;
            Log.d(TAG, "onSendSmsClicked: " + company.toString());
        }
    }

    @Override
    public void onVerifyCodeClicked() {
        // if code match go to tasks activity
        Utils.showStringToast(this, "done");
        new CompanyRepo(VerificationActivity.this).addCompany(company).observe(this, companyResponse -> {
            String msg = companyResponse.getMessage();
            Utils.showStringToast(this, msg);
            Log.d(TAG, "onVerifyCodeClicked: " + companyResponse.toString());
            int flag = Integer.parseInt(companyResponse.getFlag());
            if (Constant.RESPONSE_ADD_NEW_COMPANY == flag) {
                PreferenceHelper.getPrefernceHelperInstace().storeCompanyData(this, String.valueOf(companyResponse.getCompanyID()), company.Username, company.Password,company.LogoPath,"1");
                PreferenceHelper.getPrefernceHelperInstace().setUserLoggedIn(true,VerificationActivity.this);
                Intent intent = new Intent(this, NoteHomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }


}
