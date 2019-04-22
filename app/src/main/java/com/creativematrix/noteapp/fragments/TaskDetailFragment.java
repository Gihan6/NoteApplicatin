package com.creativematrix.noteapp.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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
import com.creativematrix.noteapp.data.task.DisplayTaskDetailsResponse;
import com.creativematrix.noteapp.data.task.Task;
import com.creativematrix.noteapp.data.task.TaskRepo;
import com.creativematrix.noteapp.util.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.net.URL;
import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


@SuppressLint("ValidFragment")
public class TaskDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerViewGroups;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mContext;
    // Progress Dialog
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;
    private OnFragmentInteractionListener mListener;
    RecyclerView recycler_view_groups;
    FloatingActionButton floating_button_add_group;
    GroupsAdapter groupsAdapter;
    private ArrayList<LstGroup> lstGroups = new ArrayList<>();
    Toolbar toolbar;
    Task mTask;
    TextView task_name_title, task_name, task_desc, task_cost, task_start_time, task_end_time, task_owners, file_name, task_status, project_name, txtDownloadFile;
    Button btnDelete, btnUpdate;
    DialogPlus dialog;
    LinearLayout file_download;
    DisplayTaskDetailsResponse displayTaskDetailsResponse;
    public TaskDetailFragment(Task task) {
        this.mTask = task;
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
        View view = inflater.inflate(R.layout.fragment_task_detail, container, false);
        configureViews(view);

        view.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                getActivity().onBackPressed();
            }
            return true;
        });
        btnDelete.setOnClickListener(v -> {
            deleteTask();
        });
        txtDownloadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFile(displayTaskDetailsResponse.getTaskesfiles().get(0).getFileExt());
            }
        });
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

        new TaskRepo(getActivity()).displayTaskDetails(mTask)
                .observe(this, GroupRes -> {
                    try {

                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                            displayTaskDetailsResponse =GroupRes;
                            initialzeData(GroupRes);
                        }
                    } catch (Exception ex) {

                    }
                });
        return view;
    }

    private void deleteTask() {
        dialog = DialogPlus.newDialog(getActivity())
                .setContentHolder(new ViewHolder(R.layout.custom_dialog_confrim_delete))
                .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setGravity(Gravity.CENTER)
                .create();
        dialog.show();

        LinearLayout btnYes = (LinearLayout) dialog.findViewById(R.id.btnYes);
        LinearLayout btnNo = (LinearLayout) dialog.findViewById(R.id.btnNo);
        btnYes.setOnClickListener(v ->
                confirm_delete_task(mTask)
        );
        btnNo.setOnClickListener(v -> dialog.dismiss());
    }

    private void confirm_delete_task(Task mTask) {
        new TaskRepo(getActivity()).deleteTask(mTask)
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                            Utils.showStringToast(getActivity(), getResources().getString(R.string.deleted_succees));
                        } else if (GroupRes.getFlag().equals(Constant.RESPONSE_FAILURE)) {
                            Utils.showStringToast(getActivity(), String.valueOf(GroupRes.getMsg()));
                        }
                    } catch (Exception ex) {

                    }
                });

    }

    private void initialzeData(DisplayTaskDetailsResponse groupRes) {
        task_name_title.setText(groupRes.getTaskName());
        task_name.setText(groupRes.getTaskName());
        task_desc.setText(groupRes.getTaskDescription());
        task_start_time.setText(groupRes.getTaskStartTime());
        task_end_time.setText(groupRes.getTaskEndTime());
        String ownersNames = "";
        for (int i = 0; i < groupRes.getTaskUsers().size(); i++) {
            ownersNames += groupRes.getTaskUsers().get(i).getUserName() + "-";
        }
        task_owners.setText(ownersNames);
        if (groupRes.getTaskStautes() == false) {
            task_status.setText(getResources().getString(R.string.task_under_processing));
        } else {
            task_status.setText(getResources().getString(R.string.task_completed));
        }

        if (groupRes.getTaskesfiles() != null) {
            file_download.setVisibility(View.VISIBLE);
            file_name.setText(mTask.getFilesBinaryList().get(0).getFileName());
        } else {
            file_download.setVisibility(View.GONE);

        }
        //project_name.setText(groupRes.get());
        task_cost.setText(String.valueOf(groupRes.getTaskCost()));
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
        task_name_title = view.findViewById(R.id.task_name_title);
        task_name = view.findViewById(R.id.task_name);
        task_desc = view.findViewById(R.id.task_desc);
        task_start_time = view.findViewById(R.id.task_start_time);
        task_end_time = view.findViewById(R.id.task_end_time);
        task_owners = view.findViewById(R.id.task_owners);
        task_status = view.findViewById(R.id.task_status);
        project_name = view.findViewById(R.id.project_name);
        task_cost = view.findViewById(R.id.task_cost);
        btnDelete = view.findViewById(R.id.btnDelete);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        txtDownloadFile = view.findViewById(R.id.txtDownloadFile);
        file_name = view.findViewById(R.id.file_name);
        file_download = view.findViewById(R.id.file_download);
        toolbar = view.findViewById(R.id.anim_toolbar);

    }

    private void downloadFile(String url) {

        Uri intentUri = Uri.parse(url);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(intentUri);
        startActivity(intent);
    }
}
