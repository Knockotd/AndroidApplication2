package com.tistory.simpleisbest.a1008application;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AdapterPractice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_practice);

        //출력할 데이터 배열 만들기!
        String [] actor = {
                "박보검", "조진웅", "박서준", "진영", "김유정", "송중기", "박보영", "차태현"
        };

        //위 데이터로 Adapter 생성
        //매개변수1 : Context
        //매개변수2 : 출력할 셀의 모양 : 제공되는 모양
        //매개변수3 : 출력할 데이터
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, actor);

        //ListView에ㅐ 위의 Adapter 연결


        //소스 코드 내부에서 연결 하는 방법
        ListView listView = (ListView)findViewById(R.id.listView);


        //정적인 배열은 소스 코드에서 만드는 것보다는 resource 파일에 만들어 두고 읽어내는 것이 효율적일 수 있습니다.
        //문자열(숫자같은 것도 됨!) 배열은 res/values 디렉토리에 arrays.xml 파일에 정의 해두고 사용할 수 있습니다.
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.actor, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setDivider(new ColorDrawable((Color.BLUE)));
        listView.setDividerHeight(3);

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            //1번 매개변호 : 이벤트가 발생할 객체
            //2번 매개변수 : 항목 뷰 - 선택한 항목의 뷰
            //3번 매개변수 : 클릭한 항목 뷰의 인덱스
            //4번 매개변수 : 항목 뷰의 아이디
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AdapterPractice.this,position+"번째 선택",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
