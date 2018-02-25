package com.club.jalvara2.polarclub2;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import java.util.Locale;

public class DisplaySessionActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 3600000;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    private ArrayList<User> users;
    RecyclerView recyclerUsers;
   // ArrayAdapter<User> adapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    private TextView mTextViewCountDown;

    private int idSession;
    private String cveSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_session);

        Intent intent = getIntent();
        idSession = intent.getIntExtra("session",-1);
        cveSession = intent.getStringExtra("cveS");


        final TextView textViewId = findViewById(R.id.textViewId);
        textViewId.setText("ID de la session: "+String.valueOf(idSession));
        mTextViewCountDown = findViewById(R.id.mTextViewCountDown);

        users = new ArrayList<>();
        recyclerUsers = (RecyclerView) findViewById(R.id.recycler_n);
        recyclerUsers.setLayoutManager(new GridLayoutManager(this, 3));

        final UserAdapter adapter = new UserAdapter(users);

        startTimer();

        myRef = database.getReference("id").child(String.valueOf(idSession));

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
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

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void updateCountDownText() {
        myRef = database.getReference("sessions").child(cveSession);
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        myRef.child("time").setValue(timeLeftFormatted);
        mTextViewCountDown.setText(timeLeftFormatted);
    }
}