package com.tistory.simpleisbest.a1011application;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SpannablePractice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_practice);
        //TextView 찾아오기
        TextView textView = (TextView)findViewById(R.id.textview);
        //스크롤 가능하도록 설정(이미지 때문)
        textView.setMovementMethod(new ScrollingMovementMethod());

        String data = "고먐미 \n img \n 담신믄 고먐미믜 저주메 걸리고 말맜습니다. \n 담신믄 미제부터 돔그라미가 들머간 글자를 쓸 수 멊습니다.";

        //img 부분에 이미지 삽입
        SpannableStringBuilder builder = new SpannableStringBuilder(data);
        //삽입할 시작 위치 찾기
        int start = data.indexOf("img");
        //삽입할 종료 위치 찾기
        int end = start + "img".length();
        //출력할 이미지 찾기
        Drawable dr = ResourcesCompat.getDrawable(getResources(), R.drawable.nemocat, null);
        //이미지 추출하기
        //dr.setBounds(0,0,dr.getIntrinsicWidth(),dr.getIntrinsicHeight());
        dr.setBounds(0,0,1050,700);
        //이미지를 출력하기 위한 Span 만들기
        ImageSpan imageSpan = new ImageSpan(dr);
        //SpannableBuilder 에 적용
        builder.setSpan(imageSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        //담신믄 고먐미믜 저주메 걸리고 말맜습니다. 부분의 서식을 변경
        start = data.indexOf("담신믄 고먐미믜 저주메 걸리고 말맜습니다.");
        end = start + "담신믄 고먐미믜 저주메 걸리고 말맜습니다.".length();
        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD_ITALIC);
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(2.0f);
        builder.setSpan(styleSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(sizeSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //텍스트 뷰에 적용
        textView.setText(builder);
    }
}
