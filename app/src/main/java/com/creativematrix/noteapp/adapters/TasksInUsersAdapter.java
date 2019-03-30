package com.creativematrix.noteapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.data.task.Task;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Ahmed.Khames on 4/16/2018.
 */

public class TasksInUsersAdapter extends RecyclerView.Adapter<TasksInUsersAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Task> tasks=new ArrayList<>();
    private TasksInUsersAdapter.OnItemClickListener clickListener;
    private static int sSelected = -1;

    public TasksInUsersAdapter(final Context context, final ArrayList<Task> items, TasksInUsersAdapter.OnItemClickListener listener) {
        this.context = context;
        this.tasks = items;
        this.clickListener=listener;

    }
    public interface MyAdapterListener {

        void addCity(View v, int position);



    }
    public void setOnItemClickListener(final TasksInUsersAdapter.OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public TasksInUsersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_in_user_list, parent, false);
        return new TasksInUsersAdapter.ViewHolder(view);
    }


    public interface OnItemClickListener {
         void onItemClick(View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView task_name,task_status,task_start_time,task_end_time;

        //RadioButton checker;
        CardView cardlist_item;


        ViewHolder(View itemView) {
            super(itemView);
            cardlist_item = itemView.findViewById(R.id.cardlist_item);
            task_name = itemView.findViewById(R.id.task_name);
            task_status= itemView.findViewById(R.id.task_status);
            task_start_time= itemView.findViewById(R.id.task_start_time);
            task_end_time= itemView.findViewById(R.id.task_end_time);
            cardlist_item.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {

            sSelected = getAdapterPosition();
            clickListener.onItemClick(v, getPosition());

        }
    }

    @Override
    public void onBindViewHolder(final TasksInUsersAdapter.ViewHolder holder, int position) {

        Task task=tasks.get(position);
        holder.task_name.setText(task.getTaskName());
        holder.task_start_time.setText(String.valueOf(task.getStartTime()));
        holder.task_end_time.setText(String.valueOf(task.getEndTime()));
        if((task.getTaskStatus())){
            holder.task_status.setText(context.getResources().getString(R.string.task_completed));
        }
        else {
            holder.task_status.setText(context.getResources().getString(R.string.task_under_processing));

        }

   //     holder.task_status.setText(String.valueOf(task.getTaskStatus()));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
