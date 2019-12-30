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
import android.widget.Toast;

import com.tonyodev.fetch2.Error;
import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.adapters.GroupsAdapter;
import com.creativematrix.noteapp.data.download.Data;
import com.creativematrix.noteapp.data.groups.LstGroup;
import com.creativematrix.noteapp.data.task.DisplayTaskDetailsResponse;
import com.creativematrix.noteapp.data.task.Task;
import com.creativematrix.noteapp.data.task.TaskRepo;
import com.creativematrix.noteapp.util.Utils;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.thin.downloadmanager.DefaultRetryPolicy;
import com.thin.downloadmanager.DownloadRequest;
import com.thin.downloadmanager.DownloadStatusListener;
import com.thin.downloadmanager.ThinDownloadManager;
import com.tonyodev.fetch2.Download;
import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.FetchConfiguration;
import com.tonyodev.fetch2.FetchListener;
import com.tonyodev.fetch2.NetworkType;
import com.tonyodev.fetch2.Priority;
import com.tonyodev.fetch2.Request;
import com.tonyodev.fetch2.Status;
import com.tonyodev.fetch2core.Extras;
import com.tonyodev.fetch2core.FetchObserver;
import com.tonyodev.fetch2core.Func;
import com.tonyodev.fetch2core.MutableExtras;
import com.tonyodev.fetch2core.Reason;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


@SuppressLint("ValidFragment")
public class TaskDetailFragment extends Fragment implements FetchObserver<Download> {
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
    LinearLayout activity_single_download;
    TextView task_name_title, txtDownloadFile, task_name, task_desc, task_cost, task_start_time, task_end_time, task_owners, file_name, task_status, project_name;
    Button btnDelete, btnUpdate;
    private TextView progressTextView;
    private TextView titleTextView;
    private TextView etaTextView;
    private TextView downloadSpeedTextView;
    DialogPlus dialog;
    LinearLayout file_download;
    DisplayTaskDetailsResponse displayTaskDetailsResponse;
    FetchConfiguration fetchConfiguration;
    View view;

    public TaskDetailFragment(Task task) {
        this.mTask = task;
    }

    private ThinDownloadManager downloadManager;
    private Request request;
    private Fetch fetch;


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
        view = inflater.inflate(R.layout.fragment_task_detail, container, false);
        FetchConfiguration fetchConfiguration = new FetchConfiguration.Builder(getActivity())
                .setDownloadConcurrentLimit(3)
                .build();

        fetch = Fetch.Impl.getInstance(fetchConfiguration);
        configureViews(view);

