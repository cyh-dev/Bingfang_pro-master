package com.example.zq.bingfang_pro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class zeroActivity extends AppCompatActivity {
    private ViewPager vpager_zero;
    private ArrayList<View> aList;
    private MyPagerAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cg);
        vpager_zero = (ViewPager) findViewById(R.id.vpager_zero);
        aList = new ArrayList<View>();
        LayoutInflater li = getLayoutInflater();

       // aList.add(li.inflate(R.layout.cg,null,false));
        aList.add(li.inflate(R.layout.activity_bingfang,null,false));
        aList.add(li.inflate(R.layout.activity_jiating, null, false));
        aList.add(li.inflate(R.layout.activity_main, null, false));

        mAdapter = new MyPagerAdapter(aList);
        vpager_zero.setAdapter(mAdapter);


    }
    public void onClick1(View view) {
        Toast.makeText(getApplicationContext(), "你点击了按钮1", Toast.LENGTH_SHORT).show();
    }
    public void onClick2(View view) {
        Toast.makeText(getApplicationContext(), "你点击了按钮2", Toast.LENGTH_SHORT).show();
    }
    public void onClick3(View view) {
        Toast.makeText(getApplicationContext(), "你点击了按钮3", Toast.LENGTH_SHORT).show();
    }


}