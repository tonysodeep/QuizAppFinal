package com.example.quizappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

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
        DataBaseHelper databaseHelper = new DataBaseHelper(this);
        list = databaseHelper.getAllData();
        loaddata();

//        list = new ArrayList<ModelClass>();
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


//

    }
    private void loaddata(){
        if(AppUtil.isNetworkAvailable(this)){
            //Network Connect
            // API
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this,DashboardActivity.class);
                    startActivity(intent);
                    finish();
//                call api or some thing
                }

            }, 1500);
        }else{
            // Network disconnect
            Toast.makeText(this, "Network disconnected", Toast.LENGTH_SHORT).show();
            DataBaseHelper databaseHelper = new DataBaseHelper(this);
            list = databaseHelper.getAllData();
        }
    }
}