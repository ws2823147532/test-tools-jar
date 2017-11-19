package com.tools.jar;


import java.util.Arrays;
import java.util.Date;

/**
 * @author wangshang
 */
public class RunDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Arrays.toString(args));
        boolean flag = true;
        int c = 0;
        while (flag) {
            Date date = new Date();
            System.out.println(date);
            Thread.sleep(1000);
            c++;
            if (c > 1000) {
                flag = false;
            }
        }
    }
}
