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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.activities.NoteHomeActivity;
import com.creativematrix.noteapp.adapters.TasksInGroupAdapter;
import com.creativematrix.noteapp.adapters.TasksInUsersAdapter;
import com.creativematrix.noteapp.data.groups.DisplayGroupDetailsRequest;
import com.creativematrix.noteapp.data.groups.DisplayGroupDetailsResponse;
import com.creativematrix.noteapp.data.groups.Group;
import com.creativematrix.noteapp.data.groups.GroupRepo;
import com.creativematrix.noteapp.data.groups.LstGroup;
import com.creativematrix.noteapp.data.groups.Userslst;
import com.creativematrix.noteapp.data.task.Task;
import com.creativematrix.noteapp.data.task.TaskRepo;
import com.creativematrix.noteapp.data.user.DisplayUserDetailsRequest;
import com.creativematrix.noteapp.data.user.LstUsers;
import com.creativematrix.noteapp.data.user.UserRepo;
import com.creativematrix.noteapp.util.PreferenceHelper;
import com.creativematrix.noteapp.util.Utils;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.SplittableRandom;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


@SuppressLint("ValidFragment")
public class GroupDetailFragment extends Fragment {
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
    TasksInGroupAdapter tasksInGroupAdapter;
    private ArrayList<Userslst> userslsts = new ArrayList<>();
    Toolbar toolbar;
    LstGroup mGroup;
    DialogPlus dialog;
    TextView group_name, group_name_title, members_count, no_member;
    FrameLayout empty_frame_layout;
    public GroupDetailFragment(LstGroup group) {
        this.mGroup = group;
    }
    ImageView group_logo;
    Button btnUpdate, btnDelete;

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
        View view = inflater.inflate(R.layout.fragment_group_detail, container, false);
        configureViews(view);

        view.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                getActivity().onBackPressed();
            }
            return true;
        });
        btnDelete.setOnClickListener(v ->
                deleteGroup(
                        new Group((mGroup.getGroupId()),
                                String.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(getActivity())), Utils.getLang())));

        btnUpdate.setOnClickListener(v -> {
            Group group = new Group();
            group.setLang(Utils.getLang());
            if (mGroup.getGroupId() != null)
                group.setGroupId(mGroup.getGroupId());

            if (mGroup.getImg() != null)
                group.setGroupImage(mGroup.getImg());

            if (mGroup.getGroupDescreption() != null)
                group.setGroupDescreption(mGroup.getGroupDescreption());

            if (mGroup.getGroupName() != null)
                group.setGroupName(
                        mGroup.getGroupName());
            if (mGroup.getGroupDescreption() != null)
                group.setGroupDescreption(mGroup.getGroupDescreption());

            Utils.switchFragmentWithAnimation
                    (R.id.fragment_holder_home,
                            new AddNewGroupFragment(group),
                            getActivity(),
                            Utils.ADDNEWGROUPFRAGMENT,
                            Utils.AnimationType.SLIDE_UP);


        });
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

        new GroupRepo(getActivity()).displayGroupDetailsResponseLiveData(new DisplayGroupDetailsRequest(mGroup.getGroupId()))
                .observe(this, (DisplayGroupDetailsResponse GroupRes) -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                            if (GroupRes.getUserslst().size() == 0) {
                                empty_frame_layout.setVisibility(View.VISIBLE);
                            }
                            userslsts.clear();
                            userslsts.addAll(GroupRes.getUserslst());
                            tasksInGroupAdapter.notifyDataSetChanged();
                            group_name_title.setText(mGroup.getGroupName());
                            Picasso.with(getActivity()).load(GroupRes.getGrouplogo()).placeholder(R.mipmap.profile).into(group_logo);

                            group_name.setText(mGroup.getGroupName());
                            no_member.setVisibility(View.GONE);
                            //Utils.showStringToast(getActivity(),getResources().getString(R.string.deleted_succees));
                        } else if (GroupRes.getFlag().equals(Constant.RESPONSE_FAILURE)) {
                            Utils.showStringToast(getActivity(), String.valueOf(GroupRes.getMessage()));
                            empty_frame_layout.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception ex) {
                        empty_frame_layout.setVisibility(View.VISIBLE);


                    }
                });

        return view;
    }

    private void deleteGroup(Group mGroup) {
        mGroup.setLang(Utils.getLang());
        dialog = DialogPlus.newDialog(getActivity())
                .setContentHolder(new ViewHolder(R.layout.custom_dialog_confrim_delete))
                .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setGravity(Gravity.CENTER)
                .create();
        dialog.show();

        LinearLayout btnYes = (LinearLayout) dialog.findViewById(R.id.btnYes);
        LinearLayout btnNo = (LinearLayout) dialog.findViewById(R.id.btnNo);
        btnYes.setOnClickListener(v ->
                confirm_delete_group(mGroup)
        );
        btnNo.setOnClickListener(v -> dialog.dismiss());
    }

    private void confirm_delete_group(Group mGroup) {
        new GroupRepo(getActivity()).deleteGroup(mGroup)
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                            dialog.dismiss();
                            Utils.showStringToast(getActivity(), getResources().getString(R.string.deleted_succees));
                            getActivity().onBackPressed();
                        } else if (GroupRes.getFlag().equals(Constant.RESPONSE_FAILURE)) {
                            Utils.showStringToast(getActivity(), String.valueOf(GroupRes.getMsg()));
                            dialog.dismiss();

                        }
                    } catch (Exception ex) {
                        dialog.dismiss();

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
        group_name = view.findViewById(R.id.group_name);
        no_member = view.findViewById(R.id.no_member);
        group_name_title = view.findViewById(R.id.group_name_title);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnDelete = view.findViewById(R.id.btnDelete);
        group_logo= view.findViewById(R.id.group_logo);
        members_count = view.findViewById(R.id.members_count);
        recycler_view_users = view.findViewById(R.id.recycler_view_users);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler_view_users.setLayoutManager(linearLayoutManager);
        recycler_view_users.setHasFixedSize(true);
        tasksInGroupAdapter = new TasksInGroupAdapter(
                getActivity(), userslsts, (v, position) ->
                Utils.switchFragmentWithAnimation(R.id.fragment_holder_home,
                        new UserDetailFragment(new LstUsers(userslsts.get(position).getUserId())),
                        getActivity(), Utils.USERDETAILFRAGMENT, Utils.AnimationType.SLIDE_UP)
        );
        empty_frame_layout = view.findViewById(R.id.empty_frame_layout);

        // Set adapter in recyclerView
        toolbar = view.findViewById(R.id.anim_toolbar);
        recycler_view_users.setAdapter(tasksInGroupAdapter);
    }
}
