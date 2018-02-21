package com.club.jalvara2.polarclub2.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.club.jalvara2.polarclub2.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class SesionFragment extends Fragment {

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

//        DatabaseReference rooRef = FirebaseDatabase.getInstance().getReference();
//
//        DatabaseReference sesions = rooRef.child("sessions");
//
//        ValueEventListener eventLis = new ValueEventListener(){
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class);
//                System.out.println("----------------------------------------------------");
//                System.out.println("LOS VALORES ENCONTRADOS SON: " + value);
//                System.out.println("----------------------------------------------------");
//                Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("----------------------------------------------------");
//                System.out.println("Error: " + databaseError.getMessage());
//                System.out.println("----------------------------------------------------");
//            }
//        };
//        sesions.addListenerForSingleValueEvent(eventLis);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("sessions");

        //myRef.setValue("Hello, Atila");

        // Read from the database
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String val = dataSnapshot.getValue(String.class);
                System.out.println("EL VALOR ES: " + val);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

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
