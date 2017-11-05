package com.user.p0571_gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    String[] data = {"a","b","c","d","e","f","h","k","l","m","n","o","p","q","r"};
    GridView gvMain;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new ArrayAdapter<String>(this,R.layout.item,R.id.tvText,data);
        gvMain = (GridView)findViewById(R.id.gvMain);
        gvMain.setAdapter(adapter);
        adjustGridView();
    }

    private void adjustGridView(){
        gvMain.setNumColumns(GridView.AUTO_FIT);
        gvMain.setColumnWidth(200);
        gvMain.setVerticalSpacing(5);
        gvMain.setHorizontalSpacing(5);
        gvMain.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);
    }
}
