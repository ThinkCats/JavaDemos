package org.nio.serialize;

import java.io.*;

/**
 * Created by wang on 15-4-16.
 */
public class SerializableUtil {

        public static byte[] toBytes(Object object){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = null;
                try {
                        oos = new ObjectOutputStream(baos);
                        oos.writeObject(object);
                        return baos.toByteArray();
                } catch (IOException e) {
                        e.printStackTrace();
                } finally {
                        if (oos != null) {
                                try {
                                        oos.close();
                                        baos.close();
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                        }
                }
                return null;
        }

        public static Object toObject(byte[] bytes){
                ByteArrayInputStream bas = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = null;
                try {
                        ois = new ObjectInputStream(bas);
                        try {
                                return ois.readObject();
                        } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return null;
        }
}
