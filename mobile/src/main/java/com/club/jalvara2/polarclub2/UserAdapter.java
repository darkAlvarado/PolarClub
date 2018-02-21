package com.club.jalvara2.polarclub2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jalvara2 on 21/02/18.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolderUsers> {

    ArrayList<User> listUser;

    public UserAdapter(ArrayList<User> listUser) {
        this.listUser = listUser;
    }

    @Override
    public ViewHolderUsers onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.sportifs;
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,null,false);
        return new ViewHolderUsers(v);
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolderUsers holder, int position) {
        holder.pseudo.setText(listUser.get(position).getPseudo());
        holder.frequence.setText(Long.toString(listUser.get(position).getFrequence()));
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }


    public class ViewHolderUsers extends RecyclerView.ViewHolder {
        TextView pseudo, frequence;

        public ViewHolderUsers(View itemView) {
            super(itemView);
            pseudo = (TextView) itemView.findViewById(R.id.userPseudo);
            frequence = (TextView) itemView.findViewById(R.id.userFreq);
        }
    }
}
