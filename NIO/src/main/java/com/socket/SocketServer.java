package com.socket;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by wang on 15-3-25.
 */
public class SocketServer {
    @Test
    public void server(){
        try {
            ServerSocketChannel serverSocketChannel =ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(9999));
            serverSocketChannel.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(2048);
            buffer.clear();
            while (true){
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null){
                    System.out.println("Client :"+socketChannel.socket().getInetAddress().getCanonicalHostName());
                    int bytesRead = socketChannel.read(buffer);
                    while (bytesRead != -1){
                        buffer.flip();
                        while (buffer.hasRemaining()){
                            System.out.print((char)buffer.get());
                        }
                        buffer.clear();
                        socketChannel.read(buffer);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void clientSocket(){
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1",9999));

            while (!socketChannel.finishConnect()){
                System.out.println("Connecting...");
            }

            System.out.println("Connected ");
            //send message
            socketChannel.write(ByteBuffer.wrap("hello world".getBytes()));
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
