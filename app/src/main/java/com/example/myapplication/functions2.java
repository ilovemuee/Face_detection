package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.ByteArrayOutputStream;

public class functions2 extends AppCompatActivity {
    public static final int REQUEST_CODE = 120;
    MainHelper db = new MainHelper(this);
    EditText text;
    Button button;
    Cursor cursor;
    String fetch;
    ImageButton button2;
    String capture;
    String hello;
    protected void activeTake() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, REQUEST_CODE);

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
