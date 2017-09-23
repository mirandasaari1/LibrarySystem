package com.example.mirandasaari.librarysystem;

/*
Abstract:java file which displays books user with pick from
 */
import android.app.Activity;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;
import java.util.List;

public class DisplayBooks extends Activity implements View.OnClickListener {


    private ListView listView;
    private String selectedBook="";
    //Button selectBookButton = (Button) findViewById(R.id.selectBookButton);

    @Override
    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.book_display_pick);

        Bundle extras = getIntent().getExtras();
        String value;
        if (extras != null) {
            value = extras.getString("key");
        }

        listView = (ListView) findViewById(R.id.bookList);
        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<String> bookList = new ArrayList<String>();
        for (Books temp : db.getAllBooks())
        {
             if(temp.getOnHold().equals("Available")){
                bookList.add(temp.getTitle());
            }
        }
        if(!bookList.isEmpty()) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bookList);
            ;
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    selectedBook = (String) listView.getItemAtPosition(position);
                    Log.d("book name", selectedBook);
                    Bundle extras = getIntent().getExtras();
                    String pickupDate = extras.getString("pickUpDate");
                    String pickupTime = extras.getString("pickUpTime");
                    String returnTime = extras.getString("returnTime");
                    String returnDate = extras.getString("returnDate");
                    Bundle bundle = new Bundle();
                    Log.d("pickuptime db: ", pickupTime);
                    bundle.putString("bookTitle", selectedBook);
                    bundle.putString("pickUpDate", pickupDate);
                    bundle.putString("pickUpTime", pickupTime);
                    bundle.putString("returnDate", returnDate);
                    bundle.putString("returnTime", returnTime);
                    Intent intent = new Intent(DisplayBooks.this, Login.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
        else{
            Intent intent = new Intent(DisplayBooks.this, NoBook.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.addBookButton) {
            Bundle extras = getIntent().getExtras();
            String pickupDate=extras.getString("pickUpDate");
            String pickupTime=extras.getString("pickUpTime");
            String returnTime=extras.getString("returnTime");
            String returnDate=extras.getString("returnDate");
            Bundle bundle = new Bundle();
            bundle.putString("bookTitle", selectedBook);
            bundle.putString("pickUpDate", pickupDate);
            bundle.putString("pickUpTime", pickupTime);
            bundle.putString("returnDate", returnDate);
            bundle.putString("returnTime", returnTime);
            Intent intent = new Intent(DisplayBooks.this, Login.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

}

