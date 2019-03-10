package com.creativematrix.noteapp.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.data.project.Project;
import com.creativematrix.noteapp.data.task.Task;

import java.util.ArrayList;

/**
 * Created by Ahmed.Khames on 4/16/2018.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Project> projects=new ArrayList<>();
    private ProjectAdapter.OnItemClickListener clickListener;
    private static int sSelected = -1;

    public ProjectAdapter(final Context context, final ArrayList<Project> items, ProjectAdapter.OnItemClickListener listener) {
        this.context = context;
        this.projects = items;
        this.clickListener=listener;

    }
    public interface MyAdapterListener {

        void addCity(View v, int position);



    }
    public void setOnItemClickListener(final ProjectAdapter.OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_list, parent, false);
        return new ProjectAdapter.ViewHolder(view);
    }


    public interface OnItemClickListener {
         void onItemClick(View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView task_name,task_status;

        //RadioButton checker;
        CardView cardlist_item;


        ViewHolder(View itemView) {
            super(itemView);
            cardlist_item = itemView.findViewById(R.id.cardlist_item);
            task_name = itemView.findViewById(R.id.task_name);
            task_status= itemView.findViewById(R.id.task_status);
            cardlist_item.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {

            sSelected = getAdapterPosition();
            clickListener.onItemClick(v, getPosition());

        }
    }

    @Override
    public void onBindViewHolder(final ProjectAdapter.ViewHolder holder, int position) {

        Project project=projects.get(position);
        holder.task_name.setText(project.getProjectName());
        holder.task_status.setText(String.valueOf(project.getProjectStatus()));
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }
}
