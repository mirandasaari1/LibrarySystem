package com.example.mirandasaari.librarysystem;


/*
Abstract:java file for user to login to cancel hold
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Miranda Saari on 12/9/2016.
 */

public class Login extends Activity implements View.OnClickListener {

    //widget variables
    private EditText passwordEditText;
    private EditText usernameEditText;
    private Button loginButton;
    private TextView usernameTextView;
    private TextView passwordTextView;
    private Context context;
    private CharSequence text = "";
    private int duration = Toast.LENGTH_SHORT;
    private int errorCount=0;

    private String usernameString = "";
    private String passwordString = "";
    private String TAG = "test";

    //sharedPreferences object
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_system);
        context = getApplicationContext();
        DatabaseHandler db = new DatabaseHandler(this);

        // get references to the widgets
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        usernameTextView = (TextView) findViewById(R.id.usernameTextView);
        passwordTextView = (TextView) findViewById(R.id.passwordTextView);
        loginButton = (Button) findViewById(R.id.loginButton);


        // set the listeners
        usernameEditText.setOnClickListener(this);
        passwordEditText.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        // get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }


    public void verifyUser() {
        usernameString = usernameEditText.getText().toString();
        passwordString = passwordEditText.getText().toString();
        Log.d("username: ", usernameString);
        Log.d("password: ", passwordString);
        DatabaseHandler db = new DatabaseHandler(this);
            if (db.getUser(usernameString)) {
                Bundle extras = getIntent().getExtras();
                String pickupDate=extras.getString("pickUpDate");
                String pickupTime=extras.getString("pickUpTime");
                Log.d("pickString", extras.getString("pickUpTime")+"");
                String returnTime=extras.getString("returnTime");
                String returnDate=extras.getString("returnDate");
                String selectedBook=extras.getString("bookTitle");
             Bundle bundle = new Bundle();

                Log.d("pickUpDate", pickupDate);
              //  Log.d("pickuptime lo: ", pickupTime);
                bundle.putString("username", usernameString);
                bundle.putString("bookTitle", selectedBook);
                bundle.putString("pickUpDate", pickupDate);
                bundle.putString("pickUpTime", pickupTime);
                bundle.putString("returnDate", returnDate);
                bundle.putString("returnTime", returnTime);
                Intent intent = new Intent(Login.this, ConfirmHold.class);
             intent.putExtras(bundle);
                startActivity(intent);

            }
            else {
                    text = "Incorrect login";
                    duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(this, text, duration);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    Intent intent = new Intent(Login.this, MainMenu.class);
                    startActivity(intent);
                }
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.loginButton) {
            verifyUser();
        }
    }
}
