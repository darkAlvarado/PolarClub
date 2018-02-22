package com.club.jalvara2.polarclub2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class AjouterActivity extends AppCompatActivity {
    FirebaseDatabase database;
    private TextView userNom;
    private TextView userPrenom;
    private TextView userPseudo;
    private TextView userAge;
    private TextView userMail;
    private Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        final Button btnAjouter = (Button) findViewById(R.id.btn_ajouter_user);
        database = FirebaseDatabase.getInstance();
        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPseudo = (TextView) findViewById(R.id.userPseudo);
                userNom = (TextView) findViewById(R.id.userNom);
                userPrenom = (TextView) findViewById(R.id.userPrenom);
                userAge = (TextView) findViewById(R.id.userAge);
                userMail = (TextView) findViewById(R.id.userMail);

                final DatabaseReference myRef = database.getReference("users");
                String pseudo = String.valueOf(userPseudo.getText().toString());

                myRef.child(pseudo);

                myRef.child(pseudo).child("age").setValue(Integer.parseInt(userAge.getText().toString()));
                myRef.child(pseudo).child("nom").setValue(String.valueOf(userNom.getText().toString()));
                myRef.child(pseudo).child("prenom").setValue(String.valueOf(userPrenom.getText().toString()));
                myRef.child(pseudo).child("email").setValue(String.valueOf(userMail.getText().toString()));
                AlertDialog msg = new AlertDialog.Builder(AjouterActivity.this).create();
                msg.setTitle("Info");
                msg.setMessage("L'utilisateur a été crée!");
                msg.setIcon(R.drawable.ic_run);
                msg.setButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.finish();
                    }
                });
                msg.show();
            }
        });
    }
}
