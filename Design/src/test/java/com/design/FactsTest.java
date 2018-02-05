package com.design;

import org.junit.Test;

/**
 * @author WangLei
 * on 2018/2/5
 */
public class FactsTest {
    @Test
    public void testBuilder() {
        BuilderFacts.Builder builder = new BuilderFacts.Builder(10, 20);
        BuilderFacts facts = builder.e(30).f(40).c(22).build();
        System.out.println(facts.toString());
    }
}
