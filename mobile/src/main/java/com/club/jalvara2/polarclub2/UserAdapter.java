package com.club.jalvara2.polarclub2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.club.jalvara2.common.User;

import java.util.ArrayList;

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
        holder.frequence.setText(listUser.get(position).getFrequence());
        holder.media.setText(String.valueOf(listUser.get(position).getMedia()));
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }


    public class ViewHolderUsers extends RecyclerView.ViewHolder {
        TextView pseudo, frequence, media;

        public ViewHolderUsers(View itemView) {
            super(itemView);
            pseudo = (TextView) itemView.findViewById(R.id.userPseudo);
            frequence = (TextView) itemView.findViewById(R.id.userFreq);
            media = (TextView) itemView.findViewById(R.id.media);
        }
    }
}
