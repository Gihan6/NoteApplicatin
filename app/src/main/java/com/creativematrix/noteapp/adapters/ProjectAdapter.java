package com.creativematrix.noteapp.adapters;

import android.content.Context;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.data.project.Project;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project_list, parent, false);
        return new ProjectAdapter.ViewHolder(view);
    }


    public interface OnItemClickListener {
         void onItemClick(View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView project_name,project_status;

        //RadioButton checker;
        CardView cardlist_item;


        ViewHolder(View itemView) {
            super(itemView);
            cardlist_item = itemView.findViewById(R.id.cardlist_item);
            project_name = itemView.findViewById(R.id.project_name);
            project_status= itemView.findViewById(R.id.project_status);
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
        holder.project_name.setText(project.getProjectName());
        if(String.valueOf(project.getProjectStatus()).equals("0")){
            holder.project_status.setText(context.getResources().getString(R.string.task_under_processing));
        }
        else {
            holder.project_status.setText(context.getResources().getString(R.string.task_completed));

        }
       // holder.project_status.setText(String.valueOf(project.getProjectStatus()));
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }
}
