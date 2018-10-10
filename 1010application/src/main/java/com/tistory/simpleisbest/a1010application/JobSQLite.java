package com.tistory.simpleisbest.a1010application;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JobSQLite extends SQLiteOpenHelper {
    //생성자
    public JobSQLite(Context context){
        //첫번째 : Context
        //두번째 : 파일명
        //세번째 : Cursor
        //네번째 : 버전
        super(context,"jobdata.db",null,1);
    }

    //데이터베이스를 처음 사용할 때 호출되는 메소드
    //테이블을 생성하고 샘플데이터 추가

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table job_data(_id integer primary key autoincrement, name text, job text);");
        db.execSQL("insert into job_data(name,job) values('박보검','배우')");
        db.execSQL("insert into job_data(name,job) values('박재범','가수')");
    }

    //버전이 변경되었을 때 호출되는 메소드
    //테이블을 삭제하고 다시 생성

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table job_data");
        onCreate(db);
    }
}
