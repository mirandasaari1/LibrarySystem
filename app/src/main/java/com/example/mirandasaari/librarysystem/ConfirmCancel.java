package com.example.mirandasaari.librarysystem;
/*
Abstract:Java file for user to confirm they want to cancel their selected hold
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.view.View.OnClickListener;

public class ConfirmCancel extends Activity implements OnClickListener {

    //widget variables

    private Button yesButton;
    private Button noButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_cancel);


        //reference widgets
        yesButton = (Button) findViewById(R.id.yesButton);
        noButton = (Button) findViewById(R.id.noButton);


        // set the listeners
        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.yesButton) {
            Bundle extras = getIntent().getExtras();
            Log.d("enterif", "eneterif");
            String selectedBook = extras.getString("bookTitle");
            String username = extras.getString("username");
            Log.d("confirmcancel", username);
            Log.d("confirmcancel", selectedBook);
            Intent intent = new Intent(ConfirmCancel.this, MainMenu.class);
            startActivity(intent);
            DatabaseHandler db = new DatabaseHandler(this);
            for (Transactions temp : db.getAllTransactions()) {
                if(temp.getTitle().equals(null)|| temp.getStudUsername().equals(null))
                    Log.d("null values", null);
//                Log.d("temp", temp.getTitle().toString());
//                Log.d("temp", temp.getStudUsername().toString());
                else if (temp.getTitle().equals(selectedBook) && temp.getStudUsername().equals(username)) {
                    Log.d("Entere inner", "enter");
                    String pickupTime2 = temp.getPickupTime();
                    String pickupDate2 = temp.getPickupDate();
                    String returnTime2 = temp.getReturnTime();
                    String returnDate2 = temp.getReturnDate();
                    db.addTransaction(new Transactions(username, "Cancel Hold", selectedBook, pickupDate2, pickupTime2, returnDate2, returnTime2));
                }
            }
                //make book available again
                for (Books btemp : db.getAllBooks()) {
                    if (btemp.getTitle().equals(selectedBook)) {
                        btemp.setOnHold("unavailable");
                    }
                }

        }
        if (v.getId() == R.id.noButton) {
                Intent intent = new Intent(ConfirmCancel.this, CancelHold.class);
                startActivity(intent);
        }
    }
}
