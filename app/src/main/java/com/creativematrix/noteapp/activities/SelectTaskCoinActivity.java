package com.creativematrix.noteapp.activities;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.creativematrix.noteapp.Constant;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.adapters.CustomCurrencyAdapter;
import com.creativematrix.noteapp.data.coins.CurrencyList;
import com.creativematrix.noteapp.data.coins.CurrencyRepo;
import com.creativematrix.noteapp.data.coins.DisplayCurrencyRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelectTaskCoinActivity extends AppCompatActivity implements Button.OnClickListener {
    private ListView listView;
    private Button button;
    private ArrayList<CurrencyList> currencyLists = new ArrayList<>();
    CustomCurrencyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_task_status);
        findViewsById();
        getAllTaskStatus();

         adapter = new CustomCurrencyAdapter(this, currencyLists);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);
        button.setOnClickListener(this);
    }

    private void getAllTaskStatus() {
        new CurrencyRepo(SelectTaskCoinActivity.this).displayCurrencyResponseLiveData(new DisplayCurrencyRequest(Constant.ALL_CURRENCY))
                .observe(this, GroupRes -> {
                    try {
                        if (GroupRes.getFlag().equals(Constant.RESPONSE_SUCCESS)){
                            currencyLists.clear();
                            currencyLists.addAll(GroupRes.getCurrencyList());
                            adapter.notifyDataSetChanged();
                        }
                    } catch (Exception ex) {

                    }
                });
       // currencyLists.add(new TaskStatus(getString(R.string.Urgent), Constant.TAST_URGENT));
        //currencyLists.add(new TaskStatus(getString(R.string.Critical),Constant.TASK_CRITCAL));
        //currencyLists.add(new TaskStatus(getString(R.string.Usual),Constant.TASK_USUAL));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                List<CurrencyList> selected = getSelectedItems();
                Intent returnIntent = new Intent();
                returnIntent.putExtra(Constant.TASK_CURRNECY_LIST, (Serializable) selected);
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

    private List<CurrencyList> getSelectedItems() {
        List<CurrencyList> result = new ArrayList<>();
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();

        for (int i = 0; i < listView.getCount(); ++i) {
            if (checkedItems.valueAt(i)) {
                result.add((CurrencyList) listView.getItemAtPosition(checkedItems.keyAt(i)));
            }
        }

        return result;
    }
}
