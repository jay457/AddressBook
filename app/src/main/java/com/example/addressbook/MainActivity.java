package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnAddContact;
    ArrayList<Address> addressArrayList;

    LinearLayout cl;

    public static final String CONTACT_PREFS = "CONTACT_PREFS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cl = findViewById(R.id.main);

        SharedPreferences prefs = getSharedPreferences(CONTACT_PREFS, MODE_PRIVATE);

        Map<String,?> keys = prefs.getAll();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            TextView tv = new TextView(this);

            Gson gson = new Gson();
            Address address = gson.fromJson(entry.getValue().toString(),Address.class);

            String txt = address.getfName() + " " + address.getlName() + " | "
                    + address.getEmail() + " | " + address.getPhone();
            tv.setText(txt);
            TextViewCompat.setTextAppearance(tv, R.style.Base_TextAppearance_AppCompat_Title);

            cl.addView(tv);
        }
        
        
        btnAddContact = findViewById(R.id.btnAddContact);
        
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Send the score to the score Activity
                Intent i = new Intent(MainActivity.this, CreateActivity.class); // Goto Score Activity

                startActivity(i);

            }
        });

    }
}