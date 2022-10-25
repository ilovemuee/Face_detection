package com.example.myapplication;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.widget.Toast;
import org.w3c.dom.Text;
public class mainhelper extends SQLiteOpenHelper {
    public mainhelper(Context context){
        super(context,"userdata.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(id text primary key,contact text,dob text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }
    public boolean insertuserdata(String id, String contact, String dob) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        long result = db.insert("Userdetails", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean updateuserdata(String id,String contact,String dob) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        long result = db.update("Userdetails", contentValues, "id=?", new String[]{id});
        db.close();
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }
    public boolean deleteuserdata(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("Userdetails","id=?",new String[]{id});
        db.close();
        if(result == -1){
            return true;
        }
        else{
            return false;
        }
    }
    public Cursor getdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from Userdetails",null);
        return result;

    }
    public Cursor getphoto(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select dob from Userdetails where id=?",new String[]{id});
        return result;
    }
}

