package com.creativematrix.noteapp.fragments;

import android.content.Context;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.callback.RegisterCallbacks;
import com.creativematrix.noteapp.data.company.Login;
import com.creativematrix.noteapp.data.user.UserRepo;
import com.creativematrix.noteapp.util.Utils;

public class UserLoginFragment extends Fragment {

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

    private TextInputEditText input_user_email_login;
    private TextInputEditText input_user_password_login;

    private TextView textViewDoNotHaveAccount;

    private Button btn_user_login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_login, container, false);
        configureViews(view);
        textViewDoNotHaveAccount.setOnClickListener(v -> mCallbacks.onDoNotHaveAccountClicked());
        btn_user_login.setOnClickListener(v -> collectData());
        return view;
    }

    private void configureViews(View view) {
        input_user_password_login = view.findViewById(R.id.input_user_password_login);
        input_user_email_login = view.findViewById(R.id.input_user_email_login);
        textViewDoNotHaveAccount = view.findViewById(R.id.text_view_signup);
        btn_user_login = view.findViewById(R.id.btn_user_login);
    }

    private void collectData() {
        String companyLang = Utils.getLang();
        // flag for detect admin
        int typeAdmin = 0;
        String userEmail = input_user_email_login.getText().toString();
        String userPassword = input_user_password_login.getText().toString();
        if (Utils.isFieldEmpty(userEmail)) {
            Utils.showResToast(mContext, R.string.user_email_empty_msg);
            return;
        }
        if (Utils.isFieldEmpty(userPassword)) {
            Utils.showResToast(mContext, R.string.user_password_empty_msg);
            return;
        }

        new UserRepo(getActivity())
                .login(new Login(companyLang, typeAdmin, userEmail, userPassword))
                .observe(this, loginResponse -> {
                    Utils.showStringToast(mContext, loginResponse.getMessageText() + " - "
                            + loginResponse.getIsAdmin());
                    mCallbacks.onLoginClicked(loginResponse, userEmail, userPassword,"","0");
                });

    }
}