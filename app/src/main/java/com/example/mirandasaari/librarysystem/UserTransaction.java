package com.example.mirandasaari.librarysystem;
/*
Abstract:java file which displays the transactions made by a particular user
 */
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;



public class UserTransaction extends Activity implements View.OnClickListener {

    Button cancelHoldButton;
    //public String username="";

    private ListView listView;
    private String selectedBook="";

    @Override
    public void onCreate(Bundle SavedInstanceState) {;
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.user_transaction_logs);

        Bundle extras = getIntent().getExtras();
       // String username="";
//        if (extras != null) {
//            username = extras.getString("username");
//        }
        String username=extras.getString("username");

        listView = (ListView) findViewById(R.id.transactionList);
        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<String> userTransaction = new ArrayList<String>();
            for (Transactions temp : db.getAllTransactions()) {
                Log.d("enter log", temp.getStudUsername());
                if(temp.getStudUsername().equals(username)){
                    userTransaction.add(temp.getTitle());
                    //Log.d("user transactions: ", temp.getTitle());
                }
            }
        ArrayList<String> unique =new ArrayList<String>();
        Log.d("userTrans: ", userTransaction.toString());
        for(String temp2 : userTransaction){
            if(!unique.contains(temp2)){
                unique.add(temp2);
                Log.d("unique", unique.toString());
            }
            else{
                unique.remove(temp2);
                Log.d("remove u: ", unique.toString());
            }

        }
        Log.d("unique", unique.toString());





        if(!userTransaction.isEmpty()) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, unique);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    selectedBook = (String) listView.getItemAtPosition(position);
                    Bundle extras = getIntent().getExtras();
                    String username = extras.getString("username");
                    Log.d("translag", username);
                    Log.d("translog", selectedBook);

                    Bundle bundle = new Bundle();
                    bundle.putString("bookTitle", selectedBook);
                    bundle.putString("username", username);
                    Intent intent = new Intent(UserTransaction.this, ConfirmCancel.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
        else{
            Intent intent = new Intent(UserTransaction.this, NoReservation.class);
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View v){
//        if(v.getId()==R.id.cancelHoldButton){
//            // Log.d("inside create account", "inside create account");
//            Intent intent = new Intent(UserTransaction.this, ConfirmCancel.class);
//            startActivity(intent);
//        }
    }
}
