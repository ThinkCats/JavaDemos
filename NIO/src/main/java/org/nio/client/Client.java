package org.nio.client;

import org.nio.bean.Request;
import org.nio.bean.Response;
import org.nio.serialize.SerializableUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by wang on 15-4-16.
 */
public class Client {
        public static void main(String[] args) {
                for (int i=0;i<4000;i++){
                        final int idx = i;
                        new Thread(new MyRunnable(idx)).start();
                }
        }

        private static final class  MyRunnable implements Runnable{

                private final int idx;

                public MyRunnable(int idx) {
                        this.idx = idx;
                }

                @Override
                public void run() {
                        SocketChannel socketChannel = null;
                        try {
                                socketChannel = SocketChannel.open();
                                SocketAddress socketAddress = new InetSocketAddress("127.0.0.1",8888);
                                socketChannel.connect(socketAddress);

                                //Send data
                                Request request = new Request("request_"+idx,"value_"+idx);
                                System.out.println("[INFO :] Send message");
                                sendData(socketChannel,request);

                                //Get data
                                System.out.println("[INFO :] Begin REC Response");
                                Response response = receiveData(socketChannel);
                                System.out.println("[Client :]"+response.toString());

                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }

                private void sendData(SocketChannel socketChannel,Request request) throws IOException {
                        byte[] bytes = SerializableUtil.toBytes(request);
                        ByteBuffer buffer = ByteBuffer.wrap(bytes);
                        socketChannel.write(buffer);
                        socketChannel.socket().shutdownOutput();
                }

                private Response receiveData(SocketChannel channel) throws IOException {
                        Response response = null;
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        byte[] bytes;
                        int count = 0;
                        while ((count=channel.read(buffer))>=0){
                                buffer.flip();
                                bytes = new  byte[count];
                                buffer.get(bytes);
                                baos.write(bytes);
                                buffer.clear();
                        }
                        bytes = baos.toByteArray();
                        response = (Response) SerializableUtil.toObject(bytes);
                        channel.socket().shutdownInput();
                        return response;
                }
        }
}
