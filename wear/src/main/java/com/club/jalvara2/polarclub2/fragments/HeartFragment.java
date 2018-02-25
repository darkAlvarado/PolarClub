package com.club.jalvara2.polarclub2.fragments;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.club.jalvara2.polarclub2.R;
import com.club.jalvara2.polarclub2.services.HeartEmulatorService;
import com.club.jalvara2.polarclub2.utils.HeartView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;
import static com.club.jalvara2.polarclub2.services.HeartService.HEARTBEAT_COUNT_MESSAGE;
import static com.club.jalvara2.polarclub2.services.HeartService.HEARTBEAT_COUNT_VALUE;

public class HeartFragment extends Fragment {

    private TextView mTextView;
    private boolean bodySensorsPermission = false;
    private static final int PERMISSION_REQUEST_BODY_SENSORS = 1;
    private HeartView heartbeat;
    private Intent heartViewItem;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private BroadcastReceiver br;

    private TextView labelMoyenne;
    private int moyenne = 80;
    private Button btnPlus;
    private Button btnMoins;

    private String idV;
    private String pseudo;

    public HeartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.layout_heart, container, false);

        if(!bodySensorsPermission){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.BODY_SENSORS}, PERMISSION_REQUEST_BODY_SENSORS);
            bodySensorsPermission = true;
        }
        heartViewItem = new Intent(getActivity(), HeartEmulatorService.class);
        mTextView = (TextView) rootView.findViewById(R.id.tvheartrate);
        heartbeat = (HeartView)rootView.findViewById(R.id.heartbeat);
        labelMoyenne = (TextView) rootView.findViewById(R.id.labelMoyenne);
        btnPlus = (Button) rootView.findViewById(R.id.btnPlus);
        btnMoins = (Button) rootView.findViewById(R.id.btnMoins);

        idV = String.valueOf(getArguments() != null ? getArguments().getInt("id") : 1);
        pseudo = getArguments() != null ? getArguments().getString("pseudo") : "default";

        btnPlus.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moyenne += 5;
                labelMoyenne.setText(String.valueOf(moyenne));
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");

                // Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d(TAG, "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }
        });

        btnMoins.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moyenne -= 5;
                labelMoyenne.setText(String.valueOf(moyenne));
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");
                // Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d(TAG, "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }


        });
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int val = intent.getExtras().getInt(HEARTBEAT_COUNT_VALUE, 10);
                mTextView.setText(String.valueOf(val));

                long x = val + 5;
                long c = val - 6;
                long d = val + 12;
                int e = val + 33;

                DatabaseReference m = database.getReference("id");

               // m.child(idV);

                m.child(idV).child(pseudo).child("frequence").setValue(x);

                DatabaseReference myRef8 = database.getReference("id/0/Bere/frequence");
                DatabaseReference myRef9 = database.getReference("id/0/Otro/frequence");
                DatabaseReference myRef10 = database.getReference("id/0/titin2/frequence");

                myRef8.setValue(c);
                myRef9.setValue(d);
                myRef10.setValue(e);

            }
        };

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().startService(heartViewItem);
        getActivity().registerReceiver(br, new IntentFilter(HEARTBEAT_COUNT_MESSAGE));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().stopService(heartViewItem);
        getActivity().unregisterReceiver(br);
    }
}
