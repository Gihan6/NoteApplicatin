package com.creativematrix.noteapp.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.adapters.GroupsAdapter;
import com.creativematrix.noteapp.adapters.TasksInUsersAdapter;
import com.creativematrix.noteapp.data.groups.Group;
import com.creativematrix.noteapp.data.groups.GroupRepo;
import com.creativematrix.noteapp.data.groups.LstGroup;
import com.creativematrix.noteapp.data.project.Project;
import com.creativematrix.noteapp.data.project.ProjectDetailsResponse;
import com.creativematrix.noteapp.data.project.ProjectRepo;
import com.creativematrix.noteapp.data.task.Task;
import com.creativematrix.noteapp.data.user.DisplayUserDetailsRequest;
import com.creativematrix.noteapp.data.user.LstUsers;
import com.creativematrix.noteapp.data.user.User;
import com.creativematrix.noteapp.data.user.UserRepo;
import com.creativematrix.noteapp.util.PreferenceHelper;
import com.creativematrix.noteapp.util.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


@SuppressLint("ValidFragment")
public class UserDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerViewGroups;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private OnFragmentInteractionListener mListener;
    RecyclerView recycler_view_users;
    TasksInUsersAdapter tasksInUsersAdapter;
    private ArrayList<Task> tasks = new ArrayList<>();
    Toolbar toolbar;
    LstUsers mUser;
    DialogPlus dialog;
    Button btnUpdate,btnDelete;
    TextView user_name,user_email,user_name_title;
    public UserDetailFragment(LstUsers user) {
        this.mUser = user;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_detail, container, false);
        configureViews(view);

        view.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                getActivity().onBackPressed();
            }
            return true;
        });
        btnDelete.setOnClickListener(v ->
                deleteUser(mUser));

        btnUpdate.setOnClickListener(v -> {
           /* Group group = new Group();
            group.setLang(Utils.getLang());
            if (mGroup.getGroupId() != null)
                group.setGroupId(mGroup.getGroupId());

            if (mGroup.getGroupImage() != null)
                group.setImagebinary(mGroup.getGroupImage());

            if (mGroup.getGroupDescripation() != null)
                group.setGroupDescreption(mGroup.getGroupDescripation());

            if (mGroup.getMGroupName() != null)
                group.setGroupName(
                        mGroup.getMGroupName());
            if (mGroup.getGroupDescripation() != null)
                group.setGroupDescreption(mGroup.getGroupDescripation());*/

            Utils.switchFragmentWithAnimation
                    (R.id.fragment_holder_home,
                            new AddNewUserFragment(mUser),
                            getActivity(),
                            Utils.ADDNEWGROUPFRAGMENT,
                            Utils.AnimationType.SLIDE_UP);


        });
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

        new UserRepo(getActivity()).displayUserDetailsResponseLiveData(new DisplayUserDetailsRequest(mUser.getUserId()))
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                            tasks.clear();
                            tasks.addAll(GroupRes.getTasks());
                            tasksInUsersAdapter.notifyDataSetChanged();
                            user_name_title.setText(GroupRes.getName());
                            user_email.setText(GroupRes.getEmail());
                            user_name.setText(GroupRes.getName());
                            //Utils.showStringToast(getActivity(),getResources().getString(R.string.deleted_succees));
                        }
                        else if (GroupRes.getFlag().equals(Constant.RESPONSE_FAILURE)){
                            Utils.showStringToast(getActivity(),String.valueOf(GroupRes.getMessage()));
                        }
                    } catch (Exception ex) {

                    }
                });

        return view;
    }






    private void deleteUser(LstUsers mUser) {
        mUser.setLang(Utils.getLang());
        dialog = DialogPlus.newDialog(getActivity())
                .setContentHolder(new ViewHolder(R.layout.custom_dialog_confrim_delete))
                .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setGravity(Gravity.CENTER)
                .create();
        dialog.show();

        LinearLayout btnYes = (LinearLayout) dialog.findViewById(R.id.btnYes);
        LinearLayout btnNo = (LinearLayout) dialog.findViewById(R.id.btnNo);
        btnYes.setOnClickListener(v ->
                confirm_delete_group(mUser)

        );
        btnNo.setOnClickListener(v -> dialog.dismiss());
    }

    private void confirm_delete_group(LstUsers mUser) {
        dialog.dismiss();
        new UserRepo(getActivity()).deleteUser(mUser)
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(String.valueOf(Constant.RESPONSE_SUCCESS))) {

                            Utils.showStringToast(getActivity(), getResources().getString(R.string.deleted_succees));
                            getActivity().onBackPressed();
                        } else if (GroupRes.getFlag().equals(String.valueOf(Constant.RESPONSE_FAILURE))) {
                            Utils.showStringToast(getActivity(), String.valueOf(GroupRes.getMessage()));


                        }
                    } catch (Exception ex) {


                    }
                });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        // mCallbacks = (ProjectCallbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void configureViews(View view) {
        user_name= view.findViewById(R.id.user_name);
        user_email= view.findViewById(R.id.user_email);
        btnUpdate= view.findViewById(R.id.btnUpdate);
        btnDelete= view.findViewById(R.id.btnDelete);
        user_name_title= view.findViewById(R.id.user_name_title);
        recycler_view_users = view.findViewById(R.id.recycler_view_tasks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler_view_users.setLayoutManager(linearLayoutManager);
        recycler_view_users.setHasFixedSize(true);
        tasksInUsersAdapter = new TasksInUsersAdapter(getActivity(), tasks, (v, position) -> Utils.showStringToast(getActivity(), String.valueOf(tasks.get(position).getAddedID())));
        // Set adapter in recyclerView
        toolbar = view.findViewById(R.id.anim_toolbar);

        recycler_view_users.setAdapter(tasksInUsersAdapter);

    }
}
