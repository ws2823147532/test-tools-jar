package com.tools.jar;

/**
 * @author wangshang
 */
public class RunDemo {
    public static void main(String[] args) throws InterruptedException {
        boolean flag = true;
        int c = 0;
        while (flag) {
            System.out.println(c);
            Thread.sleep(1000);
            c++;
            if (c > 1000) {
                flag = false;
            }
        }
    }
}
