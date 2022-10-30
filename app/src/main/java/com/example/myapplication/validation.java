package com.example.myapplication;

import android.widget.EditText;

import java.util.regex.Pattern;

import javax.xml.validation.Validator;

public class validation  {
    Boolean validatname(EditText name){
        String val = name.getText().toString();
        if(val.isEmpty()){
            name.setError("field cannot be empty");
            return false;
        }
       else {
            name.setError(null);
             return true;
        }
    }
    Boolean validation_regno(EditText regno){
        String val = regno.getText().toString();
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        if(val.isEmpty()){
            regno.setError("field cannot be empty");
            return false;
        }
        else if(val.length() < 8 && val.length() > 16){
            regno.setError("please put smaller range");
            return false;
        }
        else if (!(p.matcher(val).matches())) {
            regno.setError("filed can only have numbers");
            return false;
        }
        else{
            regno.setError(null);
            return true;
        }

    }
    Boolean getValidation_age(EditText age){
        String val = age.getText().toString();
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        if(val.isEmpty()){
            age.setError("field cannot be empty");
            return false;
        }
        else if(val.length() < 8 && val.length() > 16){
            age.setError("please put smaller range");
            return false;
        }
        else if (!(p.matcher(val).matches())) {
            age.setError("filed can only have numbers");
            return false;
        }
        else{
            age.setError(null);
            return true;
        }

    }
    Boolean getValidation_degree(EditText degree){
        String val = degree.getText().toString();
        if(val.isEmpty()){
            degree.setError("field cannot be empty");
            return false;
        }
        else {
            degree.setError(null);
            return true;
        }

    }
}

