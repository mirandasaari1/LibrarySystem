package com.example.mirandasaari.librarysystem;
/*
Abstract:java file which displays error if user tries to pick a more than seven day rental
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class sevenError extends Activity implements View.OnClickListener {

    //widget variables

    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seven_error);


        //reference widgets
        confirmButton = (Button) findViewById(R.id.confirmButton);


        // set the listeners
        confirmButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.confirmButton) {
            Intent intent = new Intent(sevenError.this, PlaceHold.class);
            startActivity(intent);
        }

    }
}
