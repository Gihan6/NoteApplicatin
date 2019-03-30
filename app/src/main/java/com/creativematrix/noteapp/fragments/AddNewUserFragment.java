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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.activities.NoteHomeActivity;
import com.creativematrix.noteapp.activities.SelectGroupActivity;
import com.creativematrix.noteapp.activities.SelectTaskStatusActivity;
import com.creativematrix.noteapp.callback.ProjectCallbacks;
import com.creativematrix.noteapp.data.groups.Group;
import com.creativematrix.noteapp.data.groups.GroupRepo;
import com.creativematrix.noteapp.data.groups.LstGroup;
import com.creativematrix.noteapp.data.project.Project;
import com.creativematrix.noteapp.data.task.LstUsersnCompnay;
import com.creativematrix.noteapp.data.user.LstUsers;
import com.creativematrix.noteapp.data.user.User;
import com.creativematrix.noteapp.data.user.UserRepo;
import com.creativematrix.noteapp.util.PreferenceHelper;
import com.creativematrix.noteapp.util.Utils;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class AddNewUserFragment extends Fragment {
    private ProjectCallbacks mCallbacks;
    public static final String TAG = AddNewProjectFragment.class.getSimpleName();
    private TextInputEditText input_register_user_email, input_register_user_phone, input_register_user_password, input_user_group;
    private List<LstGroup> lstGroups = new ArrayList<>();

    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int GET_GROUPS = 28;
    String SelectedGroupName;
    private Button btn_save_user;
    private Context mContext;
    Toolbar toolbar;
    String logoPath;
    CardView upload_photo;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mCallbacks = (ProjectCallbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_user, container, false);
        configureViews(view);

        btn_save_user.setOnClickListener(v -> {
            collectData();
        });
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        input_user_group.setOnClickListener(v -> {
            startActivityForResult(new Intent(getActivity(), SelectGroupActivity.class), GET_GROUPS);
        });
        return view;
    }

    private void configureViews(View view) {
        input_register_user_email = view.findViewById(R.id.input_register_user_email);
        input_register_user_phone = view.findViewById(R.id.input_register_user_phone);
        input_register_user_password = view.findViewById(R.id.input_register_user_password);
        input_user_group = view.findViewById(R.id.input_user_group);
        btn_save_user = view.findViewById(R.id.btn_save_user);
        toolbar = view.findViewById(R.id.toolbar);

    }


    private void collectData() {
        String userEmail = input_register_user_email.getText().toString();
        String userPhone = input_register_user_phone.getText().toString();
        String userPassword = input_register_user_password.getText().toString();
        String userGroup = input_user_group.getText().toString();


        if (Utils.isFieldEmpty(userEmail)) {
            Utils.showResToast(mContext, R.string.user_email_empty_msg);
            return;
        }
        if (Utils.isFieldEmpty(userPassword)) {
            Utils.showResToast(mContext, R.string.user_password_empty_msg);
            return;
        }

        if (Utils.isFieldEmpty(userPhone)) {
            Utils.showResToast(mContext, R.string.user_phone_empty_msg);
            return;
        }
        if (Utils.isFieldEmpty(userGroup)) {
            Utils.showResToast(mContext, R.string.user_group_empty_msg);
            return;
        }

        User user = new User(userEmail, userPhone, userPassword, Long.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(getActivity())), "", "");

        new UserRepo(getActivity()).addUser(user).observe(this, GroupRes -> {
            try {
                if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                    Utils.showResToast(mContext, R.string.user_added_successfully);
                    getActivity().onBackPressed();
                } else {
                    Utils.showStringToast(mContext, GroupRes.getMsg());
                }
                Log.d(TAG, "collectData: " + GroupRes.toString());
            } catch (Exception ex) {
                Log.d(TAG, "collectData: " + ex.getMessage());
            }
        });


    }

    private static final int READ_REQUEST_CODE = 42;



    private void uploadPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GROUPS && resultCode == Activity.RESULT_OK) {
            //Intent i = getActivity().getIntent();
            lstGroups = (List<LstGroup>) data.getSerializableExtra(Constant.GROUPS_LIST);
            if (lstGroups.size() > 0) {
                Utils.showStringToast(getActivity(), getString(R.string.Group_selected));
                setGroupName();
                input_user_group.setText(SelectedGroupName);
            }
        }
    }

    private void setGroupName() {
        SelectedGroupName = String.valueOf(lstGroups.get(0).getMGroupName());
    }
}