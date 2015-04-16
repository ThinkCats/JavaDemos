package org.nio.bean;

import java.io.Serializable;

/**
 * Created by wang on 15-4-16.
 */
public class Response implements Serializable{
        private String name;
        private String reply;
        private byte[] bytes;

        public Response(String name, String reply) {
                this.name = name;
                this.reply = reply;
                this.bytes = new  byte[1024];
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getReply() {
                return reply;
        }

        public void setReply(String reply) {
                this.reply = reply;
        }

        @Override
        public String toString() {
                return "Response{" +
                        "name='" + name + '\'' +
                        ", reply='" + reply + '\'' +
                        ", bytes length =" + bytes.length +
                        '}';
        }
}
