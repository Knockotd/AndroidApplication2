package com.tistory.simpleisbest.a1010application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class GridViewPractice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_practice);

        GridView gridView = (GridView)findViewById(R.id.gridView);
        ImegeAdapter adapter = new ImegeAdapter(this);
        gridView.setAdapter(adapter);
    }
}
