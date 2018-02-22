package com.club.jalvara2.polarclub2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SessionListActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private ArrayList<Session> sessions;
    RecyclerView recyclerSessions;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_list);

        sessions = new ArrayList<>();
        recyclerSessions = (RecyclerView) findViewById(R.id.recycler_sessions);
        recyclerSessions.setLayoutManager(new LinearLayoutManager(this));

        final SessionAdapter adapter = new SessionAdapter(sessions);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Selección: "+sessions.get
                                (recyclerSessions.getChildAdapterPosition(view))
                                .getId(),Toast.LENGTH_SHORT).show();
                System.out.println("Selección: "+sessions.get
                        (recyclerSessions.getChildAdapterPosition(view))
                        .getId());
                Intent intent = new Intent(SessionListActivity.this,DisplaySessionActivity.class);
                long myId = Long.valueOf(sessions.get(recyclerSessions.getChildAdapterPosition(view)).getId());
                System.out.println("Este es nuestro id " + myId);
                intent.putExtra("session", myId);
                startActivity(intent);
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

    }
}
