package com.tistory.simpleisbest.a1008application;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SQLiteUse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_use);

        //layout에서 필요한 데이터 찾아오기
        Button btninsert = (Button)findViewById(R.id.insert);
        Button btnselect = (Button)findViewById(R.id.select);
        Button btnupdate = (Button)findViewById(R.id.update);
        Button btndelete = (Button)findViewById(R.id.delete);

        final EditText name = (EditText)findViewById(R.id._name);
        final EditText rating = (EditText)findViewById(R.id._rating);
        final TextView id = (TextView) findViewById(R.id._id);

        btninsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.e("log1","삽입버튼 들어옴");
                String inputname = name.getText().toString();
                String inputrating = rating.getText().toString();
                //데이터베이스 연결

                DBOpenHelper dbop = new DBOpenHelper(SQLiteUse.this);
                SQLiteDatabase db = dbop.getWritableDatabase();
                Log.e("log2","데이터베이스 연결 완료");
                db.execSQL("insert into movie(name,rating) values(\'" + inputname + "\'," + inputrating + ");");

                /*
                db.execSQL 대신에 이렇게 쓸 수도 있다. 조금 더 직관적이라서 sql 구문 오타가 줄어들 수 있다.
                ContentValues value = new ContentValues();
                value.put("name", inputname);
                value.put("rating", inputrating);
                db.insert("movie", null, value);
                 */

                dbop.close();
                Log.e("log3","데이터베이스 해제");
                id.setText("삽입성공");
                name.setText("");
                rating.setText("");
            }
        });

        btnselect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String inputname = name.getText().toString();
                if(inputname.trim().length() == 0){
                    Toast.makeText(SQLiteUse.this,"입력된 값이 없음",Toast.LENGTH_SHORT).show();
                    //소리 입력해보기

                    return;
                }
                DBOpenHelper dbop = new DBOpenHelper(SQLiteUse.this);
                SQLiteDatabase db = dbop.getReadableDatabase();
                //db.rawQuery는 select만 가능하다.
                Cursor cursor = db.rawQuery("select * from movie where name='"+inputname+"';",null);


                if(cursor.moveToNext()){
                    id.setText(cursor.getInt(0)+"");
                    name.setText(cursor.getString(1));
                    rating.setText(cursor.getFloat(2)+"");
                }else {
                    id.setText("조회된 데이터가 없습니다.");
                    rating.setText("");
                }
                dbop.close();
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.e("tag1","갱신 버튼 입장");
                //rating만 수정
                DBOpenHelper dbop = new DBOpenHelper(SQLiteUse.this);
                SQLiteDatabase db = dbop.getWritableDatabase();
                Log.e("tag2","데이터베이스 연결");
                String inputrating = rating.getText().toString();
                String inputname = name.getText().toString();
                ContentValues value = new ContentValues();
                Log.e("tag5","벨류 객체 만듦");
                value.put("rating",Float.parseFloat(inputrating));
                Log.e("tag3","갱신1");
                db.update("movie",value,"name='"+inputname+"'",null);
                Log.e("tag4","갱신2");
                Toast.makeText(SQLiteUse.this,"수정 성공",Toast.LENGTH_SHORT).show();
                dbop.close();
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DBOpenHelper dbop = new DBOpenHelper(SQLiteUse.this);
                SQLiteDatabase db = dbop.getWritableDatabase();
                String inputname = name.getText().toString();
                db.delete("movie","name='"+inputname+"'",null);
                Toast.makeText(SQLiteUse.this,"삭제 성공",Toast.LENGTH_SHORT).show();
                dbop.close();
            }
        });
    }
}
