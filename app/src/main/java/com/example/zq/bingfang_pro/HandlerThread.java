package com.example.zq.bingfang_pro;

import android.os.Message;
import com.yuhao.packet.DataPacket;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * HandlerThread
 *
 * @author CYH
 * @date 2018/6/19
 */
public class HandlerThread extends Thread{


    private SocketChannel client;

    private MyHandler myHandler;


    public HandlerThread(SocketChannel client,MyHandler myHandler){
        this.client=client;
        this.myHandler=myHandler;
    }


    @Override
    public void run(){

        ByteBuffer buffer=ByteBuffer.allocate(1024);
        while (true) {
            buffer.clear();
            long count = 0;
            try {
                count = client.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (count > 0) {
                buffer.flip();
                byte[] b = new byte[buffer.limit()];
                buffer.get(b, buffer.position(), buffer.limit());

                ByteArrayInputStream byteIn = new ByteArrayInputStream(b);
                ObjectInputStream objIn = null;
                try {
                    objIn = new ObjectInputStream(byteIn);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                DataPacket result = null;
                try {
                    result = (DataPacket) objIn.readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //处理指令
                if (result!=null){
                    handleData(result);
                }


                try {
                    objIn.close();
                    byteIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }


    private void handleData(DataPacket dataPacket){

        Message message=new Message();
        message.obj=dataPacket;

        myHandler.sendMessage(message);

    }





}
