package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.ByteArrayOutputStream;

public class MainActivity2<requestCode> extends AppCompatActivity {
    public static final int REQUEST_CODE = 120;
    mainhelper db = new mainhelper(this);
    private EditText text;
    private Button button;
    private Cursor cursor;
    private String fetch;
    private ImageButton button2;
    private String capture;
    private String hello;
    @SuppressLint({"Range", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        button = this.findViewById(R.id.button4);
        text = this.findViewById(R.id.editTextTextPersonName2);
        button2 = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursor = db.getphoto(text.getText().toString());
                cursor.moveToFirst();
                try {
                    fetch = cursor.getString(cursor.getColumnIndex("dob"));
                    activeTake();

                }
                catch (Exception e){
                    Toast.makeText(MainActivity2.this, "please enter a valid id", Toast.LENGTH_SHORT).show();
                }
            }

        });
        button2.setOnClickListener(new View.OnClickListener() {


                public void onClick (View view){
                 try {
                     Toast.makeText(MainActivity2.this, ima(fetch, capture), Toast.LENGTH_SHORT).show();
                 }
                 catch (Exception e){
                     Toast.makeText(MainActivity2.this, "take your photo again", Toast.LENGTH_SHORT).show();
                 }
            }

        });



    }

    protected String ima(String a, String b) {
        if (!(Python.isStarted())) {
            Python.start(new AndroidPlatform(this));
        }
        Python py = Python.getInstance();
        PyObject pyobj = py.getModule("main");
        PyObject get = pyobj.callAttr("facerecognize", a, b);
        return get.toString();
    }
    private void activeTake() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, REQUEST_CODE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
                Bitmap thumbnail = data.getParcelableExtra("data");
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                capture = encoded;
            }
        }
    }
