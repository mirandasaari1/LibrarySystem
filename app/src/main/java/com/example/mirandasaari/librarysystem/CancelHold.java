package com.example.mirandasaari.librarysystem;
/*
Abstract:Java file for user to login to cancel their hold
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

public class CancelHold extends Activity implements OnClickListener {

    //widget variables
    private EditText passwordEditText;
    private EditText usernameEditText;
    private Button loginButton;
    private TextView usernameTextView;
    private TextView passwordTextView;
    private Context context;
    private CharSequence text = "";
    private int duration = Toast.LENGTH_SHORT;
    private int errorCount = 0;

    private String usernameString = "";
    private String passwordString = "";
    private String TAG = "test";

    //sharedPreferences object
    private SharedPreferences savedValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancel_hold);
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

    // verifies user login
    public boolean verifyUser() {
        usernameString = usernameEditText.getText().toString();
        passwordString = passwordEditText.getText().toString();
        DatabaseHandler db = new DatabaseHandler(this);
        if (errorCount <= 1) {

            if (db.getUser(usernameString)) {
                Bundle bundle = new Bundle();
                bundle.putString("username", usernameString);
                Intent intent = new Intent(CancelHold.this, UserTransaction.class);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                usernameEditText.setError("Username/Password is incorrect, please try again");
                errorCount++;
                return false;
            }
        }

        //failed too many attempts
        else if(errorCount>1){
            Intent intent = new Intent(CancelHold.this, createError.class);
            startActivity(intent);
        }
      return false;
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.loginButton) {
            verifyUser();}

    }
}

