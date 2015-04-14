package com.selector;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * Created by wang on 15-3-26.
 */
public class MoreSelector {
    public static void main(String[] args) throws IOException, InterruptedException {
        Selector[] selectors = new Selector[200];
        for ( int i=0;i<200;i++){
            selectors[i] = Selector.open();
        }
        Thread.sleep(30000);
    }
}
