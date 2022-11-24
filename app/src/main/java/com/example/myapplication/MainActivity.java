package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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
public class MainActivity extends functions {

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();
        Drawable drawable = ResourcesCompat.getDrawable(res,R.drawable.ontouch,null);
        Drawable drawable1 = ResourcesCompat.getDrawable(res,R.drawable.green_banner_background,null);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        button = findViewById(R.id.button4);

        gettext = findViewById(R.id.editTextTextPersonName2);
        editText = findViewById(R.id.editTextTextPersonName3);
        editText2 = findViewById(R.id.editTextTextPersonName4);
        getext2 = findViewById(R.id.editTextTextPersonName3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            { if(validate.validation_regno(gettext) && validate.validatname(getext2) && validate.getValidation_age(editText) && validate.getValidation_degree(editText2)){
                activeTakePhoto();
            }
            }
        });

        hello = String.valueOf((int)(Math.random()*999999999));
        SharedPreferences sp = getSharedPreferences("message",0);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("text",hello);
        ed.apply();

    }
}