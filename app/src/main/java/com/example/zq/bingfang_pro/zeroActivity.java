package com.example.zq.bingfang_pro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.yuhao.packet.DataPacket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;



public class zeroActivity extends AppCompatActivity {
    private ViewPager vpager_zero;
    private ArrayList<View> aList;
    private MyPagerAdapter mAdapter;

    private static  EditText Jspo2;

    private static EditText Spm;
    private static EditText Sspo2;

    private static EditText Ben;
    private static EditText Bpm;
    private static EditText Bspo2;





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


        Jspo2= (EditText) aList.get(1).findViewById(R.id.j_spo2);

        Spm= (EditText) aList.get(2).findViewById(R.id.s_pm);
        Sspo2= (EditText) aList.get(2).findViewById(R.id.s_spo2);

        Ben= (EditText) aList.get(0).findViewById(R.id.b_en);
        Bpm= (EditText) aList.get(0).findViewById(R.id.b_pm);
        Bspo2= (EditText) aList.get(0).findViewById(R.id.b_spo2);


    }

    public static void setJspo2(String data){

        Jspo2.setText(data);

    }

    public static void setSpm(String data){

        Spm.setText(data);

    }

    public static void setSspo2(String data){

        Sspo2.setText(data);

    }

    public static void setBen(String data){

        Ben.setText(data);

    }
    public static void setBpm(String data){

        Bpm.setText(data);

    }

    public static void setBspo2(String data){

        Bspo2.setText(data);

    }





    public void onClick1(View view) {


        if (!"".equals(Ben.getText().toString())){
            DataPacket data=new DataPacket();
            data.setContent(Ben.getText().toString());
            data.setCode(6);
            data.setSendTime(new Date());
            sendData(data);
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!"".equals(Bspo2.getText().toString())){
            DataPacket data=new DataPacket();
            data.setContent(Bspo2.getText().toString());
            data.setCode(4);
            data.setSendTime(new Date());
            sendData(data);
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!"".equals(Bpm.getText().toString())){
            DataPacket data=new DataPacket();
            data.setContent(Bpm.getText().toString());
            data.setCode(5);
            data.setSendTime(new Date());
            sendData(data);
        }


    }
    public void onClick2(View view) {

        if (!"".equals(Jspo2.getText().toString())){
            DataPacket data=new DataPacket();
            data.setContent(Jspo2.getText().toString());
            data.setCode(1);
            data.setSendTime(new Date());
            sendData(data);
        }

    }

    public void onClick3(View view) {

        if (!"".equals(Spm.getText().toString())){
            DataPacket data=new DataPacket();
            data.setContent(Spm.getText().toString());
            data.setCode(3);
            data.setSendTime(new Date());
            sendData(data);
        }

        if (!"".equals(Sspo2.getText().toString())){
            DataPacket data=new DataPacket();
            data.setContent(Sspo2.getText().toString());
            data.setCode(2);
            data.setSendTime(new Date());
            sendData(data);
        }

    }

    private void sendData(final DataPacket dataPacket){

        new Thread(){

            @Override
            public void run(){
                ByteBuffer buffer=ByteBuffer.allocate(1024);
                buffer.clear();
                ByteArrayOutputStream bytesOut=new ByteArrayOutputStream();
                ObjectOutputStream ojbOut= null;
                try {
                    ojbOut = new ObjectOutputStream(bytesOut);
                    ojbOut.writeObject(dataPacket);
                    ojbOut.close();

                    buffer.put(bytesOut.toByteArray());
                    bytesOut.close();
                    buffer.flip();
                    Connection.client.write(buffer);
                    buffer.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start();

    }



    @Override
    public void onBackPressed() {

        new Thread(){
            @Override
            public void run(){
                ByteBuffer buffer=ByteBuffer.allocate(1024);
                buffer.clear();
                DataPacket dataPacket=new DataPacket();
                dataPacket.setContent("disconnect");
                dataPacket.setCode(8);
                dataPacket.setSendTime(new Date());
                ByteArrayOutputStream bytesOut=new ByteArrayOutputStream();
                ObjectOutputStream ojbOut= null;
                try {
                    ojbOut = new ObjectOutputStream(bytesOut);
                    ojbOut.writeObject(dataPacket);
                    ojbOut.close();

                    buffer.put(bytesOut.toByteArray());
                    bytesOut.close();
                    buffer.flip();
                    Connection.client.write(buffer);
                    buffer.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Connection.client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start();



        super.onBackPressed();//注销该方法，相当于重写父类这个方法

    }


}