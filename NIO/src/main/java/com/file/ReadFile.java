package com.file;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by wang on 15-3-25.
 */
public class ReadFile {
    @Test
    public void readFile(){
        try {
            RandomAccessFile file = new RandomAccessFile("/home/wang/1D","rw");
            FileChannel channel = file.getChannel();

            ByteBuffer buffer =ByteBuffer.allocate(1024);
            try {
                int bytesRead = channel.read(buffer);
                System.out.println("data buffer length:"+bytesRead);
                while (bytesRead != -1){
                    buffer.flip();
                    buffer.hasRemaining();
                    while (buffer.hasRemaining()){
                        System.out.print((char) buffer.get());
                    }
                    System.out.println("begin clear buffer for read continue");
                    buffer.clear();
                    bytesRead = channel.read(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("begin close file");
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
