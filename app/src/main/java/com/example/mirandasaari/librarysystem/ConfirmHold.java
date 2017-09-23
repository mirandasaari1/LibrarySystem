package com.example.mirandasaari.librarysystem;

/*
Abstract:Java file for user to confirm they want to hold the selected book
 */
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfirmHold extends Activity implements View.OnClickListener {

    //widget variables

    private Button yesButton;
    private Button noButton;
    private TextView usernameTextView;
    private TextView pickupTimeTextView;
    private TextView pickupDateTextView;
    private TextView returnTimeTextView;
    private TextView returnDateTextView;
    private TextView idTextView;
    private TextView chargeTextView;
    private TextView titleTextView;

    private CharSequence text = "";
    private int duration = Toast.LENGTH_SHORT;
    private int resID=1;
    private double chargeTotal=0.00;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_hold);

        usernameTextView = (TextView)findViewById(R.id.usernameTextView);
        pickupTimeTextView = (TextView)findViewById(R.id.pickupTimeTextView);
        pickupDateTextView = (TextView)findViewById(R.id.pickupDateTextView);
        returnTimeTextView = (TextView)findViewById(R.id.returnTimeTextView);
        returnDateTextView = (TextView)findViewById(R.id.returnDateTextView);
        chargeTextView = (TextView)findViewById(R.id.chargeTextView);
        titleTextView = (TextView)findViewById(R.id.titleTextView);
        idTextView = (TextView)findViewById(R.id.idTextView);


        //reference widgets
        yesButton = (Button) findViewById(R.id.yesButton);
        noButton = (Button) findViewById(R.id.noButton);


        // set the listeners
        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);

        resID++;
        Bundle extras = getIntent().getExtras();
        Log.d("enterif", "eneterif");
        String pickupDate=extras.getString("pickUpDate");
        String pickupTime=extras.getString("pickUpTime");
        String returnTime=extras.getString("returnTime");
        String returnDate=extras.getString("returnDate");
        String selectedBook=extras.getString("bookTitle");
        String username=extras.getString("username");

        usernameTextView.setText("Username:" + username);
        pickupDateTextView.setText("Pickup Date: " + pickupDate);
        pickupTimeTextView.setText("Pickup Time: " + pickupTime);
        returnDateTextView.setText("Return Date: " + returnDate);
        returnTimeTextView.setText("Return Time: " + returnTime);
        chargeTextView.setText("Total Amount: $" + chargeTotal);
        idTextView.setText("Reservation Number: " + resID);
        titleTextView.setText("Book: " + selectedBook);
    }

    public void displayInformation(){
        DatabaseHandler db = new DatabaseHandler(this);
        //Intent intent = new Intent(Login.this, ConfirmHold.class);
        //intent.putExtras(bundle);
        //startActivity(intent);

    }

    public void calculateCharge() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        DatabaseHandler db = new DatabaseHandler(this);
        double diff=0;
        Bundle extras = getIntent().getExtras();
        String pickupDate=extras.getString("pickUpDate");
        String returnDate=extras.getString("returnDate");
        String selectedBook=extras.getString("bookTitle");


        Date pDate = sdf.parse(pickupDate);
        Date rDate= sdf.parse(returnDate);
        diff = Math.round(rDate.getTime() - pDate.getTime());
        diff=(int) diff / (1000*60*60*24);

        for (Books temp : db.getAllBooks()) {
            if(temp.getTitle().equals(selectedBook)){
                chargeTotal=diff*Double.parseDouble(temp.getHourlyFee());
                Log.d("fee: ", String.valueOf(chargeTotal));
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.yesButton){
            Bundle extras = getIntent().getExtras();
            Log.d("enterif", "eneterif");
            String pickupDate=extras.getString("pickUpDate");
            String pickupTime=extras.getString("pickUpTime");
            String returnTime=extras.getString("returnTime");
            String returnDate=extras.getString("returnDate");
            String selectedBook=extras.getString("bookTitle");
            String username=extras.getString("username");
            DatabaseHandler db = new DatabaseHandler(this);
            Log.d("pickuptime ch", pickupTime);
            db.addTransaction(new Transactions(username, "Place Hold", selectedBook, pickupDate, pickupTime, returnDate, returnTime,String.valueOf(chargeTotal)));

            for (Books temp : db.getAllBooks()) {
                if(temp.getTitle().equals(selectedBook)){
                    temp.setOnHold("Unavailable");
                }
            }
            //delete book so unavailable
            text = "Book placed on hold";
            duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            Intent intent = new Intent(ConfirmHold.this, MainMenu.class);
            startActivity(intent);
        }
        else if(view.getId()==R.id.noButton){
            text = "Book not placed on hold";
            duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            Intent intent = new Intent(ConfirmHold.this, MainMenu.class);
            startActivity(intent);
        }

    }
}
