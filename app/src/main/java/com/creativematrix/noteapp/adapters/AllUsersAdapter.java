package com.creativematrix.noteapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.data.groups.LstGroup;
import com.creativematrix.noteapp.data.user.LstUsers;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Ahmed.Khames on 4/16/2018.
 */

public class AllUsersAdapter extends RecyclerView.Adapter<AllUsersAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LstUsers> lstUsers;
    private AllUsersAdapter.OnItemClickListener clickListener;
    private static int sSelected = -1;

    public AllUsersAdapter(final Context context, final ArrayList<LstUsers> items, AllUsersAdapter.OnItemClickListener listener) {
        this.context = context;
        this.lstUsers = items;
        this.clickListener=listener;

    }
    public interface MyAdapterListener {

        void addCity(View v, int position);

    }
    public void setOnItemClickListener(final AllUsersAdapter.OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public AllUsersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_user_list, parent, false);
        return new AllUsersAdapter.ViewHolder(view);
    }


    public interface OnItemClickListener {
         void onItemClick(View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView user_name,user_group;
        //RadioButton checker;
        CardView cardlist_item;


        ViewHolder(View itemView) {
            super(itemView);
            cardlist_item = itemView.findViewById(R.id.cardlist_item);
            user_name = itemView.findViewById(R.id.user_name);
            user_group= itemView.findViewById(R.id.user_group);
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
    public void onBindViewHolder(final AllUsersAdapter.ViewHolder holder, int position) {
        LstUsers lstUser=lstUsers.get(position);
        holder.user_name.setText(lstUser.getUserName());
        holder.user_group.setText(lstUser.getGroupName());

    }

    @Override
    public int getItemCount() {
        return lstUsers.size();
    }
}
