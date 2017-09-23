package com.example.mirandasaari.librarysystem;
/*
Abstract:java file which displays the systems transactions for the librarian
 */
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.List;

public class TransactionLogs extends Activity implements OnClickListener {
    ListView listView;
    Button addBookButton;
    Button noButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("break1", "break1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_logs);


        //reference widgets
        addBookButton = (Button) findViewById(R.id.addBookButton);
        noButton = (Button) findViewById(R.id.noButton);

        // set the listeners
        addBookButton.setOnClickListener(this);
        noButton.setOnClickListener(this);
        ;

        listView = (ListView) findViewById(R.id.transactionList);
        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<String> transactions = new ArrayList<String>();
        for (Transactions temp : db.getAllTransactions()) {
            transactions.add(temp.toString());//can I add toString
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, transactions);;
        listView.setAdapter(adapter);


    }

    @Override
    public void onClick(View v){
        if(v.getId()==R.id.addBookButton){
           // Log.d("inside create account", "inside create account");
            Intent intent = new Intent(TransactionLogs.this, AddBook.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.noButton){
            Intent intent = new Intent(TransactionLogs.this, MainMenu.class);
            startActivity(intent);
        }
}
}
