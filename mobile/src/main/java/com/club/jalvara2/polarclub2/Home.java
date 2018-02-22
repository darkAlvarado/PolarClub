package com.club.jalvara2.polarclub2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class Home extends AppCompatActivity implements View.OnClickListener{

    private CardView card1, card2, card3, card4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_dash);

        card1 = (CardView) findViewById(R.id.card1);
        card2 = (CardView) findViewById(R.id.card2);
        card3 = (CardView) findViewById(R.id.card3);
        card4 = (CardView) findViewById(R.id.card4);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.card1 : i = new Intent(this, AjouterActivity.class);
                startActivity(i);
            break;
            case R.id.card2 : i = new Intent(this, MainActivity.class);
                startActivity(i);
            break;
            case R.id.card3 : i = new Intent(this, SessionListActivity.class);
                startActivity(i);
            break;
            case R.id.card4 : i = new Intent(this, MainActivity.class); break;
        }
    }
}
