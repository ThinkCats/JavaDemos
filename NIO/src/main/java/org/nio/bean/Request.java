package org.nio.bean;

import java.io.Serializable;

/**
 * Created by wang on 15-4-16.
 */
public class Request implements Serializable {
        private String name;
        private String value;
        private byte[] bytes;

        public Request(String name, String value) {
                this.name = name;
                this.value = value;
                this.bytes = new byte[1024];
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getValue() {
                return value;
        }

        public void setValue(String value) {
                this.value = value;
        }

        @Override
        public String toString() {
                return "Request{" +
                        "name='" + name + '\'' +
                        ", value='" + value + '\'' +
                        ", bytes length =" + bytes.length +
                        '}';
        }
}
