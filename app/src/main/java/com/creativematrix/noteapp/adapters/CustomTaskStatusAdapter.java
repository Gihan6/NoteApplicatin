package com.creativematrix.noteapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.data.project.Project;
import com.creativematrix.noteapp.data.task.TaskStatus;

import java.util.ArrayList;

public class CustomTaskStatusAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<TaskStatus> taskStatuses;
    public CustomTaskStatusAdapter(Context context, ArrayList<TaskStatus> items ) {
        this .taskStatuses = items;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return taskStatuses.size();
    }

    @Override
    public Object getItem(int i) {
         return taskStatuses.get(i);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        final String username = taskStatuses.get(i).getTaskStatusName();
       // final String groupname = projects.get(i).getGroupName();

        if (view == null) {
            view = inflater.inflate(R.layout.list_item_multiselect, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.username = (TextView) view.findViewById(R.id.user_name);
            viewHolder.groupname = (TextView) view.findViewById(R.id.group_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.username.setText(username);
        viewHolder.groupname.setVisibility(View.GONE);
       // viewHolder.username.setText(username);
        return view;
    }

    private class ViewHolder {
        TextView username,groupname;
    }
}
