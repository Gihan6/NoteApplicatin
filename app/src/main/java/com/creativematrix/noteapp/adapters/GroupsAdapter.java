package com.creativematrix.noteapp.adapters;

import android.content.Context;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.data.groups.LstGroup;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

/**
 * Created by Ahmed.Khames on 4/16/2018.
 */

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LstGroup> cityArrayList;
    private GroupsAdapter.OnItemClickListener clickListener;
    private static int sSelected = -1;

    public GroupsAdapter(final Context context, final ArrayList<LstGroup> items, GroupsAdapter.OnItemClickListener listener) {
        this.context = context;
        this.cityArrayList = items;
        this.clickListener=listener;

    }
    public interface MyAdapterListener {

        void addCity(View v, int position);



    }
    public void setOnItemClickListener(final GroupsAdapter.OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public GroupsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_list, parent, false);
        return new GroupsAdapter.ViewHolder(view);
    }


    public interface OnItemClickListener {
         void onItemClick(View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView group_name,group_desc;
        //RadioButton checker;
        CardView cardlist_item;
       ImageView group_logo;

        ViewHolder(View itemView) {
            super(itemView);

            cardlist_item = itemView.findViewById(R.id.cardlist_item);
            group_name = itemView.findViewById(R.id.group_name);
            group_desc= itemView.findViewById(R.id.group_desc);
            group_logo= itemView.findViewById(R.id.group_logo);
           // checker=itemView.findViewById(R.id.checker);
            cardlist_item.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {

            sSelected = getAdapterPosition();
            clickListener.onItemClick(v, getPosition());

        }
    }

    @Override
    public void onBindViewHolder(final GroupsAdapter.ViewHolder holder, int position) {

        LstGroup lstGroup=cityArrayList.get(position);

        holder.group_name.setText(lstGroup.getGroupName());
        holder.group_desc.setText(lstGroup.getGroupDescreption());
        Picasso.with(context).load(cityArrayList.get(position).getImg()).placeholder(R.mipmap.list).into(holder.group_logo);


    }

    @Override
    public int getItemCount() {
        return cityArrayList.size();
    }
}
