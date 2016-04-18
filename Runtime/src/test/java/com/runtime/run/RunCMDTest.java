package com.runtime.run;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wanglei25 on 2016/4/15.
 */
public class RunCMDTest {

    @Test
    public void testRun()  {
        String cmd = "oracle -vh";
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputStream = process.getInputStream();
        InputStream errStream = process.getErrorStream();
        String err = getInputResult(errStream).toString();
        String info = getInputResult(inputStream).toString();
        System.out.println("err:"+ err);
        System.out.println("result:"+info);
    }

    private StringBuffer getInputResult(InputStream inputStream){
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bytes = new byte[1024];
        try {
            for (int n;(n=inputStream.read(bytes))!=-1;){
                stringBuffer.append(new String(bytes,0,n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }

}
