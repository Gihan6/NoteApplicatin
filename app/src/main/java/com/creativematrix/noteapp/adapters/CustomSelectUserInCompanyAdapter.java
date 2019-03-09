package com.creativematrix.noteapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.data.groups.LstGroup;
import com.creativematrix.noteapp.data.task.LstUsersnCompnay;

import java.util.ArrayList;

public class CustomSelectUserInCompanyAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<LstUsersnCompnay> lstUsersnCompnays;
    public CustomSelectUserInCompanyAdapter(Context context, ArrayList<LstUsersnCompnay> lstUsersnCompnays ) {
        this .lstUsersnCompnays = lstUsersnCompnays;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lstUsersnCompnays.size();
    }

    @Override
    public Object getItem(int i) {
         return lstUsersnCompnays.get(i);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        final String username = lstUsersnCompnays.get(i).getUsername();
        final String groupname = lstUsersnCompnays.get(i).getGroupName();

        if (view == null) {
            view = inflater.inflate(R.layout.list_item_multiselect, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.username = (TextView) view.findViewById(R.id.user_name);
            viewHolder.groupname = (TextView) view.findViewById(R.id.group_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.groupname.setText(groupname);
        viewHolder.username.setText(username);
        return view;
    }

    private class ViewHolder {
        TextView username,groupname;
    }
}
