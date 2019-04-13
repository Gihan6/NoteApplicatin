package com.creativematrix.noteapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.adapters.CustomGroupsAdapter;
import com.creativematrix.noteapp.adapters.CustomProjectsInCompanyAdapter;
import com.creativematrix.noteapp.adapters.CustomSelectUserInCompanyAdapter;
import com.creativematrix.noteapp.adapters.GroupsAdapter;
import com.creativematrix.noteapp.data.groups.DisplayGroupRequest;
import com.creativematrix.noteapp.data.groups.GroupRepo;
import com.creativematrix.noteapp.data.groups.LstGroup;
import com.creativematrix.noteapp.data.project.DisplayProjectRequest;
import com.creativematrix.noteapp.data.project.Project;
import com.creativematrix.noteapp.data.project.ProjectRepo;
import com.creativematrix.noteapp.util.PreferenceHelper;
import com.creativematrix.noteapp.util.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class SelectGroupActivity extends AppCompatActivity implements Button.OnClickListener {
    private ListView listView;
    private Button button;
    private ArrayList<LstGroup> lstGroups=new ArrayList<>();
    CustomGroupsAdapter customGroupsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_project);
        findViewsById();
        customGroupsAdapter = new CustomGroupsAdapter(this, lstGroups);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(customGroupsAdapter);

        new GroupRepo(this).displayGroups(new DisplayGroupRequest(Long.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(this
        )), Long.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(this)), Long.valueOf(0)))
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)) {
                            lstGroups.clear();
                            lstGroups.addAll(GroupRes.getLstGroup());
                            customGroupsAdapter.notifyDataSetChanged();
                        }
                    } catch (Exception ex) {

                    }
                });
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                List<LstGroup> selected = getSelectedItems();
                Intent returnIntent = new Intent();
                returnIntent.putExtra(Constant.GROUPS_LIST, (Serializable) selected);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();

                break;
        }
    }

    private void findViewsById() {
        listView = (ListView) findViewById(R.id.list);
        button = (Button) findViewById(R.id.button);

    }

    private List<LstGroup> getSelectedItems() {
        List<LstGroup> result = new ArrayList<>();
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();

        for (int i = 0; i < listView.getCount(); ++i) {
            if (checkedItems.valueAt(i)) {
                result.add((LstGroup) listView.getItemAtPosition(checkedItems.keyAt(i)));
            }
        }

        return result;
    }
}
