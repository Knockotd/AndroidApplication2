package com.tistory.simpleisbest.a1010application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleAdapterView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adapter_view);

        //SimpleAdapter를 이용해서 출력할 데이터 생성
        //List<Map<String, Object>> 구조
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name","박보검");
        map.put("job","배우");
        list.add(map);
        map = new HashMap<>();
        map.put("name","안정은");
        map.put("job","취준생");
        list.add(map);
        map = new HashMap<>();
        map.put("name","안예은");
        map.put("job","유치원교사");
        list.add(map);
        map = new HashMap<>();
        map.put("name","박연주");
        map.put("job","회사원");
        list.add(map);
        map = new HashMap<>();
        map.put("name","김신영");
        map.put("job","간호사");
        list.add(map);

        //데이터 출력을 위한 Adapter 생성
        //첫번째 : Context
        //두번째 : 데이터
        //세번째 : 셀의 출력 모양
        //네번째 : 출력할 데이터의 키 배열
        //다섯번째 : 출력할 셀 안의 id
        SimpleAdapter adapter = new SimpleAdapter(this,list, android.R.layout.simple_expandable_list_item_2,new String[]{"name","job"}, new int[]{android.R.id.text1,android.R.id.text2});

        //ListView에 adapter를 연결해서 출력
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}
