package com.design;

/**
 * @author WangLei
 * on 2018/2/5
 */
public class BuilderFacts {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;

    public static class Builder {
        private final int a;
        private final int b;

        private int c = 0;
        private int d = 0;
        private int e = 0;
        private int f = 0;

        public Builder(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public Builder c(int val) {
            c = val;
            return this;
        }

        public Builder d(int val) {
            d = val;
            return this;
        }

        public Builder e(int val) {
            e = val;
            return this;
        }

        public Builder f(int val) {
            f = val;
            return this;
        }

        public BuilderFacts build() {
            return new BuilderFacts(this);
        }
    }

    private BuilderFacts(Builder builder) {
        a = builder.a;
        b = builder.b;
        c = builder.c;
        d = builder.d;
        e = builder.e;
        f = builder.f;
    }

    @Override
    public String toString() {
        return "BuilderFacts{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                ", e=" + e +
                ", f=" + f +
                '}';
    }
}
