package com.creativematrix.noteapp.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.android.material.textfield.TextInputEditText;

import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

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
import com.creativematrix.noteapp.callback.ProjectCallbacks;
import com.creativematrix.noteapp.data.groups.Group;
import com.creativematrix.noteapp.data.groups.GroupRepo;
import com.creativematrix.noteapp.util.PreferenceHelper;
import com.creativematrix.noteapp.util.Utils;
import com.squareup.picasso.Picasso;

public class AddNewGroupFragment extends Fragment {
    private ProjectCallbacks mCallbacks;
    public static final String TAG = AddNewProjectFragment.class.getSimpleName();
    private TextInputEditText et_group_name;
    private TextInputEditText et_group_desc;
    private static final int RESULT_LOAD_IMAGE = 1;
    private ImageView image_view_group_logo;
    private Button btn_save_group;
    private Context mContext;
    Toolbar toolbar;
    String logoPath;
    Group mGroup = null;

    @SuppressLint("ValidFragment")
    public AddNewGroupFragment(Group group) {
        this.mGroup = group;
    }

    public AddNewGroupFragment() {
    }


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
        View view = inflater.inflate(R.layout.fragment_add_new_group, container, false);
        configureViews(view);

        btn_save_group.setOnClickListener(v -> {
            try {
                collectData();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        upload_photo.setOnClickListener(view1 -> uploadPhoto());
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

        return view;
    }

    private void configureViews(View view) {
        et_group_name = view.findViewById(R.id.et_group_name);
        et_group_desc = view.findViewById(R.id.et_group_desc);
        btn_save_group = view.findViewById(R.id.btn_save_group);
        upload_photo = view.findViewById(R.id.upload_photo);
        toolbar = view.findViewById(R.id.toolbar);
        image_view_group_logo = view.findViewById(R.id.image_view_group_logo);
        ((NoteHomeActivity) getActivity()).setSupportActionBar(toolbar);
        ((NoteHomeActivity) getActivity()).getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);

        ((NoteHomeActivity) getActivity()).getSupportActionBar()
                .setDisplayShowTitleEnabled(false);

        if (mGroup != null) {
            et_group_name.setText(mGroup.getGroupName());
            if(mGroup.getGroupDescreption()!=null)
            et_group_desc.setText((String.valueOf(mGroup.getGroupDescreption())));
            if(mGroup.getGroupImage()!=null)
                Picasso.with(getActivity()).load(String.valueOf( mGroup.getGroupImage())).placeholder(R.mipmap.picture).into(image_view_group_logo);
        }
    }


    private void collectData() throws JsonProcessingException {
        String groupName = et_group_name.getText().toString();
        String groupDesc = et_group_desc.getText().toString();


        if (Utils.isFieldEmpty(groupName)) {
            Utils.showResToast(mContext, R.string.group_name_empty);
            return;
        }
        Group group = new Group();
        group.setCompanyId(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(getActivity()));
        group.setGroupName(groupName);
        group.setGroupDescreption(groupDesc);
        group.setAddBy(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(getActivity()));
        group.setImagebinary(Utils.encodeImage(logoPath));
        group.setLang(Utils.getLang());
        if(mGroup!=null){
            group.setGroupId(mGroup.getGroupId());
            updateGroup(group);
        }
        else{
            AddGroup(group);
        }
      

    }

    private void updateGroup(Group group) {
        new GroupRepo(getActivity()).updateGroup(group).observe(this, GroupRes -> {
            try {
                if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                    //Utils.showResToast(mContext, R.string.group_added_successfully);
                    Utils.showStringToast(getActivity(),String.valueOf(GroupRes.getMessage()));
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.popBackStack(
                            fragmentManager.getBackStackEntryAt(
                                    fragmentManager.getBackStackEntryCount() - 2).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    //getActivity().onBackPressed();

                }
                else if (GroupRes.getFlag().equals(Constant.RESPONSE_FAILURE)){
                    Utils.showStringToast(getActivity(),String.valueOf(GroupRes.getMessage()));
                }
                Log.d(TAG, "collectData: " + GroupRes.toString());
            } catch (Exception ex) {
                Log.d(TAG, "collectData: " + ex.getMessage());
            }
        });
    }

    private void AddGroup(Group group) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(group);
        new GroupRepo(getActivity()).addGroup(group).observe(this, GroupRes -> {
            try {
                if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                   // Utils.showResToast(mContext, R.string.group_added_successfully);
                    Utils.showStringToast(getActivity(),String.valueOf(GroupRes.getMessage()));
                    getActivity().onBackPressed();
                }
                else if (GroupRes.getFlag().equals(Constant.RESPONSE_FAILURE)){
                     Utils.showStringToast(getActivity(),String.valueOf(GroupRes.getMessage()));
                }
                Log.d(TAG, "collectData: " + GroupRes.toString());
            } catch (Exception ex) {
                Log.d(TAG, "collectData: " + ex.getMessage());
            }
        });

    }

    private static final int READ_REQUEST_CODE = 42;

    public void performFileSearch() {

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.setType("image/*");

        startActivityForResult(intent, READ_REQUEST_CODE);
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
                    Bitmap bmImg = BitmapFactory.decodeFile(logoPath);
                    image_view_group_logo.setImageBitmap(bmImg);
                    break;
                }
        }
    }
}