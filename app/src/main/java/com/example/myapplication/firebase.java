package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class firebase extends AppCompatActivity {
    private boolean Inserted = false;
    private boolean deleted = false;
    public static class fetch {
        public String name, reg_no, age, degree,photo;
        fetch(String name, String reg_no, String age, String degree,String photo) {
            this.name = name;
            this.reg_no = reg_no;
            this.age = age;
            this.degree = degree;
            this.photo = photo;
        }
    }
    Boolean insert(String id,fetch fetch){
         FirebaseDatabase.getInstance().getReference().child("students").child(id).setValue(fetch).addOnSuccessListener(new OnSuccessListener<Void>() {
             @Override
             public void onSuccess(Void unused) {
                 Inserted = true;
             }
         });
         return Inserted;
    }
    Boolean delete(String id){
           FirebaseDatabase.getInstance().getReference().child("students").child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
               @Override
               public void onSuccess(Void unused) {
                   deleted = true;
               }
           });
          return deleted;
    };
    ArrayList<String> read(String id){
        ArrayList<String> details = new ArrayList<String>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("students").child(id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                details.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    details.add(snapshot1.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        return details;
    }
}
