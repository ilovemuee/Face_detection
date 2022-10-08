package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;
    private Button button;
    private Button button2;
    private EditText gettext;
    private EditText getext2;
    private Intent intent;
    private int i = 0;
    public static ArrayList<String> faces = new ArrayList<>();
    private String hello;
    mainhelper db = new mainhelper(this);
    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();
        Drawable drawable = ResourcesCompat.getDrawable(res,R.drawable.ontouch,null);
        Drawable drawable1 = ResourcesCompat.getDrawable(res,R.drawable.green_banner_background,null);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        button = findViewById(R.id.button4);
        button2 = findViewById(R.id.button2);
        gettext = findViewById(R.id.editTextTextPersonName2);
        getext2 = findViewById(R.id.editTextTextPersonName3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activeTakePhoto();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
    public String image(String a, String b) {
        if (!(Python.isStarted())) {
            Python.start(new AndroidPlatform(this));
        }
        Python py = Python.getInstance();
        PyObject pyObj = py.getModule("main");
        PyObject get = pyObj.callAttr("facerecognize", a, b);
        return get.toString();
    }

    protected  void activeTakePhoto()  {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, REQUEST_CODE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Bitmap thumbnail = data.getParcelableExtra("data");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
            try {
                image(encoded,encoded);
                Boolean result = db.insertuserdata(gettext.getText().toString(),getext2.getText().toString(), encoded);
                if(result == true) {
                    Toast.makeText(this, "ur photo and details has been successfully fetched", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "id already taken or not properly return ", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e){
                Toast.makeText(this, "please try again ur photo as it is not properly captured", Toast.LENGTH_SHORT).show();
            }
        }
    }
}