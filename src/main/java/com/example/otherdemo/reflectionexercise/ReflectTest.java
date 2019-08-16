package com.example.otherdemo.reflectionexercise;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;

/**
 * @author shj
 * 反射的基本用法
 * 参考  https://baijiahao.baidu.com/s?id=1619748187138646880&wfr=spider&for=pc
 */
public class ReflectTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        //加载Class对象
        Class c = Class.forName("com.example.otherdemo.reflectionexercise.Student");


System.out.println("===================================================================================================");

        System.out.println("==============获取所有公用的构造方法==============");
        Constructor[] constructors = c.getConstructors();
        for(Constructor constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("==============获取所有构造方法===============");
        Constructor[] declaredConstructors = c.getDeclaredConstructors();
        for(Constructor declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }

        System.out.println("==============获取公有&无参的构造方法=============");
        Constructor constructor = c.getConstructor(null);
        System.out.println(constructor);

        System.out.println("==============获取公有&有参的构造方法=============");
        Constructor constructor1 = c.getConstructor(String.class, int.class, String.class, Instant.class);
        System.out.println(constructor1);

        System.out.println("==============获取私有&有参的构造方法=============");
        Constructor declaredConstructor1 = c.getDeclaredConstructor(String.class, int.class);

        //暴力反射，只有将属性设置为true才可以创建对象
        declaredConstructor1.setAccessible(true);
        Student student = (Student) declaredConstructor1.newInstance("张三", 18);

        System.out.println(declaredConstructor1);


System.out.println("===================================================================================================");

        System.out.println("==============获取所有的公共字段==============");
        Field[] fields = c.getFields();
        for(Field field : fields) {
            System.out.println(field);
        }

        System.out.println("==============获取所有的字段(公有&私有)=======");
        Field[] declaredFields = c.getDeclaredFields();
        for(Field field : declaredFields) {
            System.out.println(field);
        }


        System.out.println("==============获取公有字段并使用==============");
        Field field = c.getField("age");
        //获取一个公有构造方法并实例化
        Object obj = c.getConstructor().newInstance();
        field.set(obj, 20);
        //测试
        Student student1 = (Student) obj;
        System.out.println(student1.getAge());



        System.out.println("==============获取私有字段并使用==============");
        Field declaredField = c.getDeclaredField("id");
        //暴力反射
        declaredField.setAccessible(true);
        //获取一个公有构造方法并实例化
        Object obj1 = c.getConstructor().newInstance();
        declaredField.set(obj1, "001");
        //测试
        Student student2 = (Student) obj1;
        System.out.println(student2.getId());


System.out.println("===================================================================================================");

        System.out.println("==============获取所有公有方法================");
        Method[] methods = c.getMethods();
        for(Method method : methods) {
            System.out.println(method);
        }

        System.out.println("==============获取所有(公有&私有)方法=========");
        Method[] declaredMethods = c.getDeclaredMethods();
        for(Method method : declaredMethods) {
            System.out.println(method);
        }

        System.out.println("==============获取特定方法(带参)==============");
        Method method = c.getMethod("setAge", int.class);
        System.out.println(method);

        System.out.println("==============获取特定方法(不带参)============");

        //getDeclaredMethod：获取当前类的所有声明的方法，包括public、protected和private修饰的方法。需要注意的是，这些方法一定是在当前类中声明的，从父类中继承的不算，实现接口的方法由于有声明所以包括在内。
        //getMethod：获取当前类和父类的所有public的方法。这里的父类，指的是继承层次中的所有父类。

        Method method1 = c.getDeclaredMethod("getAge");
        System.out.println(method1);

        Method m = c.getMethod("getAge");
        System.out.println(m);

    }
}
