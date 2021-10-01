package com.example.addressbook;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class CreateActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnGallery, btnSave;

    static ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();

    ClipData cd;
    int photoCount;
    int CAMERA_CODE = 3;
    int GALLERY_CODE = 2;

    boolean hasPermission;

    public static final String CONTACT_PREFS = "CONTACT_PREFS";

    EditText fName, lName, email, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        fName = findViewById(R.id.editTextTextPersonFirstName);
        lName = findViewById(R.id.editTextTextPersonLastName);
        phone = findViewById(R.id.editTextPhone);
        email = findViewById(R.id.editTextTextEmailAddress);

        imageView = findViewById(R.id.imageView);
        btnGallery = findViewById(R.id.btnGallery);
        btnSave = findViewById(R.id.btnSave);

        setDefaultImages();

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hasPermission = checkPlayPermission(Manifest.permission.CAMERA,
                        CAMERA_CODE);

                if (hasPermission) {

                    Intent i = new Intent(Intent.ACTION_PICK);
                    i.setType("image/*");

                    i.setAction(Intent.ACTION_PICK);

                    try {
                        startActivityForResult(Intent.createChooser(i, "Select Image"),
                                GALLERY_CODE);
                        setImage();
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                    }


                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sp = getSharedPreferences(CONTACT_PREFS, MODE_PRIVATE);

                SharedPreferences.Editor editor = sp.edit();

                Address newAddress = new Address(fName.getText().toString(), lName.getText().toString(),
                        phone.getText().toString(), email.getText().toString());

                Gson gson = new Gson();
                String json = gson.toJson(newAddress);
                editor.putString("address", json);
                editor.commit();

                Intent i = new Intent(CreateActivity.this, MainActivity.class); // Goto Score Activity

                startActivity(i);

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int result, Intent intent) {
        super.onActivityResult(requestCode, result, intent);

        Bitmap bitmap;
        photoCount = bitmapList.size();

        if (requestCode == GALLERY_CODE && result == RESULT_OK && intent != null) {
            //bitmapList.clear();
            cd = ClipData.newPlainText("", "");
            cd = intent.getClipData();

            int cdSize = cd.getItemCount();

            if (photoCount + cdSize <= 1) {

                for (int i = 0; i < cdSize; i++) {
                    if (result == Activity.RESULT_OK) {
                        ClipData.Item item = cd.getItemAt(i);
                        Uri targetUri = item.getUri();
                        try {
                            bitmap = Bitmap.createScaledBitmap(BitmapFactory.
                                            decodeStream(getContentResolver().
                                                    openInputStream(targetUri)),
                                    imageView.getWidth(),
                                    imageView.getHeight(), true);

                            bitmapList.add(bitmap);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    protected void setImage() {
        imageView.setImageBitmap(bitmapList.get(0));
    }

    public boolean checkPlayPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_DENIED) {

            Toast.makeText(this, "Please accept the required permissions.",
                    Toast.LENGTH_SHORT).show();

            ActivityCompat.requestPermissions(this,
                    new String[] { permission },
                    requestCode);

            return false;
        } else {
            btnGallery.setEnabled(true);
        }
        return true;
    }

    protected void setDefaultImages(){
        imageView.setImageResource(R.drawable.blank_img_background);

    }

}
