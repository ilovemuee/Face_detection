package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class functions extends AppCompatActivity {
    static final int REQUEST_CODE = 1;
    Button button;
    Button button2;
    EditText gettext;
    EditText getext2;
    EditText editText;
    EditText editText2;
    public static String encoded;
    Intent intent;
    validation validate = new validation();
    firebase database = new firebase();
    public static int i = 0;
    String hello;
    public String image(String a, String b) {

        if (!(Python.isStarted())) {
            Python.start(new AndroidPlatform(this));
        }
        Python py = Python.getInstance();
        PyObject pyObj = py.getModule("main");
        PyObject get = pyObj.callAttr("facerecognize", a, b);
        return get.toString();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Bitmap thumbnail = data.getParcelableExtra("data");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
            try {

                image(encoded,encoded);
                firebase.fetch obj = new firebase.fetch(gettext.getText().toString(),getext2.getText().toString(),editText.getText().toString(),editText2.getText().toString(),encoded);
                android.os.Handler h = new Handler(Looper.getMainLooper());
                final Boolean[] result = new Boolean[1];
                synchronized (database.insert(hello, obj)) {
                        Toast.makeText(functions.this, "ur photo and details has been fetched", Toast.LENGTH_SHORT).show();
                        SharedPreferences sp = getSharedPreferences("message",0);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.putString("takenPhoto","true");
                        ed.apply();
                        Intent intent = new Intent(this,MainActivity2.class);
                        startActivity(intent);
                    }
            }
            catch (Exception e){
                Toast.makeText(this, "please take ur photo properly", Toast.LENGTH_SHORT).show();
            }
        }
    }
    protected  void activeTakePhoto()  {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, REQUEST_CODE);

    }
}
