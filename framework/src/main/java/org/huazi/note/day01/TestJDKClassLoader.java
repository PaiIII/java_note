package org.huazi.note.day01;


import sun.misc.Launcher;

import java.net.URL;

/**
 * 类加载器:引导类加载器、扩展类加载、程序应用类加载器
 *
 * @author huazi
 * @date 2022/2/19 11:41
 */
public class TestJDKClassLoader {
    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());//null 引导类加载器，C++实现，JAVA显示为null
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader());//ExtClassLoader 扩展类加载器
        System.out.println(TestJDKClassLoader.class.getClassLoader());//AppClassLoader 应用程序加类加载器
        System.out.println("~~~~~~~~~~~~~~~分隔符~~~~~~~~~~~~~~~~~~~~~~~");

        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(appClassLoader);
        System.out.println(extClassLoader);
        System.out.println(bootstrapClassLoader);

        System.out.println("bootstrapLoader加载以下文件：jre/lib");
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i]);
        }

        System.out.println("extClassloader加载以下文件：lib/ext");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println("appClassloader加载以下文件：lib/ext");
        System.out.println(System.getProperty("java.class.path"));
    }
}
