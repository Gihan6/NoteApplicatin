package com.creativematrix.noteapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.Nullable;

import com.creativematrix.noteapp.util.CenterRepository;
import com.google.android.material.textfield.TextInputEditText;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.callback.RegisterCallbacks;
import com.creativematrix.noteapp.data.company.CheckEmailAndPhone;
import com.creativematrix.noteapp.data.company.Company;
import com.creativematrix.noteapp.data.company.CompanyRepo;
import com.creativematrix.noteapp.util.Utils;

import java.util.Locale;

public class CompanyRegisterFragment extends Fragment {
    private RegisterCallbacks mCallbacks;
    private Context mContext;
    private static final int RESULT_LOAD_IMAGE = 1;
    Bitmap bmImg;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mCallbacks = (RegisterCallbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    private TextInputEditText editTextCompanyName;
    private TextInputEditText editTextCompanyEmail;
    private TextInputEditText editTextCompanyPassword;
    private TextInputEditText editTextCompanyPasswordConfirmation;

    private TextView textViewAlreadyHaveAccount;

    private Button buttonCompanySignUp;

    public static final String TAG = CompanyRegisterFragment.class.getSimpleName();

    private String emailMessage;
    private String companyName;
    private String companyEmail;
    private String companyPassword;
    private  String logoPath;
    private String companyPasswordConfirmation;
    private boolean isEmailExist = false;
    CardView upload_photo;
   ImageView image_view_company_logo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        mContext = getContext();
        View view = inflater.inflate(R.layout.fragment_company_register, container, false);
        configureViews(view);
        buttonCompanySignUp.setOnClickListener(v -> {
            collectData();
        });
        textViewAlreadyHaveAccount.setOnClickListener(v -> {
            mCallbacks.onAlreadyHaveAccountClicked();
        });
        upload_photo.setOnClickListener(v -> {
            uploadPhoto();
        });
        return view;

    }

    private void configureViews(View view) {
        Log.d(TAG, "configureViews: " + Locale.getDefault().getDisplayLanguage());
        editTextCompanyName = view.findViewById(R.id.input_register_company_name);
        editTextCompanyEmail = view.findViewById(R.id.input_register_company_email);
        editTextCompanyPassword = view.findViewById(R.id.input_register_company_password);
        editTextCompanyPasswordConfirmation = view.findViewById(R.id.input_register_company_password_confirm);
        buttonCompanySignUp = view.findViewById(R.id.btn_company_register);
        textViewAlreadyHaveAccount = view.findViewById(R.id.text_view_login);
        upload_photo= view.findViewById(R.id.upload_photo);
        image_view_company_logo= view.findViewById(R.id.image_view_company_logo);
    }

    private void collectData() {
        String companyLang = Utils.getLang();
        companyName = editTextCompanyName.getText().toString();
        companyEmail = editTextCompanyEmail.getText().toString();
        companyPassword = editTextCompanyPassword.getText().toString();
        companyPasswordConfirmation = editTextCompanyPasswordConfirmation.getText().toString();
        if (Utils.isFieldEmpty(companyName)) {
            Utils.showResToast(mContext, R.string.company_name_empty_msg);
            return;
        }
        if (Utils.isFieldEmpty(companyEmail)) {
            Utils.showResToast(mContext, R.string.company_email_empty_msg);
            return;
        }
        if (Utils.isFieldEmpty(companyPassword)) {
            Utils.showResToast(mContext, R.string.company_password_empty_msg);
            return;
        }
        if (Utils.isFieldEmpty(companyPasswordConfirmation)) {
            Utils.showResToast(mContext, R.string.company_password_confirmation_empty_msg);
            return;
        }
        if (!companyPassword.equals(companyPasswordConfirmation)) {
            Utils.showResToast(mContext, R.string.company_password_not_identical);
            return;
        }
        CheckEmailAndPhone checkName = new CheckEmailAndPhone("", Utils.getLang(),
                companyName, "");
        new CompanyRepo(getActivity()).checkCompanyName(checkName).observe(this, checkEmailAndPhoneResponse -> {
            if (checkEmailAndPhoneResponse.flag.equals(String.valueOf(Constant.RESPONSE_EXISTING_COMPANY))) {
                isEmailExist = true;
                emailMessage = checkEmailAndPhoneResponse.Message;
                Utils.showStringToast(mContext, emailMessage);
                Log.d(TAG, "isEmailAlreadyExist: " + emailMessage);
            } else {
                isEmailExist = false;
                CheckEmailAndPhone checkEmail = new CheckEmailAndPhone("", Utils.getLang(),
                        companyEmail, "");
                new CompanyRepo(getActivity()).checkEmail(checkEmail).observe(this, checkEmailResponse -> {
                    if (checkEmailResponse.flag.equals(String.valueOf(Constant.RESPONSE_EXISTING_COMPANY))) {
                        isEmailExist = true;
                        emailMessage = checkEmailResponse.Message;
                        Utils.showStringToast(mContext, emailMessage);
                        Log.d(TAG, "isEmailAlreadyExist: " + emailMessage);
                    } else {
                        isEmailExist = false;
                        Company company;
                        company = new Company(companyLang, companyName, "", "",
                                "", "", companyEmail, companyPassword, Utils.BitMapToString(bmImg));
                        CenterRepository.getCenterRepository().setmCompany(company);
                        Log.d(TAG, "collectData: " + company.toString());
                        mCallbacks.onSignUpClicked(/*company*/);
                    }

                });

            }

        });

    }

    private void uploadPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_LOAD_IMAGE:
                if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && null != data) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.Pictur_Added_Successfully), Toast.LENGTH_LONG).show();
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    logoPath = cursor.getString(columnIndex);
                    cursor.close();
                     bmImg =Utils.createSmallImage(getActivity(),selectedImage);
                            image_view_company_logo.setImageBitmap(bmImg);
                            //BitmapFactory.decodeFile(logoPath);


                    break;
                }
        }
    }
}
