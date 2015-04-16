package com.test;

import org.junit.Test;

/**
 * Created by wang on 15-4-14.
 */
public class NullTest {
        @Test
        public void testNull(){
                Man man = new Man("wang");
                int i = man.getAge();
                System.out.println(i);
        }

        @Test
        public void mod(){
                System.out.println(2- (254%255));
        }
}

class  Man{
        private String name ;
        private Integer age;

        public Man(String name) {
                this.name = name;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Integer getAge() {
                return age;
        }

        public void setAge(Integer age) {
                this.age = age;
        }
}
