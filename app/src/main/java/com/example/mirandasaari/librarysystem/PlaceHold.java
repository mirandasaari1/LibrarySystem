package com.example.mirandasaari.librarysystem;


/*
Abstract:java file which allows user to pick dates for placing the hold
 */
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TimePicker;
import android.widget.TextView;

public class PlaceHold extends Activity implements OnClickListener{


    private TextView pickupDateTextView;
    private TextView returnDateTextView;
    private TextView pickupTimeTextView;
    private TextView returnTimeTextView;
    private EditText pickupDateEditText;
    private EditText pickupTimeEditText;
    private EditText returnDateEditText;
    private EditText returnTimeEditText;
    private Button submitButton;
    private Context context;
    private CharSequence text = "";

    private String pickupDate="";
    private String pickupTime="";
    private String returnDate="";
    private String returnTime="";
    private int duration = Toast.LENGTH_SHORT;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_hold);
        context=getApplicationContext();

        pickupTimeEditText = (EditText) findViewById(R.id.pickupTimeEditText);
        returnTimeEditText = (EditText) findViewById(R.id.returnTimeEditText);
        pickupDateEditText = (EditText) findViewById(R.id.pickupDateEditText);
        returnDateEditText = (EditText) findViewById(R.id.returnDateEditText);
        pickupTimeTextView = (TextView) findViewById(R.id.pickupTimeTextView);
        returnTimeTextView = (TextView) findViewById(R.id.returnTimeTextView);
        pickupDateTextView = (TextView) findViewById(R.id.pickupDateTextView);
        returnDateTextView = (TextView) findViewById(R.id.returnDateTextView);
        submitButton = (Button) findViewById(R.id.submitButton);
    }

    public void sevenDayCheck(View v) throws ParseException {
        pickupTime = pickupTimeEditText.getText().toString();
        pickupDate = pickupDateEditText.getText().toString();
        returnTime = returnTimeEditText.getText().toString();
        returnDate = returnDateEditText.getText().toString();
        long diff = -1;
        long dayDif;
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");

        Date pDate = sdf.parse(pickupDate);
        //cal1.setTime(pDate);
        Date rDate= sdf.parse(returnDate);
        //cal2.setTime(rDate);

        //time is always 00:00:00 so rounding should help to ignore the missing hour when going from winter to summer time as well as the extra hour in the other direction
        diff = Math.round(rDate.getTime() - pDate.getTime());
        diff=(int) diff / (1000*60*60*24);
        //dayDif= TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        Log.d("dif in days:", String.valueOf(diff));


        //user doesn't enter things right
        if(pickupTime.length()!=5 && returnTime.length()!=5 && returnDate.length()!=10 && pickupDate.length()!=10){
            text = "Incorrect format";
            duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            Intent intent = new Intent(PlaceHold.this, MainMenu.class);
            startActivity(intent);
        }
        //selected hold under 7 days
        else if(diff<=7){
            Intent intent = new Intent(PlaceHold.this, DisplayBooks.class);
           // Intent bundleIntent = new Intent(PlaceHold.this,ConfirmHold.class);
            Bundle bundle = new Bundle();
            Log.d("pickuptime ph: ", pickupTime);
            bundle.putString("pickUpDate", pickupDate);
            bundle.putString("pickUpTime", pickupTime);
            bundle.putString("returnDate", returnDate);
            bundle.putString("returnTime", returnTime);
            intent.putExtras(bundle);
            Log.d("display books:", "intent for display books");
            startActivity(intent);
        }
        //hold not in time range
        else {
            Intent intent = new Intent(PlaceHold.this, sevenError.class);
            Log.d("date error:", "intent rental date");
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
//        if(v.getId()==R.id.submitButton){
//            Log.d("button", "button");
//        sevenDayCheck();
//        }
    }
}
