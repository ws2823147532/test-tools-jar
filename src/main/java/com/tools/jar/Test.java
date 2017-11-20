package com.tools.jar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");

        System.out.println(simpleDateFormat.format(new Date()));
    }
}