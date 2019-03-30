package com.creativematrix.noteapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.data.groups.Userslst;
import com.creativematrix.noteapp.data.task.Task;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Ahmed.Khames on 4/16/2018.
 */

public class TasksInGroupAdapter extends RecyclerView.Adapter<TasksInGroupAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Userslst> userslsts=new ArrayList<>();
    private TasksInGroupAdapter.OnItemClickListener clickListener;
    private static int sSelected = -1;

    public TasksInGroupAdapter(final Context context, final ArrayList<Userslst> userslsts, TasksInGroupAdapter.OnItemClickListener listener) {
        this.context = context;
        this.userslsts = userslsts;
        this.clickListener=listener;

    }
    public interface MyAdapterListener {

        void addCity(View v, int position);



    }
    public void setOnItemClickListener(final TasksInGroupAdapter.OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public TasksInGroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_in_group_list, parent, false);
        return new TasksInGroupAdapter.ViewHolder(view);
    }


    public interface OnItemClickListener {
         void onItemClick(View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView member_name,task_count;

        //RadioButton checker;
        CardView cardlist_item;


        ViewHolder(View itemView) {
            super(itemView);
            cardlist_item = itemView.findViewById(R.id.cardlist_item);
            member_name = itemView.findViewById(R.id.member_name);
            task_count= itemView.findViewById(R.id.task_count);

           cardlist_item.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {

            sSelected = getAdapterPosition();
            clickListener.onItemClick(v, getPosition());

        }
    }

    @Override
    public void onBindViewHolder(final TasksInGroupAdapter.ViewHolder holder, int position) {

        Userslst userslst=userslsts.get(position);
        holder.member_name.setText(userslst.getUserName());
        holder.task_count.setText(String.valueOf(userslst.getTasknumber()));


   //     holder.task_status.setText(String.valueOf(task.getTaskStatus()));
    }

    @Override
    public int getItemCount() {
        return userslsts.size();
    }
}
