package com.tistory.simpleisbest.androidapplication2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

//데이터베이스 클래스(SQLiteOpenHelper 상속)
class WordDBHelper extends SQLiteOpenHelper{
    //생성자
    public WordDBHelper(Context context){
        super(context, "engword.db", null ,1);
    }

    //처음 사용될 때 호출되는 메소드
    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("create table dic(id integer primary key autoincrement, eng text, kor text);");
    }
    //버전이 변경되면 호출되는 메소드
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //테이블 삭제
        db.execSQL("drop table if exists dic");
        //다시 테이블 생성
        onCreate(db);
    }
}

public class DataActivity extends AppCompatActivity {
    private WordDBHelper dbHelper;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        dbHelper = new WordDBHelper(this);
        result = (TextView)findViewById(R.id.result);

        //갱신버튼 눌렀을 때 - eng 값이 orange인 데이터를 kor값을 귤로 변경
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update dic set kor = '귤' where eng ='orange';");
                dbHelper.close();
                result.setText("수정 성공"
                );
            }
        });

        //삭제버튼 눌렀을 때
        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("delete from dic where kor = '귤'");
                dbHelper.close();
                result.setText("삭제 성공");
            }
        });

        //조회버튼 눌렀을 때
        findViewById(R.id.select).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                //select 구문 실행
                Cursor cursor = db.rawQuery("select eng, kor from dic", null);

                String resultText = "";
                //데이터 전체를 순회
                while (cursor.moveToNext()){
                    String eng = cursor.getString(0);
                    String kor = cursor.getString(1);
                    resultText = resultText + eng + ":" + kor + "\n";
                }
                result.setText(resultText);
                db.close();
            }
        });

        //삽입버튼 눌렀을 때
        findViewById(R.id.insert).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.e("tag1", "이벤트발생");
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Log.e("tag2", "데이터베이스 연결 에러");
                //SQL로 insert

                /*
                db.execSQL("insert into dic(eng, kor) values('apple', '사과')");

                Log.e("tag3","데이터베이스 실행 에러");
                */
                //SQL 사용하지 않고 데이터를 삽입
                ContentValues values = new ContentValues();
                values.put("eng","orange");
                values.put("kor","오렌지");
                db.insert("dic",null,values);
                result.setText("삽입 성공");
                dbHelper.close();
            }
        }
        );
    }
}
