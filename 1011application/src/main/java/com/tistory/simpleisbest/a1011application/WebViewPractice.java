package com.tistory.simpleisbest.a1011application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class WebViewPractice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_practice);

        //레이아웃 파일에 있는 View 객체 모두 찾아오기
        final EditText editText = (EditText)findViewById(R.id.addr);
        Button localBtn = (Button)findViewById(R.id.localBtn);
        Button urlBtn = (Button)findViewById(R.id.urlBtn);
        final WebView webView = (WebView)findViewById(R.id.webView);

        //버튼의 클릭 이벤트를 처리할 Listener 객체를 생성
        View.OnClickListener btnEventHandler = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.localBtn:
                        //로컬에 있는 파일 읽기
                        webView.loadUrl("file:///android_asset/test.html");
                        break;
                    case R.id.urlBtn:
                        webView.loadUrl(editText.getText().toString());
                        break;
                }
            }
        };

        //리다이렉트되는 URL을 요청할 때 WebView가 처리하도록 설정
        webView.setWebViewClient(new WebViewClient());

        //자바스크립트 사용과 확대 축소를 사용할 수 있도록 설정
        WebSettings set = webView.getSettings();
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);

        //웹 뷰를 이용해서 외부 URL 읽기
        webView.loadUrl("https://www.apple.com");

        //버튼에 리스너 연결
        localBtn.setOnClickListener(btnEventHandler);
        urlBtn.setOnClickListener(btnEventHandler);
    }
}
