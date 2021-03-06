package com.creativematrix.noteapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.adapters.CustomProjectsInCompanyAdapter;
import com.creativematrix.noteapp.adapters.CustomSelectUserInCompanyAdapter;
import com.creativematrix.noteapp.data.project.DisplayProjectRequest;
import com.creativematrix.noteapp.data.project.Project;
import com.creativematrix.noteapp.data.project.ProjectRepo;
import com.creativematrix.noteapp.util.PreferenceHelper;
import com.creativematrix.noteapp.util.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelectProjectActivity extends AppCompatActivity implements Button.OnClickListener {
    private ListView listView;
    private Button button;
    private ArrayList<Project> projects=new ArrayList<>();
    FrameLayout empty_frame_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_project);
        findViewsById();
        CustomProjectsInCompanyAdapter adapter = new CustomProjectsInCompanyAdapter(this, projects);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);

        new ProjectRepo(SelectProjectActivity.this).displayProjectsResponseLiveData(new DisplayProjectRequest(Long.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(SelectProjectActivity.this)), Utils.getLang()))
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)){
                            if (GroupRes.getProjects().size() == 0) {
                                empty_frame_layout.setVisibility(View.VISIBLE);
                                button.setVisibility(View.GONE);
                            }
                            projects.clear();
                            projects.addAll(GroupRes.getProjects());
                            adapter.notifyDataSetChanged();
                        }
                        else {
                            empty_frame_layout.setVisibility(View.VISIBLE);
                            button.setVisibility(View.GONE);
                        }
                    } catch (Exception ex) {
                        empty_frame_layout.setVisibility(View.VISIBLE);
                        button.setVisibility(View.GONE);
                    }
                });


        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                ArrayList<Project> selected = getSelectedItems();
                Intent returnIntent = new Intent();
                returnIntent.putExtra(Constant.PROJECTS_LIST, (Serializable) selected);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
                //Log.d("MainActivity", logString);
                //Toast.makeText(this, logString, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void findViewsById() {
        listView = (ListView) findViewById(R.id.list);
        button = (Button) findViewById(R.id.button);
        empty_frame_layout =findViewById(R.id.empty_frame_layout);
    }

    private ArrayList<Project> getSelectedItems() {
        //int count =listView.getCount();
        ArrayList<Project> result = new ArrayList<>();
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();

        for (int i = 0; i < listView.getCheckedItemCount(); ++i) {
            if (checkedItems.valueAt(i)) {
              int index=  checkedItems.keyAt(i);
               Project p= (Project) listView.getItemAtPosition(checkedItems.keyAt(i));
                result.add(p);
            }
        }

        return result;
    }
}
