package com.creativematrix.noteapp.fragments;

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
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.activities.NoteHomeActivity;
import com.creativematrix.noteapp.adapters.TasksAdapter;
import com.creativematrix.noteapp.data.download.Data;
import com.creativematrix.noteapp.data.task.DisplayTaskRequest;
import com.creativematrix.noteapp.data.task.Task;
import com.creativematrix.noteapp.data.task.TaskRepo;
import com.creativematrix.noteapp.firebase.FireBaseToken;
import com.creativematrix.noteapp.firebase.UpdateTockenRequest;
import com.creativematrix.noteapp.util.PreferenceHelper;
import com.creativematrix.noteapp.util.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.tonyodev.fetch2.Download;
import com.tonyodev.fetch2.Error;
import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.FetchConfiguration;
import com.tonyodev.fetch2.Request;
import com.tonyodev.fetch2.Status;
import com.tonyodev.fetch2core.Extras;
import com.tonyodev.fetch2core.FetchObserver;
import com.tonyodev.fetch2core.MutableExtras;
import com.tonyodev.fetch2core.Reason;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.util.ArrayList;


public class ViewAllTasksFragment extends Fragment implements FetchObserver<Download> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private OnFragmentInteractionListener mListener;
    DialogPlus confirmLogoutDialog, confirmPendingTask;
    FrameLayout empty_frame_layout;
    RecyclerView recycler_view_tasks;
    FloatingActionButton floating_button_add_task;
    TasksAdapter tasksAdapter;
    private ArrayList<Task> tasks = new ArrayList<>();
    Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private Request request;
    private Fetch fetch;
    View view;

    public ViewAllTasksFragment() {
        // Required empty public constructor
    }

    private void updateToken() {
        UpdateTockenRequest request = new UpdateTockenRequest();
        if (PreferenceHelper.getPrefernceHelperInstace().getIsCompany(getActivity()) == true) {
            request.setTypeNo(1);
        }else {
            request.setTypeNo(2);
        }
        request.setID(Long.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(getActivity())));
        request.setToken(new FireBaseToken().getToken());

        new TaskRepo(getActivity()).updateTocken(request)
                .observe(this, updateTockenResponse -> {

                    String cc=updateTockenResponse.getMessage();

                });
    }

    public static ViewAllTasksFragment newInstance(String param1, String param2) {
        ViewAllTasksFragment fragment = new ViewAllTasksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onChanged(Download data, @NotNull Reason reason) {
        updateViews(data, reason);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        updateToken();
    }

    private void updateViews(Download download, Reason reason) {

        if (request.getId() == download.getId()) {
            if (reason == Reason.DOWNLOAD_QUEUED || reason == Reason.DOWNLOAD_COMPLETED) {
                Utils.showStringToast(getActivity(), "Downloading : " + download.getFile());
            }
            setProgressView(download.getStatus(), download.getProgress());
            // Utils.showStringToast(getActivity(),download.getFile());
            //etaTextView.setText(Utils.getETAString(getActivity(), download.getEtaInMilliSeconds()));
            // downloadSpeedTextView.setText(Utils.getDownloadSpeedString(getActivity(), download.getDownloadedBytesPerSecond()));
            if (download.getError() != Error.NONE) {
                showDownloadErrorSnackBar(download.getError());
            }
        }
    }

    private void showDownloadErrorSnackBar(@NotNull Error error) {
        Utils.showStringToast(getActivity(), "Download Failed: ErrorCode: " + error);
        //final Snackbar snackbar = Snackbar.make(view, "Download Failed: ErrorCode: " + error, Snackbar.LENGTH_INDEFINITE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_all_tasks, container, false);
        configureViews(view);
        FetchConfiguration fetchConfiguration = new FetchConfiguration.Builder(getActivity())
                .setDownloadConcurrentLimit(3)
                .build();

        fetch = Fetch.Impl.getInstance(fetchConfiguration);
        floating_button_add_task.setOnClickListener(view1 ->
                Utils.switchFragmentWithAnimation(R.id.fragment_holder_home,
                        new AddNewTaskFragment(), getActivity(), Utils.ADDNEWTASKFRAGMENT,
                        Utils.AnimationType.SLIDE_UP));
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener((v, keyCode, event) -> {

            if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(Gravity.START); //CLOSE Nav Drawer!
                } else {
                    confirmLogoutDialog = DialogPlus.newDialog(getActivity())
                            .setContentHolder(new ViewHolder(R.layout.custom_dialog_confrim_log_out))
                            .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                            .setGravity(Gravity.CENTER)
                            .create();
                    confirmLogoutDialog.show();

                    Button btnOk = (Button) confirmLogoutDialog.findViewById(R.id.btnOk);

                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            confirmLogoutDialog.dismiss();
                            getActivity().finish();

                        }
                    });
                    Button btnCancel = (Button) confirmLogoutDialog.findViewById(R.id.btnCancel);

                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            confirmLogoutDialog.dismiss();
                        }
                    });
                }


            }

            return true;

        });


        new TaskRepo(getActivity()).displayTasks(new DisplayTaskRequest
                (Long.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(getActivity())),
                        Utils.getLang()))
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                            if (GroupRes.getTasks().size() == 0) {
                                empty_frame_layout.setVisibility(View.VISIBLE);
                            }
                            tasks.clear();
                            tasks.addAll(GroupRes.getTasks());
                            tasksAdapter.notifyDataSetChanged();
                        }
                    } catch (Exception ex) {

                    }
                });
        return view;
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void configureViews(View view) {
        recycler_view_tasks = view.findViewById(R.id.recycler_view_tasks);
        empty_frame_layout = view.findViewById(R.id.empty_frame_layout);
        floating_button_add_task = view.findViewById(R.id.floating_button_add_task);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler_view_tasks.setLayoutManager(linearLayoutManager);
        recycler_view_tasks.setHasFixedSize(true);
        tasksAdapter = new TasksAdapter(getActivity(), tasks, (v, position) ->
        {
            if (tasks.get(position).getPending() != null) {

                if (tasks.get(position).getPending()) {
                    showPendingTaskDialog(tasks.get(position));

                } else {
                    Utils.switchFragmentWithAnimation(R.id.fragment_holder_home,
                            new TaskDetailFragment(tasks.get(position)),
                            getActivity(), Utils.VIEWAllTASKSFRAGGMENT, Utils.AnimationType.SLIDE_UP);
                }
            } else {
                Utils.switchFragmentWithAnimation(R.id.fragment_holder_home,
                        new TaskDetailFragment(tasks.get(position)),
                        getActivity(), Utils.VIEWAllTASKSFRAGGMENT, Utils.AnimationType.SLIDE_UP);
            }
        }


        );
        // Set adapter in recyclerView
        toolbar = view.findViewById(R.id.anim_toolbar);
        ((NoteHomeActivity) getActivity()).setSupportActionBar(toolbar);
        ((NoteHomeActivity) getActivity()).getSupportActionBar()
                .setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> ((NoteHomeActivity) getActivity()).getmDrawerLayout()
                .openDrawer(GravityCompat.START));
        mDrawerLayout = getActivity().findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        recycler_view_tasks.setAdapter(tasksAdapter);
    }

    private void showPendingTaskDialog(Task task) {
        confirmPendingTask = DialogPlus.newDialog(getActivity())
                .setContentHolder(new ViewHolder(R.layout.custom_dialog_confirm_peningtask))
                .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setGravity(Gravity.CENTER)
                .create();
        confirmPendingTask.show();

        LinearLayout btnCompleteTask = (LinearLayout) confirmPendingTask.findViewById(R.id.btnCompleteTask);

        btnCompleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmPendingTask.dismiss();
                Utils.switchFragmentWithAnimation(R.id.fragment_holder_home,
                        new AddNewTaskFragment(task),
                        getActivity(), Utils.ADDNEWTASKFRAGMENT, Utils.AnimationType.SLIDE_UP);
            }
        });
        LinearLayout btnDownloadAttachement = (LinearLayout) confirmPendingTask.findViewById(R.id.btnDownloadAttachement);

        btnDownloadAttachement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmPendingTask.dismiss();
                for (int i = 0; i < task.getFilesBinaryList().size(); i++) {
                    try {
                        downloadFile(task.getFilesBinaryList().get(i).getFileName(), task.getFilesBinaryList().get(i).getFileName());


                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        LinearLayout btnCancel = (LinearLayout) confirmPendingTask.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // deleteMyLocation(locationslist.get(position));
                confirmPendingTask.dismiss();                       // Sort_Price = "1";
            }
        });
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

    private void setProgressView(@NonNull final Status status, final int progress) {
        switch (status) {
            case QUEUED: {
                Utils.showResToast(getActivity(), R.string.queued);
                //progressTextView.setText(R.string.queued);
                break;
            }
            case ADDED: {
                Utils.showResToast(getActivity(), R.string.added);
                // progressTextView.setText(R.string.added);
                break;
            }
            case DOWNLOADING:
            case COMPLETED: {
                if (progress == -1) {
                    //     progressTextView.setText(R.string.downloading);
                    Utils.showResToast(getActivity(), R.string.downloading);
                } else {
                    // final String progressString = getResources().getString(R.string.percent_progress, progress);
                    // progressTextView.setText(progressString);
                    Utils.showStringToast(getActivity(), getResources().getString(R.string.download_complete));
                    //   activity_single_download.setVisibility(View.GONE);
                }
                break;
            }
            default: {
                // progressTextView.setText(R.string.status_unknown);
                break;
            }
        }
    }
}
