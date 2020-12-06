package org.example;

import org.example.handler.MyInvocationHandler;
import org.example.service.SomeService;
import org.example.service.impl.SomeServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MyApp {
    public static void main(String[] args) {
//        SomeService service = new SomeServiceImpl();
//        service.doSome();
//        System.out.println("====================");
//        service.doOther();

        //使用jdk的Proxy创建代理对象
        //创建目标对象
        SomeService target = new SomeServiceImpl();
        //创建InvocationHandler对象
        InvocationHandler handler = new MyInvocationHandler(target);
        //使用Proxy创建代理
        SomeService proxy = (SomeService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),handler);
        proxy.doSome();
    }

}
