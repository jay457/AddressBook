package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnAddContact;
    ArrayList<Address> addressArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddContact = findViewById(R.id.btnAddContact);

        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Send the score to the score Activity
                Intent i = new Intent(MainActivity.this, CreateActivity.class); // Goto Score Activity
                //i.putExtra("playerScore", score.getPlayerScore() );
                //i.putExtra("computerScore", score.getComputerScore() );
                startActivity(i);

            }
        });

    }
}