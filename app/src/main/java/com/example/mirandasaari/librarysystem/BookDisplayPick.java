package com.example.mirandasaari.librarysystem;
/*
Abstract:Java file for user to select a book
 */
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miranda Saari on 12/8/2016.
 */

public class BookDisplayPick extends ListActivity implements View.OnClickListener {
    ArrayAdapter<String> adapter;
    DatabaseHandler db = new DatabaseHandler(this);
    ListView listView = (ListView) findViewById(R.id.list);
    Button selectBookButton= (Button) findViewById(R.id.selectBookButton);

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.book_display);

        List<Books> books= new ArrayList();
        books=db.getAllBooks();
        String[] bookArray = new String[books.size()];
        bookArray = books.toArray(bookArray);

        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bookArray);
        setListAdapter(adapter);
        listView.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {
        //how to pick book
    }
}
