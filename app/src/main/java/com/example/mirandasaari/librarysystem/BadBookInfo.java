package com.example.mirandasaari.librarysystem;
/*
Abstract:DID NOT USE
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class BadBookInfo extends Activity implements View.OnClickListener {

    //widget variables

    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bad_book_info);


        //reference widgets
        confirmButton = (Button) findViewById(R.id.confirmButton);


        // set the listeners
        confirmButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.confirmButton) {
            Intent intent = new Intent(BadBookInfo.this, MainMenu.class);
            startActivity(intent);
        }

    }
}
