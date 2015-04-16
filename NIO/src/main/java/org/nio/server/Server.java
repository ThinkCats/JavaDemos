package org.nio.server;

import org.nio.bean.Request;
import org.nio.bean.Response;
import org.nio.serialize.SerializableUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by wang on 15-4-16.
 */
public class Server {

        public static void main(String[] args) {
                Selector selector = null;
                ServerSocketChannel serverSocketChannel = null;

                try {
                        //selector for incoming time request
                        selector = Selector.open();

                        //create a new server socket and set it as non blocking
                        serverSocketChannel = ServerSocketChannel.open();
                        serverSocketChannel.configureBlocking(false);

                        //bind socket
                        serverSocketChannel.socket().setReuseAddress(true);
                        serverSocketChannel.socket().bind(new InetSocketAddress(8888));

                        // Register accepts on the server socket with the selector. This
                        // step tells the selector that the socket wants to be put on the
                        // ready list when accept operations occur, so allowing multiplexed
                        // non-blocking I/O to take place.
                        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

                        // Here's where everything happens. The select method will
                        // return when any operations registered above have occurred, the
                        // thread has been interrupted, etc.
                        while (selector.select()>0){
                                // Someone is ready for I/O, get the ready keys
                                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                                // Walk through the ready keys collection and process date requests.
                                while (iterator.hasNext()){

                                        //Get key and  then remove this iterator
                                        SelectionKey readyKey = iterator.next();
                                        iterator.remove();

                                        //Do something
                                        execute((ServerSocketChannel) readyKey.channel());
                                }
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                } finally {
                        if (selector != null) {
                                try {
                                        selector.close();
                                } catch (IOException e) {
                                        e.printStackTrace();
                                } finally {
                                        if (serverSocketChannel != null) {
                                                try {
                                                        serverSocketChannel.close();
                                                } catch (IOException e) {
                                                        e.printStackTrace();
                                                }
                                        }
                                }
                        }
                }

        }

        private static void execute(ServerSocketChannel channel) {
                SocketChannel socketChannel = null;
                try {
                        socketChannel = channel.accept();

                        System.out.println("[Info :]" + socketChannel.socket().getRemoteSocketAddress());

                        //Get datas
                        Request request = receiveData(socketChannel);
                        System.out.println("[Rcvd :]"+request.toString());

                        //Send data
                        Response response = new Response("user:"+request.getName(),"hello world +"+request.getValue());
                        sendData(socketChannel,response);

                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        private static Request receiveData(SocketChannel socketChannel){
                Request request = null;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                byte[] bytes ;
                int size = 0;
                try {
                        while ((size = socketChannel.read(byteBuffer))>0){
                                byteBuffer.flip();
                                bytes = new byte[size];
                                // Push bytebuffer data to bytes
                                byteBuffer.get(bytes);
                                baos.write(bytes);
                                byteBuffer.clear();
                        }
                        bytes = baos.toByteArray();
                        Object obj = SerializableUtil.toObject(bytes);
                        request = (Request) obj;
                } catch (IOException e) {
                        e.printStackTrace();
                }
                finally {
                        try {
                                baos.close();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
                return request;
        }

        private static void sendData(SocketChannel socketChannel ,Response response) throws IOException {
                byte[] bytes = SerializableUtil.toBytes(response);
                ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
                socketChannel.write(byteBuffer);
        }

}
