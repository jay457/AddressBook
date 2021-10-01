package com.example.addressbook;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;


public class CreateActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        imageView = findViewById(R.id.imageView);

        setDefaultImages();

    }

    protected void setDefaultImages(){
        imageView.setImageResource(R.drawable.blank_img_background);

    }

}
