package com.example.quizappfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context) {
        super(context, "QuizApp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE QuizAppQuestion (Question STRING PRIMARY KEY , oA TEXT, oB TEXT , oC TEXT, oD TEXT , ans TEXT)");
        _db.execSQL("INSERT INTO QuizAppQuestion VALUES" +
                "('question 1 : Đâu là tên của một câu truyện cười dân gian ? ', 'Thầy bói xem ngan', 'Thầy bói xem voi', 'Thầy bói xem heo', 'Thầy bói xem vịt', 'Thầy bói xem voi')");
        _db.execSQL("INSERT INTO QuizAppQuestion VALUES" +
                "('question 2 : Thưa rằng tôi đi hái ... Hai anh mở túi đưa trầu cho ăn ? ', 'Dâu', 'Ổi', 'Cau', 'Táo', 'Dâu')");
        _db.execSQL("INSERT INTO QuizAppQuestion VALUES" +
                "('question 3 : Đâu là tên của một loại phương tiện vận chuyển người thời trước? ', 'Hành', 'Tỏi', 'Kiệu', 'Dưa', 'Kiệu')");
        _db.execSQL("INSERT INTO QuizAppQuestion VALUES" +
                "('question 4 : Loài động vật nào sau đây có gai trên cơ thể?  ', 'Chó', 'Mèo', 'Nhím', 'Hùng', 'Nhím')");
        _db.execSQL("INSERT INTO QuizAppQuestion VALUES" +
                "('question 5 :  Haiku là thể thơ truyền thống của nước nào? ', 'Anh', 'Nhật Bản', 'Pháp', 'Đức', 'Nhật Bản')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
        _db.execSQL("DROP TABLE IF EXISTS QuizAppQuestion");
        onCreate(_db);
    }

    public  ArrayList<ModelClass> getAllData(){
        ArrayList<ModelClass> list = new ArrayList<ModelClass>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM QuizAppQuestion",null);

        while (cursor.moveToNext()){
            String Question = cursor.getString(0);
            String oA = cursor.getString(1);
            String oB = cursor.getString(2);
            String oC = cursor.getString(3);
            String oD = cursor.getString(4);
            String ans = cursor.getString(5);

            ModelClass modelClass = new ModelClass( Question, oA, oB, oC, oD, ans);
            list.add(modelClass);
        }
        return list;
    }
}
