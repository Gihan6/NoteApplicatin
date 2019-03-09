package com.creativematrix.noteapp.fragments;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.callback.DateTimeCallbacks;
import com.creativematrix.noteapp.callback.ProjectCallbacks;
import com.creativematrix.noteapp.data.project.Project;
import com.creativematrix.noteapp.data.project.ProjectRepo;
import com.creativematrix.noteapp.util.Utils;

public class AddNewProjectFragment extends Fragment {
    private ProjectCallbacks mCallbacks;
    public static final String TAG = AddNewProjectFragment.class.getSimpleName();
    private TextInputEditText editTextProjectName;
    private TextInputEditText editTextProjectDescription;
    private TextInputEditText editTextProjectCost;
    private TextInputEditText editTextProjectOwner;
    private TextInputEditText editTextProjectStartTime;
    private TextInputEditText editTextProjectStartDate;
    private TextInputEditText editTextProjectEndTime;
    private TextInputEditText editTextProjectEndDate;
    private TextInputEditText editTextProjectDirector ;
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
            editTextProjectStartDate.setText(startDate);
            Log.d(TAG, "onCreateView: " + startDate);
        }
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
        if (startTime != null && !startTime.equals("")) {
            editTextProjectStartTime.setText(startTime);
        }
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
        if (endDate != null && !endDate.equals("")) {
            editTextProjectEndDate.setText(endDate);
        }
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
        if (endTime != null && !endTime.equals("")) {
            editTextProjectEndTime.setText(endTime);
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
        View view = inflater.inflate(R.layout.fragment_add_new_project, container, false);
        configureViews(view);

        editTextProjectStartDate.setOnClickListener(v -> {
            //should open date picker and return the date value
            showDateFragment();
            mCallbacks.onStartDateClicked(Constant.START_DATE);
        });

        editTextProjectStartTime.setOnClickListener(v -> {
            showTimeFragment();
            mCallbacks.onStartTimeClicked(Constant.START_TIME);
        });

        editTextProjectEndDate.setOnClickListener(v -> {
            showDateFragment();
            mCallbacks.onEndDateClicked(Constant.END_DATE);
        });

        editTextProjectEndTime.setOnClickListener(v -> {
            showTimeFragment();
            mCallbacks.onEndTimeClicked(Constant.END_TIME);
        });

        buttonSaveProject.setOnClickListener(v -> {
            collectData();
        });


        return view;
    }

    private void configureViews(View view) {
        editTextProjectName = view.findViewById(R.id.input_project_name);
        editTextProjectDescription = view.findViewById(R.id.input_project_description);
        editTextProjectCost = view.findViewById(R.id.input_project_cost);
        editTextProjectOwner = view.findViewById(R.id.input_project_owner);
        editTextProjectStartTime = view.findViewById(R.id.input_project_start_time);
        editTextProjectStartDate = view.findViewById(R.id.input_project_start_date);
        editTextProjectEndTime = view.findViewById(R.id.input_project_end_time);
        editTextProjectEndDate = view.findViewById(R.id.input_project_end_date);
        editTextProjectDirector= view.findViewById(R.id.input_project_director);
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
        String projectName = editTextProjectName.getText().toString();
        String projectDesc = editTextProjectDescription.getText().toString();
        String projectCost = editTextProjectCost.getText().toString();
        String projectOwner = editTextProjectOwner.getText().toString();
        String projectStartTime = editTextProjectStartTime.getText().toString();
        String projectStartDate = editTextProjectStartDate.getText().toString();
        String projectEndTime = editTextProjectEndTime.getText().toString();
        String projectEndDate = editTextProjectEndDate.getText().toString();
        String projectDirector= editTextProjectDirector.getText().toString();
        if (Utils.isFieldEmpty(projectName)) {
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
        new ProjectRepo(getActivity()).addProject(project)
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
