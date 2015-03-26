package com.buffer;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * Created by wanglei on 15-3-25.
 */
public class ByteToCharBuffer {
    @Test
    public void byteBufferToCharBuffer(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(9);
        CharBuffer charBuffer = byteBuffer.asCharBuffer();

        //put something to bytebuffer
        byteBuffer.put(0,(byte)0);
        byteBuffer.put(1,(byte)'H');
        byteBuffer.put(2,(byte)0);
        byteBuffer.put(3,(byte)'I');
        byteBuffer.put(4,(byte)0);
        byteBuffer.put(5,(byte)'!');
        byteBuffer.put(6,(byte)0);

        System.out.println(byteBuffer);
        System.out.println(charBuffer);
    }
}
