package com.creativematrix.noteapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.callback.ProjectCallbacks;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class ViewAllProjectsFragment extends Fragment {
    private RecyclerView recyclerViewProjects;
    private FloatingActionButton fabAdd;
    private ProjectCallbacks mCallbacks;
    private Context mContext;
    DrawerLayout mDrawerLayout;
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
        View view = inflater.inflate(R.layout.fragment_view_all_projects, container, false);
        configureViews(view);
        fabAdd.setOnClickListener(v -> {
            mCallbacks.onAddProjectClicked();
        });
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        return view;
    }

    private void configureViews(View view) {
        recyclerViewProjects = view.findViewById(R.id.recycler_view_all_projects);
        fabAdd = view.findViewById(R.id.fab_add_project);
        mDrawerLayout = getActivity().findViewById(R.id.drawer_layout);

    }
}