        view.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                getActivity().onBackPressed();
            }
            return true;
        });
        txtDownloadFile.setOnClickListener(v -> {


            for (int i = 0; i < displayTaskDetailsResponse.getTaskesfiles().size(); i++) {
                try {
                    downloadFile(displayTaskDetailsResponse.getTaskesfiles().get(i).getFileExt(), displayTaskDetailsResponse.getTaskesfiles().get(i).getFileName());


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }
        });
        btnDelete.setOnClickListener(v -> {
            deleteTask();
        });
        btnUpdate.setOnClickListener(v -> {
            Utils.switchFragmentWithAnimation(R.id.fragment_holder_home,
                    new AddNewTaskFragment(displayTaskDetailsResponse), getActivity(),
                    Utils.ADDNEWTASKFRAGMENT, Utils.AnimationType.SLIDE_UP
            );
        });
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

        new TaskRepo(getActivity()).displayTaskDetails(mTask)
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                            displayTaskDetailsResponse = GroupRes;
                            initialzeData(GroupRes);
                            if (displayTaskDetailsResponse.getTaskesfiles().size() > 0) {
                                file_download.setVisibility(View.VISIBLE);
                                file_name.setText(displayTaskDetailsResponse.getTaskesfiles().get(0).getFileName());
                            } else {
                                file_download.setVisibility(View.GONE);
                            }
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
        mTask.setLang(Utils.getLang());
        btnYes.setOnClickListener(v ->

                {
                    dialog.dismiss();
                    confirm_delete_task(mTask);
                }
        );
        btnNo.setOnClickListener(v -> dialog.dismiss());
    }

    private void confirm_delete_task(Task mTask) {
        new TaskRepo(getActivity()).deleteTask(mTask)
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                            Utils.showStringToast(getActivity(), getResources().getString(R.string.deleted_succees));
                        } else if (GroupRes.getFlag().equals(String.valueOf(Constant.RESPONSE_FAILURE))) {
                            Utils.showStringToast(getActivity(), String.valueOf(GroupRes.getMessage()));
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
        project_name.setText(groupRes.getProjectName());
        task_cost.setText(String.valueOf(groupRes.getTaskCost()));
        String ownersNames = "";
        for (int i = 0; i < groupRes.getTaskUsers().size(); i++) {
            ownersNames += groupRes.getTaskUsers().get(i).getUserName() + "-";
        }
        ownersNames = ownersNames.substring(0, ownersNames.length() - 1);

        task_owners.setText(ownersNames);
        if (groupRes.getTaskStautes() == false) {
            task_status.setText(getResources().getString(R.string.task_under_processing));
        } else {
            task_status.setText(getResources().getString(R.string.task_completed));
        }


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

    @Override
    public void onChanged(Download data, @NotNull Reason reason) {
        updateViews(data, reason);
    }

    private void updateViews(Download download, Reason reason) {

        if (request.getId() == download.getId()) {
            if (reason == Reason.DOWNLOAD_QUEUED || reason == Reason.DOWNLOAD_COMPLETED) {
                setTitleView(download.getFile());
            }
            setProgressView(download.getStatus(), download.getProgress());
            etaTextView.setText(Utils.getETAString(getActivity(), download.getEtaInMilliSeconds()));
            downloadSpeedTextView.setText(Utils.getDownloadSpeedString(getActivity(), download.getDownloadedBytesPerSecond()));
            if (download.getError() != Error.NONE) {
                showDownloadErrorSnackBar(download.getError());
            }
        }
    }

    private void showDownloadErrorSnackBar(@NotNull Error error) {
        final Snackbar snackbar = Snackbar.make(view, "Download Failed: ErrorCode: " + error, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.retry, v -> {
            fetch.retry(request.getId());
            snackbar.dismiss();
        });
        snackbar.show();
    }

    private void setTitleView(@NonNull final String fileName) {
        final Uri uri = Uri.parse(fileName);
        titleTextView.setText(uri.getLastPathSegment());
    }

    private void setProgressView(@NonNull final Status status, final int progress) {
        switch (status) {
            case QUEUED: {
                progressTextView.setText(R.string.queued);
                break;
            }
            case ADDED: {
                progressTextView.setText(R.string.added);
                break;
            }
            case DOWNLOADING:
            case COMPLETED: {
                if (progress == -1) {
                    progressTextView.setText(R.string.downloading);
                } else {
                    final String progressString = getResources().getString(R.string.percent_progress, progress);
                    progressTextView.setText(progressString);
                    Utils.showStringToast(getActivity(), getResources().getString(R.string.download_complete));
                    activity_single_download.setVisibility(View.GONE);
                }
                break;
            }
            default: {
                progressTextView.setText(R.string.status_unknown);
                break;
            }
        }
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
        progressTextView = view.findViewById(R.id.progressTextView);
        titleTextView = view.findViewById(R.id.titleTextView);
        etaTextView = view.findViewById(R.id.etaTextView);
        downloadSpeedTextView = view.findViewById(R.id.downloadSpeedTextView);
        activity_single_download = view.findViewById(R.id.activity_single_download);
    }

    private Extras getExtrasForRequest(Request request) {
        final MutableExtras extras = new MutableExtras();
        extras.putBoolean("testBoolean", true);
        extras.putString("testString", "test");
        extras.putFloat("testFloat", Float.MIN_VALUE);
        extras.putDouble("testDouble", Double.MIN_VALUE);
        extras.putInt("testInt", Integer.MAX_VALUE);
        extras.putLong("testLong", Long.MAX_VALUE);
        return extras;
    }


    private void downloadFile(String url, String fileName) throws MalformedURLException {
        // String file = getPathFromURL(url);

        //final String url = Data.sampleUrls[0];
        final String filePath = Data.getSaveDir() + "/NoteApp/" + Data.getNameFromUrl(url);
        request = new Request(url, filePath);
        request.setExtras(getExtrasForRequest(request));
        fetch.attachFetchObserversForDownload(request.getId(), this)
                .enqueue(request, updatedRequest -> {
                    //Request was successfully enqueued for download.
                }, error -> {
                    Utils.showStringToast(getActivity(), error.toString());
                    //An error occurred enqueuing the request.
                });
    }

    private String getPathFromURL(String url) throws MalformedURLException {
        String path = new URL(url).getPath();
        path = path.replaceFirst("/", "");
        return path;
    }
}
