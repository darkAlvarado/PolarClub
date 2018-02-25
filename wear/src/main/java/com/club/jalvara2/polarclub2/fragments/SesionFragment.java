package com.club.jalvara2.polarclub2.fragments;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.club.jalvara2.polarclub2.R;
import com.club.jalvara2.polarclub2.utils.Session;
import com.club.jalvara2.polarclub2.utils.SessionAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SesionFragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private ArrayList<Session> sessions;
    RecyclerView recyclerSessions;
    DatabaseReference myRef;

    public SesionFragment() {
        // Required empty public constructor
    }


    public static SesionFragment newInstance(String param1, String param2) {
        SesionFragment fragment = new SesionFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sesion, container, false);

        sessions = new ArrayList<>();
        recyclerSessions = (RecyclerView) root.findViewById(R.id.recycler_sessions);
        recyclerSessions.setLayoutManager(new LinearLayoutManager(getContext()));

        final SessionAdapter adapter = new SessionAdapter(sessions);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putInt("id", sessions.get(recyclerSessions.getChildAdapterPosition(view)).getId());
                args.putString("pseudo", "jalava");
                HeartFragment hf = new HeartFragment();
                FragmentManager fm = getFragmentManager();
                hf.setArguments(args);
                fm.beginTransaction().replace(R.id.content_frame, hf).commit();
            }
        });

        myRef = database.getReference("sessions");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                sessions.add(new Session(dataSnapshot.child("active").getValue(Boolean.class),
                        dataSnapshot.child("id").getValue(Integer.class),
                        dataSnapshot.child("name").getValue(String.class),
                        dataSnapshot.child("time").getValue(String.class)));
                recyclerSessions.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                for (Session se: sessions){
                    se.name = dataSnapshot.child("name").getValue(String.class);
                    se.active = dataSnapshot.child("active").getValue(Boolean.class);
                    se.id = dataSnapshot.child("id").getValue(Integer.class);
                    se.timeS = dataSnapshot.child("time").getValue(String.class);
                    recyclerSessions.setAdapter(adapter);
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                for (Session se: sessions){
                    sessions.remove(se);
                    recyclerSessions.setAdapter(adapter);
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return root;
    }

}
