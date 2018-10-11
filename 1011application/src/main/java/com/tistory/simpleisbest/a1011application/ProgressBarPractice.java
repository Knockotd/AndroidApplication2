package com.tistory.simpleisbest.a1011application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProgressBarPractice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_practice);

        final ProgressBar bar = (ProgressBar)findViewById(R.id.progress1);
        final ProgressBar ring = (ProgressBar)findViewById(R.id.progress2);

        //여러 개의 클릭 이벤트를 처리하기 위한 핸들러 만들기
        View.OnClickListener eventHandler = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.start:
                        bar.setProgress(bar.getProgress()+20);
                        ring.setVisibility(View.VISIBLE);
                        break;
                    case R.id.end:
                        bar.setProgress(bar.getProgress()-20);
                        ring.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        };
    Button start = (Button)findViewById(R.id.start);
    Button end = (Button)findViewById(R.id.end);
    start.setOnClickListener(eventHandler);
    end.setOnClickListener(eventHandler);

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        final TextView textV = (TextView)findViewById(R.id.textView2);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            //textV.setText(progress+"");
            //progress는 숫자라서 Text에 넣으려면 문자로 바꾸어야 함.
                //String.format("%d",progress)); 라고 넣어도 됨.
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(ProgressBarPractice.this,"값 변경 시작",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textV.setText(seekBar.getProgress()+"");
                Toast.makeText(ProgressBarPractice.this,"값 변경 종료",Toast.LENGTH_SHORT).show();
            }
        });

        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        final TextView textVV = (TextView)findViewById(R.id.textView3);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                textVV.setText(String.format("%.1f",rating)+"유저가 건들인 여부"+fromUser);
            }
        });
    }
}
