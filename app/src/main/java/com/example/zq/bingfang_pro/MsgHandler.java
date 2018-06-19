package com.example.zq.bingfang_pro;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

/**
 * MsgHandler
 *
 * @author CYH
 * @date 2018/6/19
 */
public class MsgHandler extends Handler {

    private Button connection;

    private AppCompatActivity main;


    public MsgHandler(Button connection,AppCompatActivity main) {

        this.connection=connection;
        this.main=main;

    }

    @Override
    public void handleMessage(Message msg) {

        connection.setEnabled(true);

       int code= (int) msg.obj;
       if (code==200){

           Toast.makeText(main.getApplicationContext(), "连接成功！", Toast.LENGTH_SHORT).show();

           MyHandler myHandler=new MyHandler();
           HandlerThread handlerThread=new HandlerThread(Connection.client,myHandler);
           handlerThread.start();


           main.startActivity(new Intent(main,zeroActivity.class));
       }else {
           Toast.makeText(main.getApplicationContext(), "连接失败！", Toast.LENGTH_SHORT).show();
       }



    }






}
