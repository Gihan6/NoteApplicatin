package com.creativematrix.noteapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.data.task.Task;

import java.util.ArrayList;

/**
 * Created by Ahmed.Khames on 4/16/2018.
 */

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Task> tasks = new ArrayList<>();
    private TasksAdapter.OnItemClickListener clickListener;
    private static int sSelected = -1;

    public TasksAdapter(final Context context, final ArrayList<Task> items, TasksAdapter.OnItemClickListener listener) {
        this.context = context;
        this.tasks = items;
        this.clickListener = listener;

    }

    public interface MyAdapterListener {

        void addCity(View v, int position);


    }

    public void setOnItemClickListener(final TasksAdapter.OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public TasksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_list, parent, false);
        return new TasksAdapter.ViewHolder(view);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView task_name, task_status, pending_name;

        //RadioButton checker;
        CardView cardlist_item;


        ViewHolder(View itemView) {
            super(itemView);
            cardlist_item = itemView.findViewById(R.id.cardlist_item);
            task_name = itemView.findViewById(R.id.task_name);
            task_status = itemView.findViewById(R.id.task_status);
            pending_name = itemView.findViewById(R.id.pending_name);
            cardlist_item.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {

            sSelected = getAdapterPosition();
            clickListener.onItemClick(v, getPosition());

        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final TasksAdapter.ViewHolder holder, int position) {

        Task task = tasks.get(position);
        holder.task_name.setText(task.getTaskName());
        if ((task.getTaskStatus())) {
            holder.task_status.setText(context.getResources().getString(R.string.task_completed));
        } else {
            holder.task_status.setText(context.getResources().getString(R.string.task_under_processing));

        }


        if (task.getPending() != null)
            if (task.getPending()) {
                holder.cardlist_item.setCardBackgroundColor(R.color.colorPrimary);
                holder.pending_name.setVisibility(View.VISIBLE);
                holder.task_status.setText(context.getResources().getString(R.string.not_assigned));

            } else {
                holder.cardlist_item.setCardBackgroundColor(Color.WHITE);
                holder.pending_name.setVisibility(View.GONE);
            }
        //     holder.task_status.setText(String.valueOf(task.getTaskStatus()));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
