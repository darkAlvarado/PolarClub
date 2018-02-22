package com.club.jalvara2.polarclub2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends Activity {
    FirebaseDatabase database;
    long id;
    boolean ajout=false;
    Activity currentActivity=this;
    private TextView nomSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();

        final Button button = findViewById(R.id.button_session);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                final DatabaseReference myRef = database.getReference("sessions");
                nomSession = (TextView) findViewById(R.id.nom_session);
                ChildEventListener cel=new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        if (!ajout){

                            long prevId=(long)dataSnapshot.child("id").getValue();
                            //System.out.println(prevId);
                            //System.out.println(prevIdInt);
                            id=prevId+1;
                            String key = myRef.push().getKey();
                            myRef.child(key).child("id").setValue(id);
                            myRef.child(key).child("name").setValue(String.valueOf(nomSession.getText()));
                            myRef.child(key).child("active").setValue(true);
                            myRef.child(key).child("time").setValue("60:00");

                            DatabaseReference newsession=database.getReference("id");
                            newsession.child(String.valueOf(id)).child("coach").child("frequence").setValue(0);
                            ajout=true;

                            Intent intent = new Intent(currentActivity,SessionListActivity.class);

                            startActivity(intent);
                        }

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
                        System.out.println(databaseError.getMessage());
                    }
                };
                Query lastentry= myRef.orderByKey().limitToLast(1);
                lastentry.addChildEventListener(cel);

                //String key = myRef.push().getKey();

                //myRef.child(key).child("id").setValue("0");

            }
        });
    }
}
