package com.example.zq.bingfang_pro;

import android.os.Message;
import android.util.Log;
import com.yuhao.packet.DataPacket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * Connection
 *
 * @author CYH
 * @date 2018/6/19
 */
public class Connection extends Thread{

    public static SocketChannel client;
    private SocketAddress address;
    private MsgHandler handler;



    public Connection(SocketAddress address,MsgHandler handler){
        this.address=address;
        this.handler=handler;

    }

    @Override
    public void run(){
        try {
            client = SocketChannel.open(address);
            client.configureBlocking(false);
            Log.e("yuhao","连接成功！！！！");
            ByteBuffer buffer=ByteBuffer.allocate(1024);

            buffer.clear();

            DataPacket dataPacket=new DataPacket();
            dataPacket.setContent("connect");
            dataPacket.setCode(7);
            dataPacket.setSendTime(new Date());

            ByteArrayOutputStream bytesOut=new ByteArrayOutputStream();
            ObjectOutputStream ojbOut=new ObjectOutputStream(bytesOut);
            ojbOut.writeObject(dataPacket);
            ojbOut.close();

            buffer.put(bytesOut.toByteArray());
            bytesOut.close();
            buffer.flip();
            client.write(buffer);
            buffer.clear();

            int index=1000;
            while (true) {
                long count = client.read(buffer);

                if (count > 0) {
                    buffer.flip();
                    byte[] b = new byte[buffer.limit()];
                    buffer.get(b, buffer.position(), buffer.limit());

                    ByteArrayInputStream byteIn = new ByteArrayInputStream(b);
                    ObjectInputStream objIn = new ObjectInputStream(byteIn);
                    DataPacket result = null;
                    try {
                        result = (DataPacket) objIn.readObject();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (result!=null&&result.getCode().equals(200)){
                        //连接设备成功
                        Message message=new Message();
                        message.obj=result.getCode();
                        handler.sendMessage(message);
                    }else {
                        //连接设备失败
                        Message message=new Message();
                        message.obj=400;
                        handler.sendMessage(message);
                    }
                    objIn.close();
                    byteIn.close();
                    break;
                }
                index--;
                if (index<0){
                    Message message=new Message();
                    message.obj=400;
                    handler.sendMessage(message);
                    break;
                }
            }
        } catch (IOException e) {
            Message message=new Message();
            message.obj=400;
            handler.sendMessage(message);
            e.printStackTrace();
        }

    }

}
