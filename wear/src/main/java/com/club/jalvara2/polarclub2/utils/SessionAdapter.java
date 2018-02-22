package com.club.jalvara2.polarclub2.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.club.jalvara2.polarclub2.R;

import java.util.ArrayList;

/**
 * Created by jalvara2 on 22/02/18.
 */

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.ViewHolderUsers>
            implements View.OnClickListener{
    ArrayList<Session> sessions;
    private View.OnClickListener listener;

    public SessionAdapter(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public ViewHolderUsers onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.sessions;
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,null,false);

        v.setOnClickListener(this);
        return new ViewHolderUsers(v);
    }

    @Override
    public void onBindViewHolder(SessionAdapter.ViewHolderUsers holder, int position) {
        holder.sessionNom.setText("Nom de la session: " + sessions.get(position).getName());
        String status = "";
        if (sessions.get(position).isActive()){
            status = "Estatus de la session: activée";
        }else{
            status = "Estatus de la session: desativée";
        }
        holder.sessionStatus.setText(status);
    }

    @Override
    public int getItemCount() {
        return sessions.size();
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
        TextView sessionNom;
        TextView sessionStatus;
        public ViewHolderUsers(View itemView) {
            super(itemView);
            sessionStatus = (TextView) itemView.findViewById(R.id.sessionStatus);
            sessionNom = (TextView) itemView.findViewById(R.id.sessionNom);
        }
    }
}
