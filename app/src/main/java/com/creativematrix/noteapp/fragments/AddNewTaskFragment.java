package com.creativematrix.noteapp.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.android.material.textfield.TextInputEditText;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.activities.SelectProjectActivity;
import com.creativematrix.noteapp.activities.SelectTaskCoinActivity;
import com.creativematrix.noteapp.activities.SelectTaskOwnersActivity;
import com.creativematrix.noteapp.activities.SelectTaskStatusActivity;
import com.creativematrix.noteapp.adapters.CustomFilesAdapter;
import com.creativematrix.noteapp.callback.ProjectCallbacks;
import com.creativematrix.noteapp.data.coins.CurrencyList;
import com.creativematrix.noteapp.data.project.Project;
import com.creativematrix.noteapp.data.task.FilesBinary;
import com.creativematrix.noteapp.data.task.LstUsersnCompnay;
import com.creativematrix.noteapp.data.task.Task;
import com.creativematrix.noteapp.data.task.TaskRepo;
import com.creativematrix.noteapp.data.task.TaskStatus;
import com.creativematrix.noteapp.util.PreferenceHelper;
import com.creativematrix.noteapp.util.Utils;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import ir.sohreco.androidfilechooser.ExternalStorageNotAvailableException;
import ir.sohreco.androidfilechooser.FileChooser;

public class AddNewTaskFragment extends Fragment {
    private final static int READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 13;
    private ProjectCallbacks mCallbacks;
    public static final String TAG = AddNewTaskFragment.class.getSimpleName();
    private TextInputEditText editTextTaskName;
    private TextInputEditText editTextTaskDescription;
    private TextInputEditText editTextTaskCost;
    private TextInputEditText editTextTaskOwner;
    private TextInputEditText editTextTaskStartTime;
    private TextView txtAttachFile;
    private TextInputEditText editTextTaskStartDate;
    private TextInputEditText editTextTaskEndTime;
    private TextInputEditText editTextTaskEndDate;
    private TextInputEditText editTextTaskStatus;
    private TextInputEditText editTextTaskCoin;
    private TextInputEditText editTextProjectName;
    private static final int GET_USERS_INCOMPANY = 20;
    private static final int GET_PROJECTS_INCOMPANY = 21;
    private static final int GET_TASK_STATUS = 22;
    private static final int GET_CURRNECY = 23;
    private List<FilesBinary> filesBinaryList = new ArrayList<>();
    private Button buttonSaveProject;
    private Context mContext;
    private List<LstUsersnCompnay> lstUsersnCompnays = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private List<TaskStatus> taskStatuses = new ArrayList<>();
    private List<CurrencyList> currencyLists = new ArrayList<>();
    CustomFilesAdapter customFilesAdapter;
    String taskOwnerIDS = "", taskOwnerNames = "", selectedProjectName = "", selectedProjectID = "", selectedTaskStatusName = "", selectedTaskStatusID = "", selectedCurrnecyID = "", selectedCurrencyName = "";
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    RecyclerView recycler_view_files;

