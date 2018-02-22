package com.club.jalvara2.polarclub2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DisplaySessionActivity extends AppCompatActivity {

    private ArrayList<User> users;
    RecyclerView recyclerUsers;
   // ArrayAdapter<User> adapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_session);

        Intent intent = getIntent();
        System.out.println("Imprimimos el valor " + intent.getLongExtra("session" ,-1));
        long idSession=  intent.getLongExtra("session",-1);


        final TextView textViewId = findViewById(R.id.textViewId);
        textViewId.setText("ID de la session: "+String.valueOf(idSession));

        users = new ArrayList<>();
        recyclerUsers = (RecyclerView) findViewById(R.id.recycler_n);
        recyclerUsers.setLayoutManager(new GridLayoutManager(this, 3));

        final UserAdapter adapter = new UserAdapter(users);


        //adapter=new ArrayAdapter<User>(this,android.R.layout.simple_list_item_1,users);

        String id=String.valueOf(idSession);
       // mListView = (ListView)findViewById(R.id.listview);
        //mListView.setAdapter(adapter);
        myRef = database.getReference("id").child(id);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                System.out.println(dataSnapshot.getKey());
                if (!dataSnapshot.getKey().equals("coach")){
                    users.add(new User(1, dataSnapshot.getKey(),dataSnapshot.child("frequence").getValue(Long.class)));
                    //adapter.notifyDataSetChanged();
                    recyclerUsers.setAdapter(adapter);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                for (User u:users){
                    if (dataSnapshot.getKey().equals(u.pseudo)){
                        System.out.println("ok");
                        u.frequence=dataSnapshot.child("frequence").getValue(Long.class);
                        //adapter.notifyDataSetChanged();
                        recyclerUsers.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                for (User u:users){
                    if (dataSnapshot.getKey().equals(u.pseudo)){
                        users.remove(u);
                       // adapter.notifyDataSetChanged();
                        recyclerUsers.setAdapter(adapter);
                    }
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
