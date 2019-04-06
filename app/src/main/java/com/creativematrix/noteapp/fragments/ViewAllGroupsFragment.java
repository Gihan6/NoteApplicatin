package com.creativematrix.noteapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import com.creativematrix.noteapp.util.PreferenceHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.adapters.GroupsAdapter;
import com.creativematrix.noteapp.data.groups.DisplayGroupRequest;
import com.creativematrix.noteapp.data.groups.GroupRepo;
import com.creativematrix.noteapp.data.groups.LstGroup;
import com.creativematrix.noteapp.util.Utils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewAllGroupsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewAllGroupsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewAllGroupsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerViewGroups;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private OnFragmentInteractionListener mListener;
    RecyclerView recycler_view_groups;
    FloatingActionButton floating_button_add_group;
    GroupsAdapter groupsAdapter;
    private ArrayList<LstGroup> lstGroups = new ArrayList<>();
    Toolbar toolbar;

    public ViewAllGroupsFragment() {
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
    public static ViewAllGroupsFragment newInstance(String param1, String param2) {
        ViewAllGroupsFragment fragment = new ViewAllGroupsFragment();
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
        View view = inflater.inflate(R.layout.fragment_view_all_groups, container, false);
        configureViews(view);
        floating_button_add_group.setOnClickListener(
                view1 -> Utils.switchFragmentWithAnimation(R.id.fragment_holder_home,
                        new AddNewGroupFragment(), getActivity(),
                        Utils.ADDNEWGROUPFRAGMENT, Utils.AnimationType.SLIDE_UP
                ));

        view.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                getActivity().onBackPressed();
            }
            return true;
        });
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

        new GroupRepo(getActivity()).displayGroups(new DisplayGroupRequest(Long.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(getActivity())), Long.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(getActivity())), Long.valueOf(0)))
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                            lstGroups.clear();
                            lstGroups.addAll(GroupRes.getLstGroup());
                            groupsAdapter.notifyDataSetChanged();
                        } else {
                            Utils.showStringToast(getActivity(), String.valueOf(GroupRes.getMessage()));
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
        recycler_view_groups = view.findViewById(R.id.recycler_view_groups);
        floating_button_add_group = view.findViewById(R.id.floating_button_add_group);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler_view_groups.setLayoutManager(linearLayoutManager);
        recycler_view_groups.setHasFixedSize(true);
        groupsAdapter = new GroupsAdapter(getActivity(), lstGroups, (v, position) ->
                Utils.switchFragmentWithAnimation(R.id.fragment_holder_home,
                        new GroupDetailFragment(lstGroups.get(position)),
                        getActivity(), Utils.USERDETAILFRAGMENT, Utils.AnimationType.SLIDE_UP));
        // Set adapter in recyclerView
        toolbar = view.findViewById(R.id.anim_toolbar);

        recycler_view_groups.setAdapter(groupsAdapter);
    }
}
