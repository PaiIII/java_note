package org.huazi.note.day01;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * 自定义类加载器:实现双亲委派机制：加载其他路径下的类
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
                e.printStackTrace();
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
