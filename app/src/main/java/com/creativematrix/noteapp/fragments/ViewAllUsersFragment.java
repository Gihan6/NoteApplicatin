package com.creativematrix.noteapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.adapters.AllUsersAdapter;
import com.creativematrix.noteapp.adapters.GroupsAdapter;
import com.creativematrix.noteapp.data.groups.DisplayGroupRequest;
import com.creativematrix.noteapp.data.groups.GroupRepo;
import com.creativematrix.noteapp.data.groups.LstGroup;
import com.creativematrix.noteapp.data.user.DisplayAllUsersRequest;
import com.creativematrix.noteapp.data.user.LstUsers;
import com.creativematrix.noteapp.data.user.UserRepo;
import com.creativematrix.noteapp.util.PreferenceHelper;
import com.creativematrix.noteapp.util.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewAllUsersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewAllUsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewAllUsersFragment extends Fragment {
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
    RecyclerView recycler_view_users;
    FloatingActionButton floating_button_add_user;
    AllUsersAdapter allUsersAdapter;
    private ArrayList<LstUsers> lstUsers = new ArrayList<>();
    Toolbar toolbar;
    FrameLayout empty_frame_layout;

    public ViewAllUsersFragment() {
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
    public static ViewAllUsersFragment newInstance(String param1, String param2) {
        ViewAllUsersFragment fragment = new ViewAllUsersFragment();
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
        View view = inflater.inflate(R.layout.fragment_view_all_users, container, false);
        configureViews(view);
        floating_button_add_user.setOnClickListener(view1 -> Utils.switchFragmentWithAnimation(R.id.fragment_holder_home, new AddNewUserFragment(), getActivity(), Utils.ADDNEWUSERFRAGMENT, Utils.AnimationType.SLIDE_UP));

        view.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                getActivity().onBackPressed();
            }
            return true;
        });
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

        new UserRepo(getActivity()).DisplayAllUsers(new DisplayAllUsersRequest(Long.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(getActivity()))))
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                            if (GroupRes.getLst().size() == 0) {
                                empty_frame_layout.setVisibility(View.VISIBLE);
                            }
                            lstUsers.clear();
                            lstUsers.addAll(GroupRes.getLst());
                            allUsersAdapter.notifyDataSetChanged();
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
        recycler_view_users = view.findViewById(R.id.recycler_view_users);
        empty_frame_layout = view.findViewById(R.id.empty_frame_layout);

        floating_button_add_user = view.findViewById(R.id.floating_button_add_user);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler_view_users.setLayoutManager(linearLayoutManager);
        recycler_view_users.setHasFixedSize(true);
        allUsersAdapter = new AllUsersAdapter(getActivity(), lstUsers, (v, position) ->
                Utils.switchFragmentWithAnimation(R.id.fragment_holder_home,
                        new UserDetailFragment(lstUsers.get(position)),
                        getActivity(), Utils.USERDETAILFRAGMENT, Utils.AnimationType.SLIDE_UP)
        );
        // Set adapter in recyclerView
        toolbar = view.findViewById(R.id.anim_toolbar);

        recycler_view_users.setAdapter(allUsersAdapter);
    }
}
