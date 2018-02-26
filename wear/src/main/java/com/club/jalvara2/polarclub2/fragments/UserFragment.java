package com.club.jalvara2.polarclub2.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.club.jalvara2.common.User;
import com.club.jalvara2.polarclub2.R;
import com.club.jalvara2.polarclub2.utils.UserAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class UserFragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private ArrayList<User> users;
    RecyclerView recyclerUsers;
    DatabaseReference myRef;
    private UserAdapter adapter;
    private String idV;
    public UserFragment() {
        // Required empty public constructor
    }


    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
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
        View root = inflater.inflate(R.layout.fragment_user, container, false);

        users = new ArrayList<User>();
        recyclerUsers = (RecyclerView) root.findViewById(R.id.recycler_users);
        recyclerUsers.setLayoutManager(new LinearLayoutManager(getContext()));

        idV = String.valueOf(getArguments() != null ? getArguments().getInt("id") : 1);

        adapter = new UserAdapter(users);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putInt("id", Integer.parseInt(idV));

                args.putString("pseudo", users.get(recyclerUsers.getChildAdapterPosition(view)).getPseudo());
                HeartFragment hf = new HeartFragment();
                FragmentManager fm = getFragmentManager();
                hf.setArguments(args);
                fm.beginTransaction().replace(R.id.content_frame, hf).commit();
            }
        });

        myRef = database.getReference("users");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                users.add(new User(dataSnapshot.getKey()));
                recyclerUsers.setAdapter(adapter);
                System.out.println("Tamaño de la lista " + users.size());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                for (User se: users){
                    se.pseudo = dataSnapshot.getKey();
                    recyclerUsers.setAdapter(adapter);
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                for (User se: users){
                    users.remove(se);
                    recyclerUsers.setAdapter(adapter);
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
