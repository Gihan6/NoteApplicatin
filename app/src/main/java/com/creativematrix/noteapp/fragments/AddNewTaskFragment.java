package com.creativematrix.noteapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.activities.SelectProjectActivity;
import com.creativematrix.noteapp.activities.SelectTaskOwnersActivity;
import com.creativematrix.noteapp.callback.ProjectCallbacks;
import com.creativematrix.noteapp.callback.TaskCallbacks;
import com.creativematrix.noteapp.data.project.DisplayProjectRequest;
import com.creativematrix.noteapp.data.project.Project;
import com.creativematrix.noteapp.data.project.ProjectRepo;
import com.creativematrix.noteapp.data.task.LstUsersnCompnay;
import com.creativematrix.noteapp.data.task.Task;
import com.creativematrix.noteapp.data.task.TaskRepo;
import com.creativematrix.noteapp.util.PreferenceHelper;
import com.creativematrix.noteapp.util.Utils;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

public class AddNewTaskFragment extends Fragment {
    private ProjectCallbacks mCallbacks;
    public static final String TAG = AddNewTaskFragment.class.getSimpleName();
    private TextInputEditText editTextTaskName;
    private TextInputEditText editTextTaskDescription;
    private TextInputEditText editTextTaskCost;
    private TextInputEditText editTextTaskOwner;
    private TextInputEditText editTextTaskStartTime;
    private TextInputEditText editTextTaskStartDate;
    private TextInputEditText editTextTaskEndTime;
    private TextInputEditText editTextTaskEndDate;
    private TextInputEditText editTextTaskStatus;
    private TextInputEditText editTextTaskCoin;
    private TextInputEditText editTextProjectName;
    private static final int GET_USERS_INCOMPANY = 20;
    private static final int GET_PROJECTS_INCOMPANY = 21;
    private TextView textViewAddMember;
    private Button buttonSaveProject;
    private Context mContext;
    private List<LstUsersnCompnay> lstUsersnCompnays=new ArrayList<>();
    private List<Project> projects=new ArrayList<>();

    String taskOwnerIDS ,taskOwnerNames,selectedProjectName,selectedProjectID;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;

