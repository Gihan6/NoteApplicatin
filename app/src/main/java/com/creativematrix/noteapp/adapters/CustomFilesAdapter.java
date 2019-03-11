package com.creativematrix.noteapp.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.data.task.FilesBinary;
import com.creativematrix.noteapp.data.task.LstUsersnCompnay;

import java.util.ArrayList;
import java.util.List;


public class CustomFilesAdapter extends RecyclerView.Adapter<CustomFilesAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<FilesBinary> filesBinaryArrayList;
    public CustomFilesAdapter.MyAdapterListener onClickListener;


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView username,groupname;
        ImageView iv_delete;



        ViewHolder(View itemView) {
            super(itemView);

            username = (TextView) itemView.findViewById(R.id.user_name);
            groupname = (TextView) itemView.findViewById(R.id.group_name);
            iv_delete = (ImageView) itemView.findViewById(R.id.iv_delete);

            //itemView.setOnClickListener(this);

        }



    }

    @Override
    public void onBindViewHolder(final CustomFilesAdapter.ViewHolder holder, int position) {


        holder.username.setText(filesBinaryArrayList.get(position).getFileName());
        holder.groupname.setText(filesBinaryArrayList.get(position).getFileExt());



        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.deleteFile(v, holder.getAdapterPosition());
            }
        });


        //set photo by Picasso lib

    }
    public CustomFilesAdapter(Context context, List<FilesBinary> filesBinaryArrayList, CustomFilesAdapter.MyAdapterListener onClickListener) {
        this.filesBinaryArrayList = filesBinaryArrayList;
        inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
    }

    @Override
    public CustomFilesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_files, parent, false);
        return new CustomFilesAdapter.ViewHolder(view);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return filesBinaryArrayList.size();
    }


    public interface MyAdapterListener {

        void deleteFile(View v, int position);


    }
}
