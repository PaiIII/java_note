package org.huazi.note.day01;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * 自定义类加载器:打破双亲委派机制
 * 同一个JVM内，两个相同包名和类名的类对象可以共存，因为他们的类加载器可以不一样，所以看两个类对象是否是同一个，
 * 除了看类的包名和类名是否都相同之外，还需要他们的类加载器也是同一个才能认为他们是同一个。
 *
 * @author huazi
 * @date 2022/2/21 10:16
 */
public class MyClassLoaderTestTwo {
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

        @SneakyThrows
        @Override
        protected Class<?> loadClass(String name, boolean resolve) {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    //非自定义类，走双亲委派机制
                    if (!name.startsWith("org.huazi.note.common")) {
                        c = this.getParent().loadClass(name);
                    } else {
                        c = findClass(name);
                    }
                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        MyClassLoader classLoader = new MyClassLoader("E:/test");
        //注意：需要删除项目里的User1类(因为会委托上级加载器，加载到了User1，就会使AppClassLoader)
        Class clazz = classLoader.loadClass("org.huazi.note.common.User1");//java.lang.String 会报错。包含核心API
        Object obj = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("sout", null);
        method.invoke(obj, null);
        System.out.println(clazz.getClassLoader().getClass().getName());
        System.out.println("**********分隔符*********");
        MyClassLoader classLoader1 = new MyClassLoader("E:/test1");
        Class clazz1 = classLoader1.loadClass("org.huazi.note.common.User1");
        Object obj1 = clazz1.newInstance();
        Method method1 = clazz1.getDeclaredMethod("sout", null);
        method1.invoke(obj1, null);
        System.out.println(clazz1.getClassLoader().getClass().getName());
        //*********************load User******************
        //=======自己的加载器加载类调用方法=======
        //org.huazi.note.day01.MyClassLoaderTestTwo$MyClassLoader
        //**********分隔符*********
        //*********************load User******************
        //另一个版本=======自己的加载器加载类调用方法=======
        //org.huazi.note.day01.MyClassLoaderTestTwo$MyClassLoader
    }


}
