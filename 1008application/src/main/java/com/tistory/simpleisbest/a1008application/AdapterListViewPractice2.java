package com.tistory.simpleisbest.a1008application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdapterListViewPractice2 extends AppCompatActivity {

    //ListView 출력을 위한 변수
    private List<String> list;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_list_view_practice2);

        list = new ArrayList<>();
        list.add("Encapsulation(캡슐화)");

        adapter = new ArrayAdapter<>(this,android.R.layout.select_dialog_multichoice,list);
        //선택할 수 있는 곳이 보여지기만 함
        listView = (ListView)findViewById(R.id.listView);
        //실제 선택할 수 있도록 함
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);


        Button insertBtn = (Button)findViewById(R.id.insert);
        insertBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText edit = (EditText)findViewById(R.id.word);
                //입력한 내용의 공백을 제거하고 가져오기
                String word = edit.getText().toString().trim();
                if(word.length() == 0){
                    Toast.makeText(AdapterListViewPractice2.this, "삽입할 단어를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    list.add(word);
                    //리스트 뷰 다시 출력
                    adapter.notifyDataSetChanged();
                }
            }
        });

        Button deleteBtn = (Button)findViewById(R.id.delete);
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                /*
                //선택된 행번호 가져오기
                int pos = listView.getCheckedItemPosition();
                //선택한 행 번호가 list 안의 번호인지 확인해서 아니면 리턴
                if(pos < 0 || pos >= list.size()){
                    Toast.makeText(AdapterListViewPractice2.this, "선택하고 삭제를 누르세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                //데이터 삭제
                list.remove(pos);

                */
                //선택여부를 배열로 받아오기
                SparseBooleanArray sba = listView.getCheckedItemPositions();
                //순서대로 접근해서 삭제 -- 이상함
                /*
                for(int i=0; i<sba.size(); i=i+1){
                    if(sba.get(i)){
                        list.remove(i);
                    }
                }
                */

                //뒤에서부터 삭제를 수행해야 합니다.
                for(int i=listView.getCount()-1; i>=0; i=i-1){
                    if(sba.get(i)){
                        list.remove(i);
                    }
                }
                /*
                for(int i=0; i<listView.getCount(); i=i+1){
                if(sba.get(listView.getCount()-(i+1))){
                list.remove(listView.getCount()-(i+1));
                }
                }
                 */
                adapter.notifyDataSetChanged();
                listView.clearChoices();
                Toast.makeText(AdapterListViewPractice2.this, "삭제 성공.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
