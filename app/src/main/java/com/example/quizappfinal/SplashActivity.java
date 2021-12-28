package com.example.quizappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    public static ArrayList<ModelClass> list;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        list = new ArrayList<ModelClass>();

        // sau khi làm xg sqlite db vs số câu hỏi thì lấy lây bỏ list

//        list =
        list.add(new ModelClass("question 1 : how do u feel ","a","b","c","d","a"));
        list.add(new ModelClass("aswer2","a","b","c","d","a"));
        list.add(new ModelClass("aswer3","a","b","c","d","a"));
        list.add(new ModelClass("aswer4","a","b","c","d","a"));
        list.add(new ModelClass("aswer5","a","b","c","d","a"));
        //neu co internet

//        databaseReference = FirebaseDatabase.getInstance().getReference("Question");
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    ModelClass modelClass = snapshot.getValue(ModelClass.class);
//                    list.add(modelClass);
//                }
//                Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
//                startActivity(intent);
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

//      nếu ko có internet
        //lay data từ sqlite lên

//
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,DashboardActivity.class);
                startActivity(intent);
                //call api or some thing
            }

        }, 1500);
    }
}