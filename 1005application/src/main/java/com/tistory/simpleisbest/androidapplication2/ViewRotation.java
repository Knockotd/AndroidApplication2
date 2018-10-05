package com.tistory.simpleisbest.androidapplication2;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ViewRotation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rotation);
    }

    //기기 방향이나 키보드가 숨는 등의 환경이 변경될 때 호출되는 메소드
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    setContentView(R.layout.activity_view_rotation);
        super.onConfigurationChanged(newConfig);
    }
}
