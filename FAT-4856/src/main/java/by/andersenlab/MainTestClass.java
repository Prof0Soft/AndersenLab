package by.andersenlab;

import java.lang.reflect.InvocationTargetException;

public class MainTestClass {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader;

        while (true) {
            loader = new DynamicClassOverload(new String[]{".", "src\\main\\java"});
            Class clazz = Class.forName("by.andersenlab.TestLoadModule", true, loader);

            try {
                StaticClassForLoad stCls;
                stCls = (StaticClassForLoad) clazz.getDeclaredConstructor().newInstance();
                System.out.println(stCls);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                    NoSuchMethodException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
