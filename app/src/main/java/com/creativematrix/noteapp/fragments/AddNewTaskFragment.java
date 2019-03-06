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
import com.creativematrix.noteapp.callback.ProjectCallbacks;
import com.creativematrix.noteapp.data.project.Project;
import com.creativematrix.noteapp.data.project.ProjectRepo;
import com.creativematrix.noteapp.util.Utils;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

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

    private TextView textViewAddMember;
    private Button buttonSaveProject;
    private Context mContext;

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

        editTextTaskStartTime.setOnClickListener(v -> {
            showTimeFragment();
            mCallbacks.onStartTimeClicked(Constant.START_TIME);
        });

        editTextTaskEndDate.setOnClickListener(v -> {
            showDateFragment();
            mCallbacks.onEndDateClicked(Constant.END_DATE);
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
        editTextTaskCoin= view.findViewById(R.id.input_task_coin);
        editTextTaskOwner = view.findViewById(R.id.input_task_owner);
        editTextTaskStartTime = view.findViewById(R.id.input_task_start_time);
        editTextTaskStartDate = view.findViewById(R.id.input_task_start_date);
        editTextTaskEndTime = view.findViewById(R.id.input_task_end_time);
        editTextTaskEndDate = view.findViewById(R.id.input_task_end_date);
        editTextTaskStatus= view.findViewById(R.id.input_task_status);
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
        String taskDesc = editTextTaskDescription.getText().toString();
        String taskCost = editTextTaskCost.getText().toString();
        String taskCoin = editTextTaskCoin.getText().toString();
        String taskOwner = editTextTaskOwner.getText().toString();
        String taskStartTime = editTextTaskStartTime.getText().toString();
        String taskStartDate = editTextTaskStartDate.getText().toString();
        String taskEndTime = editTextTaskEndTime.getText().toString();
        String taskEndDate = editTextTaskEndDate.getText().toString();
        String taskStatus= editTextTaskStatus.getText().toString();
        if (Utils.isFieldEmpty(taskName)) {
            Utils.showResToast(mContext, R.string.project_name_empty);
            return;
        }
                Project project =
                new Project(projectName, projectStartDate + " " + projectStartTime
                , projectEndDate + " " + projectEndTime, projectOwner, projectDesc, projectCost
                , 13, ""
                , Utils.getLang(), 0, projectDirector);

        Log.d(TAG, "collectData: " + projectStartDate + " " + projectStartTime);
        Log.d(TAG, "collectData: " + projectEndDate + " " + projectEndTime);
        new ProjectRepo().addProject(project)
                .observe(this, projectRes -> {
                    try {
                        Log.d(TAG, "collectData: " + projectRes.toString());
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

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                Log.i(TAG, "Uri: " + uri.toString());
//                showImage(uri);
            }
        }
    }
}
