package com.example.mirandasaari.librarysystem;
/*
Abstract:java file if no books are available for the user to place on hold
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NoBook  extends Activity implements View.OnClickListener{
    //widget variables

    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_books);


        //reference widgets
        confirmButton = (Button) findViewById(R.id.confirmButton);


        // set the listeners
        confirmButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.confirmButton) {
            Intent intent = new Intent(NoBook.this, MainMenu.class);
            startActivity(intent);
        }

    }
}
