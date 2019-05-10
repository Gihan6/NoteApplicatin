package com.creativematrix.noteapp.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.creativematrix.noteapp.activities.SelectTaskCoinActivity;
import com.creativematrix.noteapp.data.coins.CurrencyList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.android.material.textfield.TextInputEditText;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.activities.SelectTaskOwnersActivity;
import com.creativematrix.noteapp.callback.ProjectCallbacks;
import com.creativematrix.noteapp.data.project.Project;
import com.creativematrix.noteapp.data.project.ProjectRepo;
import com.creativematrix.noteapp.data.task.LstUsersnCompnay;
import com.creativematrix.noteapp.util.PreferenceHelper;
import com.creativematrix.noteapp.util.Utils;

import java.util.ArrayList;
import java.util.List;

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
    private TextInputEditText  editTextProjectCoin;
    private TextView textViewAddMember;
    private Button buttonSaveProject;
    private Context mContext;
    Toolbar toolbar;
    String taskOwnerIDS = "", taskOwnerNames = "", selectedProjectName = "", selectedProjectID = "", selectedTaskStatusName = "", selectedTaskStatusID = "", selectedCurrnecyID = "", selectedCurrencyName = "";
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private List<CurrencyList> currencyLists = new ArrayList<>();
    Project mProject;
    private List<LstUsersnCompnay> lstUsersnCompnays = new ArrayList<>();
    private static final int GET_CURRNECY = 23;

    @SuppressLint("ValidFragment")
    public AddNewProjectFragment(Project project) {
        this.mProject=project;
    }
    public AddNewProjectFragment() {
    }

    private static final int GET_USERS_INCOMPANY = 20;

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
    public View onCreateView
            (@NonNull LayoutInflater inflater,
             @Nullable ViewGroup container,
             @Nullable Bundle savedInstanceState) {
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
        editTextProjectCoin.setOnClickListener(v -> {
            startActivityForResult(new Intent(getActivity(), SelectTaskCoinActivity.class), GET_CURRNECY);
        });
        buttonSaveProject.setOnClickListener(v -> {
            collectData();
        });

        editTextProjectDirector.setOnClickListener(v -> {
            //should open date picker and return the date value
            startActivityForResult(new Intent(getActivity(), SelectTaskOwnersActivity.class), GET_USERS_INCOMPANY);

        });

        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

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
        editTextProjectCoin= view.findViewById(R.id.editTextProjectCoin);
        editTextProjectDirector= view.findViewById(R.id.input_project_director);
        toolbar = view.findViewById(R.id.anim_toolbar);
        buttonSaveProject = view.findViewById(R.id.btn_save_project);
        if (mProject != null) {
            editTextProjectName.setText(mProject.getProjectName());
            if(mProject.getProjectDescripation()!=null)
                editTextProjectDescription .setText((String.valueOf(mProject.getProjectDescripation())));
            if(mProject.getProjectCost()!=null)
                editTextProjectCost.setText((String.valueOf(mProject.getProjectCost())));

            if(mProject.getDirctoresNames()!=null) {
                editTextProjectDirector.setText((String.valueOf(mProject.getDirctoresNames())));
                taskOwnerIDS=String.valueOf(mProject.getDirectorIDs());
            }
            if(mProject.getStartTime()!=null){
                handleStartTimeDate(mProject.getStartTime());
            }
            if(mProject.getEndTime()!=null){
                handleEndTimeDate(mProject.getEndTime());
            }
            if(mProject.getProjectOwner()!=null){
                editTextProjectOwner.setText(mProject.getProjectOwner());
            }

        }
    }

    private void handleStartTimeDate(String startDateTime) {
     /*   String s1= startDateTime.substring(0,startDateTime.indexOf("T",1));
        editTextProjectStartDate.setText(s1);
        String s2= startDateTime.substring(s1.length()+2);
        editTextProjectStartTime.setText(s2);
*/

        String[] splitStr = startDateTime.split("\\s+");
        editTextProjectStartDate.setText(((splitStr[0])));
        editTextProjectStartTime.setText(((splitStr[1])));
    }

    private void handleEndTimeDate(String startEndTime) {
     /*   String s1= startEndTime.substring(0,startEndTime.indexOf("T",1));
        editTextProjectEndDate.setText(s1);
        String s2= startEndTime.substring(s1.length()+2);
        editTextProjectEndTime.setText(s2);*/

        String[] splitStr = startEndTime.split("\\s+");
        editTextProjectEndDate.setText(((splitStr[0])));
        editTextProjectEndTime.setText(((splitStr[1])));
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

        String projectDirector= taskOwnerIDS;
        if (Utils.isFieldEmpty(projectName)) {
            Utils.showResToast(mContext, R.string.project_name_empty);
            return;
        }
                Project project =
                new Project(projectName, projectStartDate + " " + projectStartTime
                , projectEndDate + " " + projectEndTime, projectOwner, projectDesc, projectCost
                , Long.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(getActivity())), ""
                , Utils.getLang(), 0, projectDirector,Long.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(getActivity())));

        Log.d(TAG, "collectData: " + projectStartDate + " " + projectStartTime);
        Log.d(TAG, "collectData: " + projectEndDate + " " + projectEndTime);
        if(mProject!=null){
            project.setId(mProject.getId());
            project.setDirectorIDs(mProject.getDirectorIDs());
            updateProject(project);
        }
        else{
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            try {
                String json = ow.writeValueAsString(project);
                String B = json;
                String C=B;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            AddProject(project);
        }
    }

    private void AddProject(Project project) {
        new ProjectRepo(getActivity()).addProject(project)
                .observe(this, projectRes -> {
                    try {
                        if (projectRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                            Utils.showStringToast(getActivity(), getString(R.string.project_add_successfully));
                            getActivity().onBackPressed();
                        }
                        else if (projectRes.getFlag().equals(Constant.RESPONSE_FAILURE)){
                            Utils.showStringToast(getActivity(),String.valueOf(projectRes.getMsg()));
                        }
                        Log.d(TAG, "collectData: " + projectRes.toString());
                    } catch (Exception ex) {
                        Log.d(TAG, "collectData: " + ex.getMessage());
                    }
                });
    }

    private void updateProject(Project project) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String json = null;
        try {
            json = ow.writeValueAsString(project);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String B = json;
            String C=B;

        new ProjectRepo(getActivity()).updateProject(project)
                .observe(this, projectRes -> {
                    try {
                        if (projectRes.getFlag().equals(String.valueOf(Constant.RESPONSE_SUCCESS))) {
                            Utils.showStringToast(getActivity(), getString(R.string.project_updated_successfully));
                            getActivity().onBackPressed();
                        }
                        else if (projectRes.getFlag().equals(String.valueOf(Constant.RESPONSE_FAILURE))){
                            Utils.showStringToast(getActivity(),String.valueOf(projectRes.getMessage()));
                        }
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

        if (requestCode == GET_USERS_INCOMPANY && resultCode == Activity.RESULT_OK) {
            //Intent i = getActivity().getIntent();
            lstUsersnCompnays = (List<LstUsersnCompnay>) resultData.getSerializableExtra(Constant.USERS_LIST);
            if (lstUsersnCompnays.size() > 0) {
                Utils.showStringToast(getActivity(), getString(R.string.users_selected));
                setIdsAndNamesOfUsers();
                editTextProjectDirector.setText(taskOwnerNames);
            }
        }
        if (requestCode == GET_CURRNECY && resultCode == Activity.RESULT_OK) {
            //Intent i = getActivity().getIntent();
            currencyLists = (List<CurrencyList>) resultData.getSerializableExtra(Constant.TASK_CURRNECY_LIST);
            if (currencyLists.size() > 0) {
                setTaskCurrency();
                editTextProjectCoin.setText(selectedCurrencyName);
            }
        }
    }
    private void setTaskCurrency() {
        selectedCurrnecyID = String.valueOf(currencyLists.get(0).getCurrencyID());
        selectedCurrencyName = String.valueOf(currencyLists.get(0).getCurrencyName());
    }
    private void setIdsAndNamesOfUsers() {
        taskOwnerIDS = "";
        taskOwnerNames = "";
        for (int i = 0; i < lstUsersnCompnays.size(); i++) {
            taskOwnerNames += lstUsersnCompnays.get(i).getUsername() + "-";
            taskOwnerIDS += lstUsersnCompnays.get(i).getUserID() + "-";
        }
        taskOwnerNames = taskOwnerNames.substring(0, taskOwnerNames.length() - 1);

    }
}
