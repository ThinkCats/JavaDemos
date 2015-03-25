package com.redis.subs;

import java.io.Serializable;

/**
 * Created by wang on 15-3-5.
 */
public class Bean implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
