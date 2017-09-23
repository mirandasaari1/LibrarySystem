package com.example.mirandasaari.librarysystem;
/*
Abstract:java file if there is no holds for the user to cancel
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NoReservation extends Activity implements View.OnClickListener {
    //widget variables

    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_reservation);


        //reference widgets
        okButton = (Button) findViewById(R.id.okButton);


        // set the listeners
        okButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.okButton) {
            Intent intent = new Intent(NoReservation.this, MainMenu.class);
            startActivity(intent);
        }

    }
}
