package com.tistory.simpleisbest.a1010application;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    //Context 변수
    Context context;
    //출력할 데이터의 List
    List<VO> data;
    //하나의 셀을 만들 레이아웃 id
    int layout;
    //layout을 화면에 출력하기 위해서 전개할 Inflater
    LayoutInflater inflater;

    //생성자
    public MyAdapter(Context context, List<VO> data, int layout){
        this.context = context;
        this.data = data;
        this.layout =  layout;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    //이 메소드가 리턴한 개수만큼 리스트 뷰는 항목을 출력
    @Override
    public int getCount() {
        return data.size();
    }
    //항목의 이름을 설정하는 메소드
    @Override
    public Object getItem(int position) {
        return data.get(position).getName();
    }
    //항목의 아이디를 설정하는 메소드
    @Override
    public long getItemId(int position) {
        return position;
    }


    //항목뷰를 만드는 메소드
    //첫번째 : 출력되는 뷰의 인덱스
    //두번째 : 이전 셀의 참조(주소)
    //세번째 : 출력되는 AdapterView
    @Override
    public View getView(int position,
                        View convertView,
                        ViewGroup parent) {

        //position은 지역변수라서 anonymouse class에서 사용할 수 없기 때문에 final로 변환
        final int pos = position;

        //이전에 넘어온 뷰가 없으면 생성
        if(convertView == null){
            convertView = inflater.inflate(layout, parent, false);
            //layout = userview (메인 액티비티에서 매개변수로 받아옴)
            //parent = 놓이게 될 부모뷰. getView 메소드에서 매개변수로 받아온 parent. => 메인 액티비티의 layout.xml인 avtivity_user_sell_view.xml 파일. 즉, ListView
            //false = 그룹 뷰 여부
        }

        //짝수 행과 홀수 행의 배경색을 다르게 설정
        if(position % 2 == 0){
            convertView.setBackgroundColor(Color.LTGRAY);
        }else {
            convertView.setBackgroundColor(Color.GRAY);
        }

        //이미지 출력
        ImageView imageView = (ImageView)convertView.findViewById(R.id.img);
        imageView.setImageResource(data.get(pos).getIcon());
        //텍스트 출력
        TextView textView = (TextView)convertView.findViewById(R.id.text);
        textView.setText(data.get(pos).getName());

        //버튼을 클릭했을 때 수행할 내용 작성
        Button btn = (Button)convertView.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String msg = data.get(pos).getName()+"선택";
                Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
