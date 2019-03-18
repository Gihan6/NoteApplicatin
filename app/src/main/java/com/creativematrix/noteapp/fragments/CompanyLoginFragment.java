package com.creativematrix.noteapp.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.callback.RegisterCallbacks;
import com.creativematrix.noteapp.data.company.CompanyRepo;
import com.creativematrix.noteapp.data.company.Login;
import com.creativematrix.noteapp.util.Utils;

public class CompanyLoginFragment extends Fragment {

    private RegisterCallbacks mCallbacks;
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (RegisterCallbacks) context;
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    private TextInputEditText editTextCompanyEmailLogin;
    private TextInputEditText editTextCompanyPasswordLogin;

    private TextView textViewDoNotHaveAccount;

    private Button buttonCompanyLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company_login, container, false);
        configureViews(view);
        textViewDoNotHaveAccount.setOnClickListener(v -> mCallbacks.onDoNotHaveAccountClicked());
        buttonCompanyLogin.setOnClickListener(v -> collectData());
        return view;
    }

    private void configureViews(View view) {
        editTextCompanyEmailLogin = view.findViewById(R.id.input_company_email_login);
        editTextCompanyPasswordLogin = view.findViewById(R.id.input_company_password_login);
        textViewDoNotHaveAccount = view.findViewById(R.id.text_view_signup);
        buttonCompanyLogin = view.findViewById(R.id.btn_company_login);
    }

    private void collectData() {
        String companyLang = Utils.getLang();
        // flag for detect admin
        int typeAdmin = 1;
        String companyEmail = editTextCompanyEmailLogin.getText().toString();
        String companyPassword = editTextCompanyPasswordLogin.getText().toString();
        if (Utils.isFieldEmpty(companyEmail)) {
            Utils.showResToast(mContext, R.string.company_email_empty_msg);
            return;
        }
        if (Utils.isFieldEmpty(companyPassword)) {
            Utils.showResToast(mContext, R.string.company_password_empty_msg);
            return;
        }

        new CompanyRepo(getActivity())
                .login(new Login(companyLang, typeAdmin, companyEmail, companyPassword))
                .observe(this, loginResponse -> {
                    Utils.showStringToast(mContext, loginResponse.getMessageText() + " - "
                            + loginResponse.getIsAdmin());
                    mCallbacks.onLoginClicked(loginResponse, companyEmail, companyPassword,"","1");
                });

    }


}
