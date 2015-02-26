package com.bit;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * Created by wanglei on 15-2-26.
 */
public class BitCal {
    @Test
    public void testBit(){
        String source="F1";
        int flag=0;
        // String to buffer
        ByteBuffer buffer=string2Buffer(source);
        while (buffer.position() < buffer.limit()){
            // format to unsigned byte
            flag= buffer.get() & 0x0ff;
        }
        System.out.println("16 hex:"+source);
        System.out.println("int value:"+flag);
        System.out.println("int binary:"+Integer.toBinaryString(flag));
        System.out.println("high binary:"+Integer.toBinaryString(flag>>4));
        System.out.println("high int value:"+(flag>>4));
        System.out.println("low binary:"+Integer.toBinaryString(flag & 0x0f));
        System.out.println("low int value:"+(flag & 0x0f));
        System.out.println((flag>>4) == 0x0f);
        System.out.println("=======");
        System.out.println(72&0x0f);
        System.out.println(Integer.toBinaryString(72&0x0f));
        byte bb= (byte) 125;
        System.out.println(bb);

    }

    public ByteBuffer string2Buffer(String source){
		ByteBuffer buffer = ByteBuffer.allocate(source.length() / 2);
		for (int idx = 0; idx < buffer.capacity(); idx++) {
			int byteValue = Integer.parseInt(source.substring(idx * 2, idx * 2 + 2), 16);
			buffer.put((byte) byteValue);
		}
		buffer.position(0);
		return buffer;
    }
}
