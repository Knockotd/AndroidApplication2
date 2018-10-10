package com.tistory.simpleisbest.a1010application;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CursorAdapterPractice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor_adapter);

        ListView simplelist = (ListView)findViewById(R.id.simplelist);
        ListView cursorlist = (ListView)findViewById(R.id.cursorlist);

        //데이터베이스 사용 클래스의 객체 생성 - onCreate 가 호출
        JobSQLite jobSQLite = new JobSQLite(this);

        //데이터베이스에서 읽기 작업
        SQLiteDatabase db = jobSQLite.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from job_data;", null);

        //읽어온 데이터를 List<Map>에 저장하기
        List<Map<String, Object>> list = new ArrayList<>();
        while(cursor.moveToNext()) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", cursor.getString(1));
            map.put("job",cursor.getString(2));
            list.add(map);
        }
        db.close();

        //데이터 출력을 위한 Adapter 생성
        //첫번째 : Context
        //두번째 : 데이터
        //세번째 : 셀의 출력 모양
        //네번째 : 출력할 데이터의 키 배열
        //다섯번째 : 출력할 셀 안의 id
        SimpleAdapter simpleAdapter =
                new SimpleAdapter(CursorAdapterPractice.this,
                        list,
                        android.R.layout.simple_expandable_list_item_2,
                        new String[]{"name","job"},
                        new int[]{android.R.id.text1,android.R.id.text2});
        simplelist.setAdapter(simpleAdapter);

        CursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[]{"name","job"},
                new int[]{android.R.id.text1,android.R.id.text2},
                android.widget.CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        cursorlist.setAdapter(cursorAdapter);
    }
}
