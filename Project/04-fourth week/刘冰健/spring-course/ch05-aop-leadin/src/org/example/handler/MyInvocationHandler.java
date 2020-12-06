package org.example.handler;

import org.example.util.ServiceTools;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    private Object target;//SomeServiceImpl类

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //通过代理对象执行方法式，会调用执行这个invoke（）
        Object res = null;
        ServiceTools.doLog();
        //执行目标类的方法，通过Method实现
        res = method.invoke(target,args);//
        ServiceTools.doTrans();
        return res;
    }
}
