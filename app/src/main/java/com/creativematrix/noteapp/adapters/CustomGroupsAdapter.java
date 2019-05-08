package com.creativematrix.noteapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.data.groups.LstGroup;
import com.creativematrix.noteapp.data.project.Project;

import java.util.ArrayList;

public class CustomGroupsAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<LstGroup> lstGroups;
    public CustomGroupsAdapter(Context context, ArrayList<LstGroup> items ) {
        this .lstGroups = items;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lstGroups.size();
    }

    @Override
    public Object getItem(int i) {
         return lstGroups.get(i);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        final String username = lstGroups.get(i).getGroupName();
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
