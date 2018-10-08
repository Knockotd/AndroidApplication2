package com.tistory.simpleisbest.a1008application;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    //생성자
    public DBOpenHelper(Context context){
        super(context, "product.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //테이블 생성과 샘플 데이터 작성
        db.execSQL("create table movie(id integer primary key autoincrement, name text, rating real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //테이블을 삭제하고 다시 생성
        db.execSQL("drop table movie;");
        onCreate(db);
    }
}
