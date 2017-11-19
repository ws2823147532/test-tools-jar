package com.tools.jar.jmap;

import sun.tools.jmap.JMap;

/**
 * 不用 Jmap
 * Jmap demo
 */
public class JmapDemo {

    public static void main(String[] args) throws Exception {

        JMap.main(new String[]{"-heap", "6712"});

    }
}
