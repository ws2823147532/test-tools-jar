package com.tools.jar.models;

/**
 * 原工具 Jps.main(args);
 *
 * @author wangshang
 */
public class JpsDemo {


    public static void main(String[] args) {
        args = new String[]{"-v", "-m", "-l", "-V"};

        JpsCollections jpsCollections = JpsUtil.execute(args);

        System.out.println(jpsCollections);
    }
}
