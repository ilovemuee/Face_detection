package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import java.util.ArrayList;

public class MainActivity2<requestCode> extends functions2 {

    @SuppressLint({"Range", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        button = this.findViewById(R.id.button4);
        text = this.findViewById(R.id.editTextTextPersonName2);
        button2 = findViewById(R.id.button3);
        firebase fire = new firebase();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sp = getSharedPreferences("message", 0);
                String d = sp.getString("text", "-1");
                ArrayList<String> fetched = new ArrayList<String>();
                Toast.makeText(MainActivity2.this, d, Toast.LENGTH_SHORT).show();
                fetched = fire.read(d);
                Handler h = new Handler(Looper.getMainLooper());
                ArrayList<String> finalFetched = fetched;
                h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                fetch = finalFetched.get(3);
                                activeTake();
                            }
                            catch (Exception e) {
                                Toast.makeText(MainActivity2.this, "please enter a valid id", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },2500);
                    }

        });


        Intent intent = new Intent(this,Tabbedapp.class);
        button2.setOnClickListener(new View.OnClickListener() {
                public void onClick (View view){
                 try {
                     String next = ima(fetch, capture);
                     if(next.equals("True")){
                         startActivity(intent);
                         finish();
                     }
                     else{
                         Toast.makeText(MainActivity2.this, "face doesn't match", Toast.LENGTH_SHORT).show();
                     }
                 }
                 catch (Exception e){
                     Toast.makeText(MainActivity2.this, "take your photo again", Toast.LENGTH_SHORT).show();
                 }
            }

        });
    }

    }
