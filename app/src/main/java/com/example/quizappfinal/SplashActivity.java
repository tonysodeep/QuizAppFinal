package com.example.quizappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizappfinal.helpers.DataBaseHelper;
import com.example.quizappfinal.utils.AppUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    public static ArrayList<ModelClass> list;
    DatabaseReference databaseReference;

    DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        list = new ArrayList<ModelClass>();
        databaseHelper = new DataBaseHelper(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        }, 1500);
        Log.d("AAA", "list of data " + list.size());
    }

    private void loadData() {
        if (AppUtil.isNetworkAvailable(this)) {
            Toast.makeText(this,"Internet connected",Toast.LENGTH_SHORT).show();
            databaseReference = FirebaseDatabase.getInstance().getReference("Question");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        ModelClass modelClass = snapshot.getValue(ModelClass.class);
                        list.add(modelClass);
                    }
                    Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        } else {
            Toast.makeText(this,"Internet not connected",Toast.LENGTH_SHORT).show();
            list = databaseHelper.getAllData();
            Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
            startActivity(intent);
        }
    }
}