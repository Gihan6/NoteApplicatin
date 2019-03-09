package com.creativematrix.noteapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.adapters.CustomSelectUserInCompanyAdapter;
import com.creativematrix.noteapp.data.task.DisplayTaskRequest;
import com.creativematrix.noteapp.data.task.LstUsersnCompnay;
import com.creativematrix.noteapp.data.task.TaskRepo;
import com.creativematrix.noteapp.util.PreferenceHelper;
import com.creativematrix.noteapp.util.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelectTaskOwnersActivity extends AppCompatActivity implements Button.OnClickListener {
    private ListView listView;
    private Button button;
    private ArrayList<LstUsersnCompnay> lstUsersnCompnays=new ArrayList<>();
    CustomSelectUserInCompanyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_task_owners);
        findViewsById();
        CustomSelectUserInCompanyAdapter adapter = new CustomSelectUserInCompanyAdapter(this, lstUsersnCompnays);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        new TaskRepo(SelectTaskOwnersActivity.this).getUsersInCompanyResponseLiveData(new DisplayTaskRequest(Long.valueOf(PreferenceHelper.getPrefernceHelperInstace().getCompanyid(SelectTaskOwnersActivity.this)), Utils.getLang()))
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)){
                            lstUsersnCompnays.clear();
                            lstUsersnCompnays.addAll(GroupRes.getLstUsersnCompnay());
                            adapter.notifyDataSetChanged();
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
                List<LstUsersnCompnay> selected = getSelectedItems();
                String logString = "Selected items: " + TextUtils.join(", ", selected);
                Intent returnIntent = new Intent();
                returnIntent.putExtra(Constant.USERS_LIST, (Serializable) selected);
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

    }

    private List<LstUsersnCompnay> getSelectedItems() {
        List<LstUsersnCompnay> result = new ArrayList<>();
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();

        for (int i = 0; i < listView.getCount(); ++i) {
            if (checkedItems.valueAt(i)) {
                result.add((LstUsersnCompnay) listView.getItemAtPosition(checkedItems.keyAt(i)));
            }
        }

        return result;
    }
}
