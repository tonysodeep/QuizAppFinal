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
        DataBaseHelper databaseHelper = new DataBaseHelper(this);
        list = databaseHelper.getAllData();

//        list = new ArrayList<ModelClass>();
//
//        // sau khi làm xg sqlite db vs số câu hỏi thì lấy lây bỏ list
//        list.add(new ModelClass
//                ("question 1 : Đâu là tên của một câu truyện cười dân gian ? ","Thầy bói xem ngan","Thầy bói xem voi","Thầy bói xem vịt","Thầy bói xem heo","Thầy bói xem voi"));
//        list.add(new ModelClass
//                ("question 2 : Thưa rằng tôi đi hái ... Hai anh mở túi đưa trầu cho ăn ? ","Dâu","Cau","Ổi","Táo","Dâu"));
//        list.add(new ModelClass
//                ("question 3 : Đâu là tên của một loại phương tiện vận chuyển người thời trước? ","Hành","Tỏi","Kiệu","Dưa","Kiệu"));
//        list.add(new ModelClass
//                ("question 4 : Loài động vật nào sau đây có gai trên cơ thể? ","Chó","Mèo","Hùng","Nhím","Nhím"));
//        list.add(new ModelClass
//                ("question 5 : Haiku là thể thơ truyền thống của nước nào? ","Anh","Nhật Bản ","Pháp","Ý","Nhật Bản"));
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,DashboardActivity.class);
                startActivity(intent);
//                call api or some thing
            }

        }, 1500);
    }
}