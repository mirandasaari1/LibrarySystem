package com.example.mirandasaari.librarysystem;
/*
Abstract:DID NOT USE
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


public class InsertBook extends Activity implements OnClickListener {

    //widget variables
    private Button addBookButton;
    private EditText titleEditText;
    private EditText authorEditText;
    private EditText isbnEditText;
    private EditText hourlyFeeEditText;
    private TextView titleTextView;
    private TextView authorTextView;
    private TextView isbnTextView;
    private TextView hourlyFeeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_system);


        // get references to the widgets
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        authorTextView = (TextView) findViewById(R.id.authorTextView);
        isbnTextView = (TextView) findViewById(R.id.isbnTextView);
        hourlyFeeTextView = (TextView) findViewById(R.id.hourlyFeeTextView);
        addBookButton = (Button) findViewById(R.id.addBookButton);
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        authorEditText = (EditText) findViewById(R.id.authorEditText);
        isbnEditText = (EditText) findViewById(R.id.isbnEditText);
        hourlyFeeEditText = (EditText) findViewById(R.id.hourlyFeeEditText);


        // set the listeners
        addBookButton.setOnClickListener(this);
        titleEditText.setOnClickListener(this);
        authorEditText.setOnClickListener(this);
        isbnEditText.setOnClickListener(this);
        hourlyFeeEditText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.addBookButton) {
           //pass variables on for verification
        }

    }
}
