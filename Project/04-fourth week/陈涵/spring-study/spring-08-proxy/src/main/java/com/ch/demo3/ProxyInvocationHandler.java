package com.ch.demo3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyInvocationHandler implements InvocationHandler {
    //被代理的接口
    private Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    //生成得到代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),rent.getClass().getInterfaces(),this);
    }

    //处理代理实例，并返回结果
    public Object invoke(Object proxy, Method method,Object[] args) throws InvocationTargetException, IllegalAccessException {
        seeHouse();

        //动态代理的本质，就是利用反射机制实现
        Object result = method.invoke(rent,args);

        heTong();
        return null;
    }

    public void seeHouse(){
        System.out.println("看房子");
    }

    public void heTong(){
        System.out.println("签合同");
    }
}
