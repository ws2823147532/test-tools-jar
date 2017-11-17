package com.tools.jar;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class PermGenOomMock {
    public static void main(String[] args) {
        URL url;
        List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();
        try {
            url = new File("D:\\workspace\\test-tools-jar\\tmp").toURI().toURL();
            URL[] urls = {url};
            int c = 0;
            while (true) {
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                loader.loadClass("com.tools.jar.Test");
                System.out.println(++c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}