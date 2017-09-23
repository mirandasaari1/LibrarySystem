package com.example.mirandasaari.librarysystem;
/*
Abstract:Java file for when users input incorrect information
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

public class createError extends Activity implements OnClickListener  {


        //widget variables

        private Button confirmButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.create_error);


            //reference widgets
            confirmButton = (Button) findViewById(R.id.confirmButton);


            // set the listeners
            confirmButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.confirmButton) {
                Intent intent = new Intent(createError.this, MainMenu.class);
                startActivity(intent);
            }

            }
}
