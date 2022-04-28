package org.huazi.note.day01;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * 自定义类加载器:实现双亲委派机制：加载其他路径下的类：重写findClass方法
 * <p>
 * 沙箱安全机制：自己写的java.lang.String.class类不会被加载，这样便可以防止核心API库被随意篡改
 * 避免类的重复加载：当父亲已经加载了该类时，就没有必要子ClassLoader再加载一次，保证被加载类的唯一性
 * https://www.cnblogs.com/enroute/p/13865807.html
 *
 * @author huazi
 * @date 2022/2/21 10:16
 */
public class MyClassLoaderTest {
    static class MyClassLoader extends ClassLoader {
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        @SneakyThrows
        private byte[] loadByte(String name) {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
            byte[] data = new byte[fis.available()];
            fis.read(data);
            fis.close();
            return data;
        }

        @SneakyThrows
        @Override
        protected Class<?> findClass(String name) {
            try {
                byte[] data = loadByte(name);
                //defineClass将一个字节数组转为class对象，这个字节数组是class文件读取后最终的字节数组
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                throw new ClassNotFoundException();
            }
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        MyClassLoader classLoader = new MyClassLoader("E:/test");
        //注意：需要删除项目里的User1类(因为会委托上级加载器，使用AppClassLoader加载到了classPath路径下User1)
        Class clazz = classLoader.loadClass("org.huazi.note.common.User1");
        Object obj = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("sout", null);
        method.invoke(obj, null);
        System.out.println(clazz.getClassLoader().getClass().getName());
        //=======自己的加载器加载类调用方法=======
        //org.huazi.note.day01.MyClassLoaderTest$MyClassLoader
    }


}
