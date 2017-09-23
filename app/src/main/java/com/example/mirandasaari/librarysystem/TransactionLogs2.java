package com.example.mirandasaari.librarysystem;
/*
Abstract:DID NOT USE
 */

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class TransactionLogs2 extends Activity implements View.OnClickListener {

        //widget variables
        private Button createAccountButton;
        private Button placeHoldButton;
        private Button cancelHoldButton;
        private Button manageSystemButton;
        private Context context;
        private CharSequence text = "";


        //sharedPreferences object
        private SharedPreferences savedValues;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.transaction_logs_2);
            context=getApplicationContext();
            DatabaseHandler db = new DatabaseHandler(this);


        }

        @Override
        public void onClick(View v) {

        }
    }
