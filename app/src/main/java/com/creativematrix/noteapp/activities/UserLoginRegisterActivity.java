package com.creativematrix.noteapp.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.callback.RegisterCallbacks;
import com.creativematrix.noteapp.data.company.Company;
import com.creativematrix.noteapp.data.company.LoginResponse;
import com.creativematrix.noteapp.fragments.CompanyLoginFragment;
import com.creativematrix.noteapp.fragments.UserLoginFragment;
import com.creativematrix.noteapp.util.PreferenceHelper;

public class UserLoginRegisterActivity extends AppCompatActivity implements RegisterCallbacks {
    public static final String TAG = UserLoginRegisterActivity.class.getSimpleName();
    private FragmentManager mFragmentManager;
    private UserLoginFragment userLoginFragment;
    private CompanyLoginFragment companyLoginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login_register);

            mFragmentManager = getSupportFragmentManager();
            userLoginFragment = new UserLoginFragment();
          //  companyLoginFragment = new CompanyLoginFragment();
            mFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_holder, userLoginFragment)
                    .commit();



    }

    @Override
    public void onAlreadyHaveAccountClicked() {
        mFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_holder, companyLoginFragment)
                .commit();
    }

    @Override
    public void onDoNotHaveAccountClicked() {
       /* mFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_holder, companyRegisterFragment)
                .commit();*/
    }

    @Override
    public void onSignUpClicked(/*Company company*/) {
        Intent intent = new Intent(this, VerificationActivity.class);
        //intent.putExtra("company", company);
        startActivity(intent);
    }

    @Override
    public void onLoginClicked(LoginResponse loginResponse, String email, String password,String logoPath,String Type) {
        Log.d(TAG, "onLoginClicked: " + loginResponse.toString() + " - " + email);
        if (loginResponse.getFlag().equals(String.valueOf(Constant.RESPONSE_ADD_NEW_COMPANY))) {
            // go to new activity
            PreferenceHelper.getPrefernceHelperInstace().storeCompanyData(this, String.valueOf(loginResponse.getiD()), email, password,loginResponse.getPhotoLink(),Type);
            PreferenceHelper.getPrefernceHelperInstace().setUserLoggedIn(true, UserLoginRegisterActivity.this);
            PreferenceHelper.getPrefernceHelperInstace().setIscompany(false,UserLoginRegisterActivity.this);
            Intent intent = new Intent(this, NoteHomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {

        }
    }


    //        new CompanyRepo().addCompany(new Company(
//                Constant.AR_LANG, "موسسة الابداع", "",
//                "", "", "",
//                "md123@mail.com", "1234", ""
//        ));
//        new CompanyRepo().login(new Login(Constant.AR_LANG, Constant.COMPANY_FLAG,"md123@mail.com", "1234"));
//        new ProjectRepo().addProject(new Project("NoteAPP","2019-01-17 01:10",
//                "2019-01-19 01:11"
//                , "MMM", "business", "1000", 31, "", Constant.AR_LANG
//                , 0, "1 - 2"));
//        new UserRepo().addUser(new User("mahmoud@gmail.com","01095477923",
//                "123",4,Constant.AR_LANG,""));

//        new TaskRepo().addTask(new Task(
//                "Design4","2019-01-17 01:10","2019-01-19 01:11"
//                ,"",2000,4,true,"",Constant.AR_LANG,
//                0,"3",5,null
//        ));
}
