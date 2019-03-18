package com.creativematrix.noteapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.adapters.CustomTaskStatusAdapter;
import com.creativematrix.noteapp.data.task.TaskStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelectTaskStatusActivity extends AppCompatActivity implements Button.OnClickListener {
    private ListView listView;
    private Button button;
    private ArrayList<TaskStatus> taskStatuses=new ArrayList<>();
    CustomTaskStatusAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_task_status);
        findViewsById();
        getAllTaskStatus();

        CustomTaskStatusAdapter adapter = new CustomTaskStatusAdapter(this, taskStatuses);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);
        button.setOnClickListener(this);
    }

    private void getAllTaskStatus() {
        taskStatuses.add(new TaskStatus(getString(R.string.Urgent),Constant.TAST_URGENT));
        taskStatuses.add(new TaskStatus(getString(R.string.Critical),Constant.TASK_CRITCAL));
        taskStatuses.add(new TaskStatus(getString(R.string.Usual),Constant.TASK_USUAL));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                List<TaskStatus> selected = getSelectedItems();
                Intent returnIntent = new Intent();
                returnIntent.putExtra(Constant.TASK_STATUS_LIST, (Serializable) selected);
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

    private List<TaskStatus> getSelectedItems() {
        List<TaskStatus> result = new ArrayList<>();
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();

        for (int i = 0; i < listView.getCount(); ++i) {
            if (checkedItems.valueAt(i)) {
                result.add((TaskStatus) listView.getItemAtPosition(checkedItems.keyAt(i)));
            }
        }

        return result;
    }
}
