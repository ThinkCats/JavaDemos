package com.selector;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * Created by wang on 15-3-26.
 */
public class SelectorSocket {

        private int PORT = 9999;
        private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        @Test
        public void selectSocket() throws IOException, InterruptedException {
                System.out.println("listening on port " + PORT);
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.configureBlocking(false);

                ServerSocket serverSocket = serverSocketChannel.socket();
                serverSocket.bind(new InetSocketAddress(PORT));

                Selector selector = Selector.open();
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

                while (true) {
                        int n = selector.select();
                        if (n == 0) {
                                continue;
                        }

                        Iterator iterator = selector.selectedKeys().iterator();
                        while (iterator.hasNext()) {
                                SelectionKey key = (SelectionKey) iterator.next();
                                if (key.isAcceptable()) {
                                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                                        SocketChannel socketChannel = server.accept();
                                        registeChannel(selector, socketChannel, SelectionKey.OP_READ);
                                        sayHello(socketChannel);
                                } else if (key.isReadable()) {
                                        System.out.println("---------- ready to read -----------");
                                        readDateFromSocket(key);
                                }
                                iterator.remove();
                        }
                }
        }

        private void registeChannel(Selector selector, SelectableChannel channel, int ops) throws IOException {
                channel.configureBlocking(false);
                channel.register(selector, ops);
        }

        private void sayHello(SocketChannel channel) throws IOException {
                byteBuffer.clear();
                byteBuffer.put("Hello , You Have Connected on Server !\r\n".getBytes());
                byteBuffer.flip();
                channel.write(byteBuffer);
        }

        private void readDateFromSocket(SelectionKey key) throws IOException {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                Socket socket = socketChannel.socket();
                int count;
                byteBuffer.clear();
                while ((count = socketChannel.read(byteBuffer)) > 0) {
                        System.out.println("the buffer size is > 0");
                        byteBuffer.flip();
                        while (byteBuffer.hasRemaining()) {
                                System.out.print((char) byteBuffer.get());
                        }
                        System.out.println(" on Port :" + socket.getPort());
                        byteBuffer.clear();
                }
                if (count < 0) {
                        System.out.println("begin close socket channel");
                        socketChannel.close();
                        System.out.println("close socket success");
                }
        }

        private void writeDataToSocket(SelectionKey key,String msg) throws IOException {
                SocketChannel channel = (SocketChannel) key.channel();
                byteBuffer.put(msg.getBytes());
                byteBuffer.flip();
                channel.write(byteBuffer);
        }
}
