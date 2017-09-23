package com.example.mirandasaari.librarysystem;
/*
Abstract:Java file if the librarian does not input correct book information
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddBookError extends Activity implements View.OnClickListener {


    //widget variables

    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book_error);


        //reference widgets
        confirmButton = (Button) findViewById(R.id.confirmButton);


        // set the listeners
        confirmButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.confirmButton) {
            Intent intent = new Intent(AddBookError.this, TransactionLogs.class);
            startActivity(intent);
        }

    }
}
