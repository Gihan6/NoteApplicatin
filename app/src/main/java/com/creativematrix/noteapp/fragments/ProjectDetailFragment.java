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
import com.creativematrix.noteapp.data.groups.LstGroup;
import com.creativematrix.noteapp.data.project.Project;
import com.creativematrix.noteapp.data.project.ProjectDetailsResponse;
import com.creativematrix.noteapp.data.project.ProjectRepo;

import com.creativematrix.noteapp.util.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


@SuppressLint("ValidFragment")
public class ProjectDetailFragment extends Fragment {
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
    RecyclerView recycler_view_groups;
    FloatingActionButton floating_button_add_group;
    GroupsAdapter groupsAdapter;
    private ArrayList<LstGroup> lstGroups = new ArrayList<>();
    Toolbar toolbar;
    Project mProject;
    TextView project_name_title, project_name, project_desc, project_cost, project_start_time, project_end_time, project_owners, project_status, project_director;
    Button btnDelete, btnUpdate;
    DialogPlus dialog;

    public ProjectDetailFragment(Project project) {
        this.mProject = project;
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
        View view = inflater.inflate(R.layout.fragment_project_detail, container, false);
        configureViews(view);

        view.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                getActivity().onBackPressed();
            }
            return true;
        });
        btnDelete.setOnClickListener(v -> {
           deleteProject();
        });
        btnUpdate.setOnClickListener(v ->
                Utils.switchFragmentWithAnimation
                        (R.id.fragment_holder_home,
                                new AddNewProjectFragment(mProject),
                                getActivity(),
                                Utils.ADDNEWGROUPFRAGMENT,
                                Utils.AnimationType.SLIDE_UP));
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

        new ProjectRepo(getActivity()).displayProjectDeitals(mProject)
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                            initialzeData(GroupRes);
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

    private void deleteProject() {
        dialog = DialogPlus.newDialog(getActivity())
                .setContentHolder(new ViewHolder(R.layout.custom_dialog_confrim_delete))
                .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setGravity(Gravity.CENTER)
                .create();
        dialog.show();

        LinearLayout btnYes = (LinearLayout) dialog.findViewById(R.id.btnYes);
        LinearLayout btnNo = (LinearLayout) dialog.findViewById(R.id.btnNo);
        btnYes.setOnClickListener(v ->
                confirm_delete_project(mProject)
        );
        btnNo.setOnClickListener(v -> dialog.dismiss());
    }

    private void confirm_delete_project(Project mProject) {

        /*new ProjectRepo(getActivity()).displayProjectDeitals(mProject)
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                            Utils.showStringToast(getActivity(),getResources().getString(R.string.deleted_succees));
                        }
                        else if (GroupRes.getFlag().equals(Constant.RESPONSE_FAILURE)){
                            Utils.showStringToast(getActivity(),String.valueOf(GroupRes.getMessage()));
                        }
                    } catch (Exception ex) {

                    }
                });
*/
    }

    private void initialzeData(ProjectDetailsResponse groupRes) {
        project_name_title.setText(groupRes.getName());
        project_name.setText(groupRes.getName());
        project_desc.setText(groupRes.getName());
        project_start_time.setText(groupRes.getStart());
        project_end_time.setText(groupRes.getEnd());
        project_owners.setText(groupRes.getOwner());
        project_director.setText(groupRes.getDirector());
        if (groupRes.getState().equals("0")) {
            project_status.setText(getResources().getString(R.string.task_under_processing));
        } else {
            project_status.setText(getResources().getString(R.string.task_completed));
        }
        project_cost.setText(String.valueOf(groupRes.getCoast()));
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
        project_name_title = view.findViewById(R.id.project_name_title);
        project_name = view.findViewById(R.id.project_name);
        project_desc = view.findViewById(R.id.project_desc);
        project_start_time = view.findViewById(R.id.project_start_time);
        project_end_time = view.findViewById(R.id.project_end_time);
        project_owners = view.findViewById(R.id.project_owners);
        project_status = view.findViewById(R.id.project_status);
        project_name = view.findViewById(R.id.project_name);
        project_cost = view.findViewById(R.id.project_cost);
        btnDelete = view.findViewById(R.id.btnDelete);
        btnUpdate = view.findViewById(R.id.btnUpdate);

        toolbar = view.findViewById(R.id.anim_toolbar);
    }
}
