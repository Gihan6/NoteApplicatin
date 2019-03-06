package com.creativematrix.noteapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.activities.NoteHomeActivity;
import com.creativematrix.noteapp.adapters.GroupsAdapter;
import com.creativematrix.noteapp.adapters.TasksAdapter;
import com.creativematrix.noteapp.data.groups.DisplayGroupRequest;
import com.creativematrix.noteapp.data.groups.GroupRepo;
import com.creativematrix.noteapp.data.groups.LstGroup;
import com.creativematrix.noteapp.data.task.DisplayTaskRequest;
import com.creativematrix.noteapp.data.task.Task;
import com.creativematrix.noteapp.data.task.TaskRepo;
import com.creativematrix.noteapp.util.PreferenceHelper;
import com.creativematrix.noteapp.util.Utils;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewAllTasksFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewAllTasksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewAllTasksFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private OnFragmentInteractionListener mListener;
    DialogPlus dialog;

    RecyclerView recycler_view_tasks;
    FloatingActionButton floating_button_add_task;
    TasksAdapter tasksAdapter;
    private ArrayList<Task> tasks=new ArrayList<>();
    Toolbar toolbar;
    private DrawerLayout mDrawerLayout;

    public ViewAllTasksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewAllGroupsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewAllTasksFragment newInstance(String param1, String param2) {
        ViewAllTasksFragment fragment = new ViewAllTasksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_all_tasks, container, false);
        configureViews(view);
        floating_button_add_task.setOnClickListener(view1 -> Utils.switchFragmentWithAnimation(R.id.fragment_holder_home, new AddNewTaskFragment(), getActivity(), Utils.ADDNEWTASKFRAGMENT, Utils.AnimationType.SLIDE_UP));
        view.setFocusableInTouchMode(true);
                    view.requestFocus();
                    view.setOnKeyListener((v, keyCode, event) -> {

            if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(Gravity.START); //CLOSE Nav Drawer!
                } else {
                    dialog = DialogPlus.newDialog(getActivity())
                            .setContentHolder(new ViewHolder(R.layout.custom_dialog_confrim_log_out))
                            .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                            .setGravity(Gravity.CENTER)
                            .create();
                    dialog.show();

                    Button btnOk = (Button) dialog.findViewById(R.id.btnOk);

                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            getActivity().finish();

                            // Sort_Price = "1";
                            // getAllOffers();
                        }
                    });
                    Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);

                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // deleteMyLocation(locationslist.get(position));
                            dialog.dismiss();                       // Sort_Price = "1";
                        }
                    });
                }


            }

            return true;

        });


        new TaskRepo(getActivity()).displayTasks(new DisplayTaskRequest(Long.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(getActivity()))))
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)){
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
        recycler_view_tasks=view.findViewById(R.id.recycler_view_tasks);
        floating_button_add_task=view.findViewById(R.id.floating_button_add_task);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler_view_tasks.setLayoutManager(linearLayoutManager);
        recycler_view_tasks.setHasFixedSize(true);
        tasksAdapter=new TasksAdapter(getActivity(), tasks, (v, position) -> Utils.showStringToast(getActivity(),String.valueOf("")));
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
}