    public void setStartDate(String startDate) {
        this.startDate = startDate;
        if (startDate != null && !startDate.equals("")) {
            editTextTaskStartDate.setText(startDate);
            Log.d(TAG, "onCreateView: " + startDate);
        }
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
        if (startTime != null && !startTime.equals("")) {
            editTextTaskStartTime.setText(startTime);
        }
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
        if (endDate != null && !endDate.equals("")) {
            editTextTaskEndDate.setText(endDate);
        }
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
        if (endTime != null && !endTime.equals("")) {
            editTextTaskEndTime.setText(endTime);
        }
    }

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
        View view = inflater.inflate(R.layout.fragment_add_new_task, container, false);
        configureViews(view);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener((v, keyCode, event) -> {

            if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                getActivity().onBackPressed();
            }

            return true;
        });
        editTextTaskStartDate.setOnClickListener(v -> {
            //should open date picker and return the date value
            showDateFragment();
            mCallbacks.onStartDateClicked(Constant.START_DATE);
        });
        editTextTaskOwner.setOnClickListener(v -> {
            //should open date picker and return the date value
            startActivityForResult(new Intent(getActivity(), SelectTaskOwnersActivity.class), GET_USERS_INCOMPANY);

        });

        editTextTaskStartTime.setOnClickListener(v -> {
            showTimeFragment();
            mCallbacks.onStartTimeClicked(Constant.START_TIME);
        });

        editTextTaskEndDate.setOnClickListener(v -> {
            showDateFragment();
            mCallbacks.onEndDateClicked(Constant.END_DATE);
        });
        editTextProjectName.setOnClickListener(v -> {
            startActivityForResult(new Intent(getActivity(), SelectProjectActivity.class), GET_PROJECTS_INCOMPANY);

        });
        editTextTaskEndTime.setOnClickListener(v -> {
            showTimeFragment();
            mCallbacks.onEndTimeClicked(Constant.END_TIME);
        });

        buttonSaveProject.setOnClickListener(v -> {
            collectData();
        });


        return view;
    }

    private void configureViews(View view) {
        editTextTaskName = view.findViewById(R.id.input_task_name);
        editTextTaskDescription = view.findViewById(R.id.input_task_description);
        editTextTaskCost = view.findViewById(R.id.input_task_cost);
        editTextTaskCoin = view.findViewById(R.id.input_task_coin);
        editTextTaskOwner = view.findViewById(R.id.input_task_owner);
        editTextTaskStartTime = view.findViewById(R.id.input_task_start_time);
        editTextTaskStartDate = view.findViewById(R.id.input_task_start_date);
        editTextTaskEndTime = view.findViewById(R.id.input_task_end_time);
        editTextTaskEndDate = view.findViewById(R.id.input_task_end_date);
        editTextTaskStatus = view.findViewById(R.id.input_task_status);
        editTextProjectName= view.findViewById(R.id.input_project_name);
        final Toolbar toolbar = view.findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        buttonSaveProject = view.findViewById(R.id.btn_save_project);
    }

    public void showDateFragment() {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getActivity().getSupportFragmentManager(), "TaskDateFragment");
    }

    public void showTimeFragment() {
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.show(getActivity().getSupportFragmentManager(), "TaskTimeFragment");
    }

    private void collectData() {
        String taskName = editTextTaskName.getText().toString();
        String ProjectID=selectedProjectID;
        String taskDesc = editTextTaskDescription.getText().toString();
        String taskCost = editTextTaskCost.getText().toString();
        String taskCoin = editTextTaskCoin.getText().toString();
        String taskOwner = editTextTaskOwner.getText().toString();
        String taskStartTime = editTextTaskStartTime.getText().toString();
        String taskStartDate = editTextTaskStartDate.getText().toString();
        String taskEndTime = editTextTaskEndTime.getText().toString();
        String taskEndDate = editTextTaskEndDate.getText().toString();
        String taskStatus = editTextTaskStatus.getText().toString();
        if (Utils.isFieldEmpty(taskName)) {
            Utils.showResToast(mContext, R.string.task_name_empty);
            return;
        }
        Task task=new Task();
        task.setTaskName(taskName);
        task.setTaskDescripation(taskDesc);
        task.setProjectID(Long.valueOf(ProjectID));
        task.setUsersIDs(taskOwnerIDS);
        task.setCompanyID(Long.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(getActivity())));

        new TaskRepo(getActivity()).addTask(task)
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)){
                            Utils.showStringToast(getActivity(),getString(R.string.task_added_success));
                            getActivity().onBackPressed();
                        }
                    } catch (Exception ex) {

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == GET_USERS_INCOMPANY && resultCode == Activity.RESULT_OK) {
            //Intent i = getActivity().getIntent();
            lstUsersnCompnays = (List<LstUsersnCompnay>) resultData.getSerializableExtra(Constant.USERS_LIST);
            if(lstUsersnCompnays.size()>0){
                Utils.showStringToast(getActivity(),getString(R.string.users_selected));
                setIdsAndNamesOfUsers();
                editTextTaskOwner.setText(taskOwnerNames);
            }
        }

        if (requestCode == GET_PROJECTS_INCOMPANY && resultCode == Activity.RESULT_OK) {
            //Intent i = getActivity().getIntent();
            projects = (List<Project>) resultData.getSerializableExtra(Constant.PROJECTS_LIST);
            if(projects.size()>0){
                Utils.showStringToast(getActivity(),getString(R.string.Project_selected));
                setIdsAndNamesOProject();
                editTextProjectName.setText(selectedProjectName);
            }
        }
    }

    private void setIdsAndNamesOfUsers() {
        taskOwnerIDS="";
        taskOwnerNames="";
        for (int i=0;i<lstUsersnCompnays.size();i++){
            taskOwnerNames+=lstUsersnCompnays.get(i).getUsername()+"-";
            taskOwnerIDS+=lstUsersnCompnays.get(i).getUserID()+"-";
        }
    }


    private void setIdsAndNamesOProject() {
        selectedProjectID=String.valueOf(projects.get(0).getId());
        selectedProjectName=String.valueOf(projects.get(0).getProjectName());
    }

}
