package com.goryn.randomapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goryn.randomapplication.Entity.User;
import com.goryn.randomapplication.R;

import java.util.List;

/**
 * Created by Odinn on 29.06.2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    private List<User> userList;

    public UserAdapter(List<User> userList){
        this.userList = userList;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int position) {
        final User user = userList.get(position);
        holder.tvTestText.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTestText;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTestText = (TextView) itemView.findViewById(R.id.tvText);
        }
    }
}
