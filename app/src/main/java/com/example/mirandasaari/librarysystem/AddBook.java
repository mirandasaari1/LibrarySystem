package com.example.mirandasaari.librarysystem;
/*
Abstract:Java file for librarian to input new book information
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


public class AddBook extends Activity implements OnClickListener{

    //widget variables
    private Button submitButton;
    private TextView addBookTextView;
    private EditText authorEditText;
    private EditText titleEditText;
    private EditText isbnEditText;
    private EditText chargeEditText;

    private String titleString="";
    private String authorString="";
    private String isbnString="";
    private String chargeString="";
    private CharSequence text = "";
    private int duration = Toast.LENGTH_SHORT;
    private double charge=0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_books);
        DatabaseHandler db = new DatabaseHandler(this);


        // get references to the widgets
        addBookTextView = (TextView) findViewById(R.id.addBookTextView);
        submitButton = (Button) findViewById(R.id.submitButton);
        authorEditText = (EditText) findViewById(R.id.authorEditText);
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        isbnEditText = (EditText) findViewById(R.id.isbnEditText);
        chargeEditText = (EditText) findViewById(R.id.chargeEditText);


        // set the listeners
        submitButton.setOnClickListener(this);
        titleEditText.setOnClickListener(this);
        authorEditText.setOnClickListener(this);
        isbnEditText.setOnClickListener(this);
        chargeEditText.setOnClickListener(this);
    }

    public void verifyInformation() {
        titleString = titleEditText.getText().toString();
        authorString = authorEditText.getText().toString();
        isbnString = isbnEditText.getText().toString();
        chargeString = chargeEditText.getText().toString();
        //charge=Double.parseDouble(chargeString);

        //fields are missing
        if(titleString==null || authorString==null || isbnString==null || chargeString==null ||titleString.equals("Title") || authorString.equals("Author") || isbnString.equals("ISBN")|| chargeString.equals("Charge Per Hour")){
            Intent intent = new Intent(AddBook.this, AddBookError.class);
            startActivity(intent);
        }

        else {
            text = "Book has beed added to libraries system";
            duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            DatabaseHandler db = new DatabaseHandler(this);
            db.addBook(new Books(titleString,authorString,isbnString,chargeString));
            Intent intent = new Intent(AddBook.this, MainMenu.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submitButton) {
            verifyInformation();
        }
    }
}
