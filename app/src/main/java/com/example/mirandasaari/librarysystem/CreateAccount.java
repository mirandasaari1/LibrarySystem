package com.example.mirandasaari.librarysystem;
/*
Abstract:Java file for user to create a new account
 */

import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import java.text.NumberFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.text.DecimalFormat;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateAccount extends Activity implements OnClickListener{

    //widget variables
    private EditText passwordEditText;
    private EditText usernameEditText;
    private Button createButton;
    private TextView usernameTextView;
    private TextView passwordTextView;
    private Context context;
    private CharSequence text = "";
    private int duration = Toast.LENGTH_SHORT;

    //instance variables
    private String usernameString = "";
    private String passwordString = "";
    private String TAG = "test";
    private int errorCounter=0;
    private char specialSymbol[] = {'!', '@', '#', '$'};
    private char numbers[] = {'0','1','2','3','4','5','6','7','8','9'};
    private int symbolFlag = 0;
    private int numberFlag = 0;
    private int letterCount = 0;
    private int symbolFlagP = 0;
    private int numberFlagP = 0;
    private int letterCountP = 0;
    private int usernameFlag=0;
    private int passwordFlag=0;
    private String transactionType="";
    private String username="";

    //sharedPreferences object
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        context = getApplicationContext();
        DatabaseHandler db = new DatabaseHandler(this);

        // get references to the widgets
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        usernameTextView = (TextView) findViewById(R.id.usernameTextView);
        passwordTextView = (TextView) findViewById(R.id.passwordTextView);
        createButton = (Button) findViewById(R.id.createButton);

        // set the listeners
        usernameEditText.setOnClickListener(this);
        passwordEditText.setOnClickListener(this);
        createButton.setOnClickListener(this);

        // get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    //verifies username and password
    public boolean verifyUsernameAndPassword() {

        usernameString = usernameEditText.getText().toString();
        passwordString = passwordEditText.getText().toString();
        Log.d("entered verify", "entered verify");
        DatabaseHandler db = new DatabaseHandler(this);
        if (errorCounter <= 1) {
            //checks username availability
            if(db.getUser(usernameString)){
                usernameEditText.setError("Username already exists, please try again");
                usernameFlag=2;
            }
            else{
                //checks username requirements
               if(usernameString.matches(".*\\d.*") && usernameString.matches(".*[a-zA-Z]{3,}.*") && usernameString.matches(".*[!@#$]+.*")){
                   usernameFlag=1;
               }
                //checks requirements for password
                if(passwordString.matches(".*\\d.*") && passwordString.matches(".*[a-zA-Z]{3,}.*") && passwordString.matches(".*[!@#$]+.*")){
                    passwordFlag=1;
                }
            }

            //all username requirements met
            if(usernameFlag==1&& passwordFlag==1) {
                db.addUser(new User(usernameString,passwordString));
                db.addTransaction(new Transactions(usernameString,"New Account"));
                Log.d("new user", "user added");
                // display account creation success
                text = "Account was successfully created!";
                duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(this, text, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                Intent intent = new Intent(CreateAccount.this, MainMenu.class);
                startActivity(intent);

                return true;
            }

            //both requuirements not met
             else if (passwordFlag == 0 && usernameFlag == 0) {
                usernameEditText.setError("Username must contain at least 1 symbol, 1 number, and 3 or more letters");
                passwordEditText.setError("Password must contain at least 1 symbol, 1 number, and 3 or more letters");
                errorCounter++;
                return false;
            //username requirements not met
            } else if (passwordFlag == 0 && usernameFlag == 1) {
                passwordEditText.setError("Password must contain at least 1 symbol, 1 number, and 3 or more letters");
                errorCounter++;
                return false;
             //password requirements not met
            } else if (passwordFlag == 1 && usernameFlag == 0) {
                usernameEditText.setError("Username must contain at least 1 symbol, 1 number, and 3 or more letters");
                errorCounter++;
                return false;
            }
            else if (passwordFlag == 0 && usernameFlag == 2) {
                usernameEditText.setError("Username already exists, please try again");
                errorCounter++;
                return false;
            }
        }
        //failed to meet requirements too many times
        else if (errorCounter > 1) {
                Intent intent = new Intent(CreateAccount.this, createError.class);
                //v.getContext().startActivity(intent);
                startActivity(intent);
        }
        usernameString="";
        passwordString="";
        usernameFlag=0;
        passwordFlag=0;
        return false;
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId()==R.id.createButton)
        verifyUsernameAndPassword();
    }
}