    String taskStatus;

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
        txtAttachFile.setOnClickListener(view1 -> {
            int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
            } else {
                addFileChooserFragment();
            }
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
        editTextTaskStatus.setOnClickListener(v -> {
            startActivityForResult(new Intent(getActivity(), SelectTaskStatusActivity.class), GET_TASK_STATUS);

        });
        editTextTaskCoin.setOnClickListener(v -> {
            startActivityForResult(new Intent(getActivity(), SelectTaskCoinActivity.class), GET_CURRNECY);

        });


        editTextTaskEndTime.setOnClickListener(v -> {
            showTimeFragment();
            mCallbacks.onEndTimeClicked(Constant.END_TIME);
        });

        buttonSaveProject.setOnClickListener(v -> {
            try {
                collectData();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });


        return view;
    }

    private void addFileChooserFragment() {
        FileChooser.Builder builder = new FileChooser.Builder(FileChooser.ChooserType.FILE_CHOOSER,
                (FileChooser.ChooserListener) path ->
                {
                    getActivity().onBackPressed();
                    Toast.makeText(getActivity(), path, Toast.LENGTH_SHORT).show();
                    String filename = path.substring(path.lastIndexOf("/") + 1);
                    String fileExtension = path.substring(path.lastIndexOf(".") + 1);
                    String fileContent = null;
                    fileContent = Utils.getFileBinary(path);
                    filesBinaryList.add(new FilesBinary(filename, fileExtension, fileContent));
                    customFilesAdapter.notifyDataSetChanged();

                }

        );
        try {
            Utils.switchFragmentWithAnimation(R.id.fragment_holder_home, builder.build(), getActivity(), Utils.VIEWAllTASKSFRAGGMENT, Utils.AnimationType.SLIDE_UP);

        } catch (ExternalStorageNotAvailableException e) {
            Toast.makeText(getActivity(), "There is no external storage available on this device.",
                    Toast.LENGTH_SHORT).show();e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                addFileChooserFragment();
            }
        }
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
        txtAttachFile = view.findViewById(R.id.txtAttachFile);
        editTextProjectName = view.findViewById(R.id.input_project_name);
        recycler_view_files = view.findViewById(R.id.recycler_view_files);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler_view_files.setLayoutManager(linearLayoutManager);
        recycler_view_files.setHasFixedSize(true);
        customFilesAdapter = new CustomFilesAdapter(getActivity(), filesBinaryList, new CustomFilesAdapter.MyAdapterListener() {
            @Override
            public void deleteFile(View v, int position) {
                filesBinaryList.remove(position);
                customFilesAdapter.notifyDataSetChanged();
            }
        });

        recycler_view_files.setAdapter(customFilesAdapter);
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

    private void collectData() throws JsonProcessingException {
        String taskName = editTextTaskName.getText().toString();
        String ProjectID = selectedProjectID;
        String taskDesc = editTextTaskDescription.getText().toString();
        String taskCost = editTextTaskCost.getText().toString();
        String CurrencyID = selectedCurrnecyID;
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
        if (Utils.isFieldEmpty(ProjectID)) {
            Utils.showResToast(mContext, R.string.project_name_empty);
            return;
        }

        if (Utils.isFieldEmpty(taskStartTime)) {
            Utils.showResToast(mContext, R.string.task_start_time_empty);
            return;
        }
        if (Utils.isFieldEmpty(taskEndTime)) {
            Utils.showResToast(mContext, R.string.task_end_time_empty);
            return;
        }
        if (Utils.isFieldEmpty(taskStartDate)) {
            Utils.showResToast(mContext, R.string.task_start_date_empty);
            return;
        }
        if (Utils.isFieldEmpty(taskEndDate)) {
            Utils.showResToast(mContext, R.string.task_end_date_empty);
            return;
        }

        if (Utils.isFieldEmpty(selectedTaskStatusID)) {
            Utils.showResToast(mContext, R.string.task_name_empty);
            return;
        }
        Task task = new Task();
        task.setTaskName(taskName);
        task.setTaskDescripation(taskDesc);
        task.setProjectID(Long.valueOf(ProjectID));
        task.setUsersIDs(taskOwnerIDS);
        task.setTaskCost(Long.valueOf(taskCost));
        task.setTaskState(Long.valueOf(selectedTaskStatusID));
        task.setCurrencyID(CurrencyID);
        task.setCompanyID(Long.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(getActivity())));
        task.setAddedID(Long.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(getActivity())));
        try {
            task.setStartTime(Utils.formatDate(taskStartDate + " " + taskStartTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            task.setEndTime(Utils.formatDate(taskEndDate + " " + taskEndTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        task.setFilesBinaryList(filesBinaryList);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(task);

        new TaskRepo(getActivity()).addTask(task)
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                            Utils.showStringToast(getActivity(), getString(R.string.task_added_success));
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
            if (lstUsersnCompnays.size() > 0) {
                Utils.showStringToast(getActivity(), getString(R.string.users_selected));
                setIdsAndNamesOfUsers();
                editTextTaskOwner.setText(taskOwnerNames);
            }
        }

        if (requestCode == GET_PROJECTS_INCOMPANY && resultCode == Activity.RESULT_OK) {
            //Intent i = getActivity().getIntent();
            projects = (List<Project>) resultData.getSerializableExtra(Constant.PROJECTS_LIST);
            if (projects.size() > 0) {
                Utils.showStringToast(getActivity(), getString(R.string.Project_selected));
                setIdsAndNamesOProject();
                editTextProjectName.setText(selectedProjectName);
            }
        }

        if (requestCode == GET_TASK_STATUS && resultCode == Activity.RESULT_OK) {
            //Intent i = getActivity().getIntent();
            taskStatuses = (List<TaskStatus>) resultData.getSerializableExtra(Constant.TASK_STATUS_LIST);
            if (taskStatuses.size() > 0) {
                setTaskStatus();
                editTextTaskStatus.setText(selectedTaskStatusName);
            }
        }

        if (requestCode == GET_CURRNECY && resultCode == Activity.RESULT_OK) {
            //Intent i = getActivity().getIntent();
            currencyLists = (List<CurrencyList>) resultData.getSerializableExtra(Constant.TASK_CURRNECY_LIST);
            if (currencyLists.size() > 0) {
                setTaskCurrency();
                editTextTaskCoin.setText(selectedCurrencyName);
            }
        }
    }

    private void setTaskStatus() {
        selectedTaskStatusName = String.valueOf(taskStatuses.get(0).getTaskStatusName());
        selectedTaskStatusID = String.valueOf(taskStatuses.get(0).getID());
    }

    private void setIdsAndNamesOfUsers() {
        taskOwnerIDS = "";
        taskOwnerNames = "";
        for (int i = 0; i < lstUsersnCompnays.size(); i++) {
            taskOwnerNames += lstUsersnCompnays.get(i).getUsername() + "-";
            taskOwnerIDS += lstUsersnCompnays.get(i).getUserID() + "-";
        }
    }


    private void setIdsAndNamesOProject() {
        selectedProjectID = String.valueOf(projects.get(0).getId());
        selectedProjectName = String.valueOf(projects.get(0).getProjectName());
    }

    private void setTaskCurrency() {
        selectedCurrnecyID = String.valueOf(currencyLists.get(0).getCurrencyID());
        selectedCurrencyName = String.valueOf(currencyLists.get(0).getCurrencyName());
    }

}
