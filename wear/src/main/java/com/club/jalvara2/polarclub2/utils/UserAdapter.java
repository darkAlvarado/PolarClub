package com.club.jalvara2.polarclub2.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.club.jalvara2.common.User;
import com.club.jalvara2.polarclub2.R;

import java.util.ArrayList;

/**
 * Created by jalvara2 on 22/02/18.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolderUsers>
            implements View.OnClickListener{
    ArrayList<User> users;
    private View.OnClickListener listener;

    public UserAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public ViewHolderUsers onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.users;
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,null,false);

        v.setOnClickListener(this);
        return new ViewHolderUsers(v);
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolderUsers holder, int position) {
        holder.userPseudo.setText("-" + users.get(position).getPseudo());
    }

    @Override
    public int getItemCount() {
        System.out.println("Tamaño " + users.size());
        return users.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderUsers extends RecyclerView.ViewHolder {
        TextView userPseudo;
        public ViewHolderUsers(View itemView) {
            super(itemView);
            userPseudo = (TextView) itemView.findViewById(R.id.userPseudo);
        }
    }
}
