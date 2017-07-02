package com.goryn.randomapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexzh.circleimageview.CircleImageView;
import com.goryn.randomapplication.models.User;
import com.goryn.randomapplication.R;
import com.goryn.randomapplication.models.UserInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Odinn on 29.06.2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    private List<UserInfo> userList;

    public UserAdapter(List<UserInfo> userList){
        this.userList = userList;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int position) {
        final UserInfo user = userList.get(position);
        try {
            String firstName = user.getResults().get(0).getName().getFirst();
            String lastName = user.getResults().get(0).getName().getLast();
            String imageURL = user.getResults().get(0).getPicture().getMedium();
            holder.tvTestText.setText(firstName +" "+  lastName);
            Picasso.with(holder.circleImageView.getContext())
                    .load(imageURL)
                    .resize(50,50)
                    .into(holder.circleImageView);
        } catch (NullPointerException ignored){

        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTestText;
        CircleImageView circleImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTestText = (TextView) itemView.findViewById(R.id.tvName);
            circleImageView = (CircleImageView) itemView.findViewById(R.id.ivThumbnail);
        }
    }
}
