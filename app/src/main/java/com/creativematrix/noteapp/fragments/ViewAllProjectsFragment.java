package com.creativematrix.noteapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.adapters.ProjectAdapter;
import com.creativematrix.noteapp.data.project.DisplayProjectRequest;
import com.creativematrix.noteapp.data.project.Project;
import com.creativematrix.noteapp.data.project.ProjectRepo;
import com.creativematrix.noteapp.util.PreferenceHelper;
import com.creativematrix.noteapp.util.Utils;

import java.util.ArrayList;

public class ViewAllProjectsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private ViewAllTasksFragment.OnFragmentInteractionListener mListener;
FrameLayout     empty_frame_layout;


    RecyclerView recycler_view_projects;
    FloatingActionButton floating_button_add_project;
    ProjectAdapter projectAdapter;
    private ArrayList<Project> projects = new ArrayList<>();
    Toolbar toolbar;


    public ViewAllProjectsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_view_all_projects, container, false);
        configureViews(view);
        floating_button_add_project.setOnClickListener(view1 -> Utils.switchFragmentWithAnimation(R.id.fragment_holder_home, new AddNewProjectFragment(), getActivity(), Utils.ADDNEWPROJECTFRAGMENT, Utils.AnimationType.SLIDE_UP));
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                getActivity().onBackPressed();
            }
            return true;
        });
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());


        new ProjectRepo(getActivity()).displayProjectsResponseLiveData(new DisplayProjectRequest(Long.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(getActivity())), Utils.getLang()))
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                            if(GroupRes.getProjects().size()==0){
                                empty_frame_layout.setVisibility(View.VISIBLE);
                            }
                            projects.clear();
                            projects.addAll(GroupRes.getProjects());
                            projectAdapter.notifyDataSetChanged();
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
        recycler_view_projects = view.findViewById(R.id.recycler_view_projects);
        floating_button_add_project = view.findViewById(R.id.floating_button_add_project);
        empty_frame_layout = view.findViewById(R.id.empty_frame_layout);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler_view_projects.setLayoutManager(linearLayoutManager);
        recycler_view_projects.setHasFixedSize(true);
        projectAdapter = new ProjectAdapter(getActivity(), projects, (v, position) ->
                Utils.switchFragmentWithAnimation(R.id.fragment_holder_home, new ProjectDetailFragment(projects.get(position)), getActivity(), Utils.PROJECT_DETAIL_FRAGMENT, Utils.AnimationType.SLIDE_UP)

        );
        // Set adapter in recyclerView
        toolbar = view.findViewById(R.id.anim_toolbar);
        /*
        ((NoteHomeActivity) getActivity()).setSupportActionBar(toolbar);
        ((NoteHomeActivity) getActivity()).getSupportActionBar()
                .setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> ((NoteHomeActivity) getActivity()).getmDrawerLayout()
                .openDrawer(GravityCompat.START));*/

        recycler_view_projects.setAdapter(projectAdapter);
    }
}
