package com.example.zq.bingfang_pro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.yuhao.packet.DataPacket;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_one;
    private EditText port;
    private EditText ip;
    private SocketAddress address;
    private MsgHandler handler;


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
        port = (EditText) findViewById(R.id.port);
        ip=(EditText) this.findViewById(R.id.ip);

        handler=new MsgHandler(btn_one,this);
    }


    @Override
    public void onClick(View v) {

        int c=-1;
        try {
             c = Integer.parseInt(port.getText().toString());
        }catch (Exception e){
            e.printStackTrace();

        }

        if(c<=0||c>65553)
            {
                Toast.makeText(getApplicationContext(), "端口输入错误！请重新输入！", Toast.LENGTH_SHORT).show();
                return;
             }


        if (v.getId()==R.id.button1){

            address=new InetSocketAddress(ip.getText().toString(),c);

            //启动连接线程
            Connection connection=new Connection(address,handler);
            connection.start();


            btn_one.setEnabled(false);

        }


    }



}
