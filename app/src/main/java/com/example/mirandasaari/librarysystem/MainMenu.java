package com.example.mirandasaari.librarysystem;
/*
Title: LibrarySystem
Author:Miranda Saari
Date Due: December 9
ID: 4229
Abstract:java file the app opens up to
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
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;


public class MainMenu extends Activity implements OnClickListener {

    //widget variables
    private Button createAccountButton;
    private Button placeHoldButton;
    private Button cancelHoldButton;
    private Button manageSystemButton;
    private Context context;
    private CharSequence text = "";

    private static int calledMyMethod=0;


    //sharedPreferences object
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        context=getApplicationContext();
        DatabaseHandler db = new DatabaseHandler(this);

        //inserts users
        if(calledMyMethod==0) {
            insertDB();
            calledMyMethod = 1;
        }

        List<User> users= new ArrayList();
        users=db.getAllUsers();

        for(User c : users){
            Log.d("users",c.toString());
        }

        //reference widgets
        createAccountButton = (Button) findViewById(R.id.createAccountButton);
        placeHoldButton = (Button) findViewById(R.id.placeHoldButton);
        cancelHoldButton = (Button) findViewById(R.id.cancelHoldButton);
        manageSystemButton = (Button) findViewById(R.id.manageSystemButton);


        // set the listeners
        createAccountButton.setOnClickListener(this);
        placeHoldButton.setOnClickListener(this);
        cancelHoldButton.setOnClickListener(this);
        manageSystemButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.createAccountButton){
            Log.d("inside create account", "inside create account");
            Intent intent = new Intent(MainMenu.this, CreateAccount.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.placeHoldButton){
            Intent intent = new Intent(MainMenu.this, PlaceHold.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.cancelHoldButton){
            Intent intent = new Intent(MainMenu.this, CancelHold.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.manageSystemButton){
            Intent intent = new Intent(MainMenu.this, ManageSystem.class);
            startActivity(intent);
        }
    }

    private final void insertDB(){
        DatabaseHandler db = new DatabaseHandler(this);
        // Inserting Users
        Log.d("Insert: ", "Inserting ..");
        db.addUser(new User("a@lice5", "@csit100"));
        db.addUser(new User("$brian7", "123abc##"));
        db.addUser(new User("!chris12!", "CHRIS12!!"));
        db.addUser(new User("!admin2", "!admin2"));

        //insert books
        db.addBook(new Books("Hot Java", "S.Narayanan", "123-ABC-101", "0.05"));
        db.addBook(new Books("Fun Java", "Y.Byun", "ABCDEF-09", "1.00"));
        db.addBook(new Books("Algorithm for Java", "K.Alice", "CDE-777-123", "0.25"));
    }
}
