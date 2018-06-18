package com.example.zq.bingfang_pro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_one;
    private EditText edit;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
       // super.onSaveInstanceState(outState);
        getDelegate().onSaveInstanceState(outState);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.connectserver);
        initViews();
    }
    private void initViews() {
        btn_one = (Button) findViewById(R.id.button1);
        btn_one.setOnClickListener(this);
        edit = (EditText) findViewById(R.id.port);
    }
    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "你点击了按钮", Toast.LENGTH_SHORT).show();
        int c = Integer.parseInt(edit.getText().toString());
        if(c<=0||c>65553)
            { Toast.makeText(getApplicationContext(), "端口输入错误！请重新输入！", Toast.LENGTH_SHORT).show();
                return;
             }

        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(this,zeroActivity.class));
                break;
        }
    }





}
